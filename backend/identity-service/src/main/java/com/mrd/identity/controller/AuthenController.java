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
@RequestMapping("/identity/api/authen")
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
                    new MessageContent(null));
        }else {
            String username = bodyParam.get("username").toString();
            String password = bodyParam.get("password").toString();

            String invalidData = new UserValidation().validateLogin(username, password);
            if (invalidData != null) {
                response = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), invalidData,
                        new MessageContent(null));
            }else {
                // Check exist user with username
                User existUser = userService.findByUserName(username);
                if (existUser == null) {
                    invalidData = "username kh??ng t???n t???i";
                    return new ResponseMessage(HttpStatus.NOT_FOUND.value(), invalidData,
                            new MessageContent(null));
                }else{
                    // X??c th???c th??ng tin ng?????i d??ng Request l??n, n???u kh??ng x???y ra exception t???c l?? th??ng tin h???p l???
                    Authentication authentication = null;
                    try {
                        authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                    } catch (AuthenticationException ex) {
                        LOGGER.error(ex.toString());
                        invalidData = "username ho???c m???t kh???u kh??ng ????ng";
                        return new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), invalidData,
                                new MessageContent(null));
                    }

                    // Set th??ng tin authentication v??o Security Context
                    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
                    if (userDetails.getUser().getStatus() == User.STATUS_LOCK) {
                        response = new ResponseMessage(HttpStatus.UNAUTHORIZED.value(), Constant.VALIDATION_ACCOUNT_LOCKED, new MessageContent(null));
                    }else {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        // Tr??? v??? jwt cho ng?????i d??ng.
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
