package com.mrd.identity.controller;

import com.mrd.identity.auth.CustomUserDetails;
import com.mrd.identity.constant.Constant;
import com.mrd.identity.entity.User;
import com.mrd.identity.entity.dto.AuthorizationResponseDTO;
import com.mrd.identity.entity.dto.UserDetailDTO;
import com.mrd.identity.entity.dto.UserSearchForm;
import com.mrd.identity.jwt.JwtTokenProvider;
import com.mrd.identity.message.MessageContent;
import com.mrd.identity.message.ResponseMessage;
import com.mrd.identity.service.AuthService;
import com.mrd.identity.service.UserService;
import com.mrd.identity.utils.StringUtil;
import com.mrd.identity.validate.UserValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * @author ducnh
 */
@RestController
@RequestMapping("/identity/api/user")
public class UserController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @GetMapping("/search")
    public ResponseMessage getAllUser(@RequestParam(value = "currentPage") int currentPage,
                                      @RequestParam(value = "rowsPerPage") int rowsPerPage,
                                      @RequestParam(value = "sort", required = false) String sort,

                                      @RequestHeader Map<String, String> headerParam) {
        ResponseMessage response = null;

        AuthorizationResponseDTO dto = getAuthorFromToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), Constant.VALIDATION_ACCOUNT_NOT_LOGIN,
                    new MessageContent(null));
        } else {

            if (currentPage == 0 || rowsPerPage == 0) {
                response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), Constant.VALIDATION_INVALID_PARAM_VALUE,
                        new MessageContent(null));
            } else {
                if (!StringUtil.isNullOrEmpty(sort) && !"uuid".equalsIgnoreCase(sort)
                        && !"email".equalsIgnoreCase(sort) && !"mobile".equalsIgnoreCase(sort)
                        && !"fullName".equalsIgnoreCase(sort) && !"createdAt".equalsIgnoreCase(sort)) {
                    response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Không có kiểu sort theo " + sort,
                            new MessageContent(null));
                } else {
                    UserSearchForm searchForm = new UserSearchForm();
                    searchForm.setSort(sort);
                    List<User> userList = userService.search(searchForm , currentPage, rowsPerPage);

                    long total = userService.count(searchForm);

                    if (userList == null) {
                        response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                new MessageContent(null, total));
                    } else {
                        response = new ResponseMessage(HttpStatus.OK.value(), HttpStatus.OK.toString(),
                                new MessageContent(userList, total));
                    }
                }
            }

        }
        return response;
    }

    @PostMapping
    public ResponseMessage createUser(final @Valid @RequestBody Map<String, Object> bodyParam, @RequestHeader Map<String, String> headerMap) {
        ResponseMessage response = null;
        if (bodyParam == null || bodyParam.isEmpty()) {
            response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), Constant.VALIDATION_INVALID_PARAM_VALUE,
                    new MessageContent(null));
        } else {
            AuthorizationResponseDTO dto = getAuthorFromToken(headerMap);
            if (dto == null) {
                return new ResponseMessage(HttpStatus.FORBIDDEN.value(), "Bạn chưa đăng nhập",
                        new MessageContent(null));
            }
            String username = (String) bodyParam.get("username");
            String email = (String) bodyParam.get("email");
            String mobile = (String) bodyParam.get("mobile");
            String fullName = (String) bodyParam.get("fullName");
            String password = (String) bodyParam.get("password");
            int status = 1;
            int gender = Integer.parseInt(bodyParam.get("gender").toString());

            String address = "";
            String avatar = "";
            String description = "";
            String birthDay = "";
            //String roleCode = (String) bodyParam.get("roleCode");

            if (bodyParam.get("address") != null) {
                address = (String) bodyParam.get("address");
            }
            if (bodyParam.get("avatar") != null) {
                avatar = (String) bodyParam.get("avatar");
            }
            if (bodyParam.get("description") != null) {
                description = (String) bodyParam.get("description");
            }
            if (bodyParam.get("birthDay") != null) {
                birthDay = (String) bodyParam.get("birthDay");
            }

            User user = new User();
            user.setUuid(UUID.randomUUID().toString());
            user.setUsername(username);
            user.setEmail(email);
            user.setMobile(mobile);
            user.setFullName(fullName);
            user.setPassword(password);
            user.setAddress(address);
            user.setIsDelete(0);
            user.setStatus(status);
            user.setAvatar(avatar);
            user.setGender(gender);
            user.setDescription(description);
            user.setCreatedBy(dto.getUsername());
            user.setModifyBy(dto.getUsername());
            user.setBirthDay(birthDay);

            String invalidData = new UserValidation().validateInsertUser(user);
            if (invalidData != null) {
                response = new ResponseMessage(HttpStatus.OK.value(), invalidData, new MessageContent(null));
            } else {
                User existUser = null;
                //Check email exist
                existUser = userService.findByEmail(email);
                if (existUser != null) {
                    invalidData = "Đã tồn tại user trên hệ thống ứng với email " + user.getEmail();
                    response = new ResponseMessage(HttpStatus.OK.value(), invalidData,
                            new MessageContent(null));
                } else {
                    //Check mobile exist
                    existUser = userService.findByMobile(user.getMobile());
                    if (existUser != null) {
                        invalidData = "Đã tồn tại user trên hệ thống ứng với mobile " + user.getMobile();
                        response = new ResponseMessage(HttpStatus.OK.value(), invalidData,
                                new MessageContent(null));
                    } else {
                        //Check user_name exist
                        existUser = userService.findByUserName(user.getUsername());
                        if (existUser != null) {
                            invalidData = "Đã tồn tại user trên hệ thống ứng với user_name " + user.getUsername();
                            response = new ResponseMessage(HttpStatus.OK.value(), invalidData,
                                    new MessageContent(null));
                        } else {
                            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
                            user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                            try {
                                userService.save(user);
                                response = new ResponseMessage(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), new MessageContent(user));
                            } catch (Exception ex) {
                                response = new ResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                                        new MessageContent(ex.toString()));
                            }
                            if (response != null && response.getStatus() == HttpStatus.CREATED.value()) {

                                //Send 2 RBAC => Create ROLE_USER
//                                idThreadManager.execute(() -> {
//                                    Map<String, Object> rbacRequestBodyParam = new HashMap<>();
//                                    rbacRequestBodyParam.put("uuidUser", user.getUuid());
//                                    rbacRequestBodyParam.put("roleCode", roleCode);
//
//                                    RequestMessage request = new RequestMessage("POST", RabbitMQProperties.RBAC_RPC_DEFAULT_ROLE_URL,
//                                            null, null, rbacRequestBodyParam, null);
//                                    LOGGER.info("CREATE USER ROLE (send 2 RBAC service) with param : " + request.toJsonString());
//                                    String result = rabbitMQClient.callRpcService(RabbitMQProperties.RBAC_RPC_EXCHANGE,
//                                            RabbitMQProperties.RBAC_RPC_QUEUE, RabbitMQProperties.RBAC_RPC_KEY,
//                                            request.toJsonString());
//                                    if (!StringUtil.isNullOrEmpty(result)) {
//                                        try {
//                                            ObjectMapper mapper = new ObjectMapper();
//                                            ResponseMessage resultResponse = mapper.readValue(result, ResponseMessage.class);
//                                            if (resultResponse != null && (resultResponse.getStatus() == HttpStatus.OK.value()
//                                                    || resultResponse.getStatus() == HttpStatus.CREATED.value())
//                                                    && resultResponse.getData() != null) {
//                                                LOGGER.info("CREATE USER ROLE for {} ok ", user.getUuid());
//                                            } else {
//                                                LOGGER.info("ERROR >>> create USER ROLE for {} ", user.getUuid());
//                                            }
//                                        } catch (JsonProcessingException ex) {
//                                            LOGGER.error("Error to parse json FROM CREATE DEFAULT ROLE (send 2 RBAC service) >>> " + ex.toString());
//                                            ex.printStackTrace();
//                                        }
//                                    }
//                                });
                            }
                        }
                    }
                }
            }
        }
        return response;
    }

    @GetMapping("/{uuid}")
    public ResponseMessage getDetailUser(@PathVariable String uuid, @RequestHeader Map<String, String> headerParam) {
        ResponseMessage response;
        AuthorizationResponseDTO dto = getAuthorFromToken(headerParam);
        if (dto == null) {
            response = new ResponseMessage(HttpStatus.FORBIDDEN.value(), Constant.VALIDATION_ACCOUNT_NOT_LOGIN,
                    new MessageContent(null));
        } else {
            if (!StringUtil.isNumberic(uuid) && !StringUtil.isUUID(uuid)) {
                response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), Constant.VALIDATION_INVALID_PARAM_VALUE, new MessageContent(null));
            } else {
                User user = userService.findByUuid(uuid);
                if (user == null) {
                    response = new ResponseMessage(HttpStatus.NOT_FOUND.value(), Constant.VALIDATION_DATA_NOT_FOUND, new MessageContent(null));
                } else {
                    UserDetailDTO detailDTO = new UserDetailDTO(user);
                    response = new ResponseMessage(new MessageContent(detailDTO));
                }
            }
        }
        return response;
    }


    private AuthorizationResponseDTO getAuthorFromToken(Map<String, String> headerParam) {
        if (headerParam == null || (!headerParam.containsKey("authorization")
                && !headerParam.containsKey("Authorization"))) {
            return null;
        }
        String bearerToken = headerParam.get("authorization");
        // Kiểm tra xem header Authorization có chứa thông tin jwt không
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            try {
                String jwt = bearerToken.substring(7);
                String uuid = tokenProvider.getUuidFromJWT(jwt);
                UserDetails userDetails = authService.loadUserByUuid(uuid);
                if (userDetails != null) {
                    User user = ((CustomUserDetails) userDetails).getUser();
                    if (user.getStatus() == User.STATUS_LOCK) {
                        return null;
                    } else {
                        UsernamePasswordAuthenticationToken authentication
                                = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        AuthorizationResponseDTO responseDTO = new AuthorizationResponseDTO((CustomUserDetails) authentication.getPrincipal(), null, null);
                        return responseDTO;
                    }
                }
            } catch (Exception ex) {
                LOGGER.error("failed on set user authentication", ex);
                return null;
            }
        }
        return null;
    }
}
