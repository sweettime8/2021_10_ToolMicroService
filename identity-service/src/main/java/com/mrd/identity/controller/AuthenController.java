package com.mrd.identity.controller;

import com.mrd.identity.auth.CustomUserDetails;
import com.mrd.identity.constant.Constant;
import com.mrd.identity.entity.User;
import com.mrd.identity.entity.dto.AuthorizationResponseDTO;
import com.mrd.identity.jwt.JwtTokenProvider;
import com.mrd.identity.message.MessageContent;
import com.mrd.identity.message.ResponseMessage;
import com.mrd.identity.service.UserService;
import com.mrd.identity.utils.JWTutils;
import com.mrd.identity.validate.UserValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * @author ducnh
 */
@RestController
@RequestMapping("/api/authen")
public class AuthenController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenController.class);

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
//
//    @Autowired
//    private AuthService authService;

    /***
     *  Login
     */

    @PostMapping("/login")
    public ResponseMessage userLogin(final @Valid @RequestBody Map<String,Object> bodyParam) {
        ResponseMessage response = null;
        if (bodyParam == null || bodyParam.isEmpty()) {
            response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), Constant.VALIDATION_INVALID_PARAM_VALUE,
                    new MessageContent(HttpStatus.BAD_REQUEST.value(), Constant.VALIDATION_INVALID_PARAM_VALUE, null));
        }else {
            String username = bodyParam.get("username").toString();
            String password = bodyParam.get("password").toString();

            String invalidData = new UserValidation().validateLogin(username, password);
            if (invalidData != null) {
                response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), invalidData,
                        new MessageContent(HttpStatus.BAD_REQUEST.value(), invalidData, null));
            }else {
                // Check exist user with username
                User existUser = userService.findByUserName(username);
                if (existUser == null) {
                    invalidData = "username không tồn tại";
                    return new ResponseMessage(HttpStatus.NOT_FOUND.value(), invalidData,
                            new MessageContent(HttpStatus.NOT_FOUND.value(), invalidData, null));
                }else{
                    // Xác thực thông tin người dùng Request lên, nếu không xảy ra exception tức là thông tin hợp lệ
                    Authentication authentication = null;
                    try {
                        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                    } catch (AuthenticationException ex) {
                        LOGGER.error(ex.toString());
                        invalidData = "username hoặc mật khẩu không đúng";
                        return new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), invalidData,
                                new MessageContent(HttpStatus.UNAUTHORIZED.value(), invalidData, null));
                    }

                    // Set thông tin authentication vào Security Context
                    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                    if (userDetails.getUser().getStatus() == User.STATUS_LOCK) {
                        response = new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), Constant.VALIDATION_ACCOUNT_LOCKED, new MessageContent(HttpStatus.UNAUTHORIZED.value(), Constant.VALIDATION_ACCOUNT_LOCKED, null));
                    }else {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        // Trả về jwt cho người dùng.
                        String accessJwt = tokenProvider.generateToken(userDetails);
                        String refreshJwt = JWTutils.createToken(userDetails.getUser().getUuid());

                        AuthorizationResponseDTO responseDTO = new AuthorizationResponseDTO(userDetails, accessJwt, refreshJwt);

                        response = new ResponseMessage(new MessageContent(responseDTO));
                    }
                }
            }
        }

        return response;
    }
}
