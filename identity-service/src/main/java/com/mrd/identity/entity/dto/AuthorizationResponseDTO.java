package com.mrd.identity.entity.dto;

import com.mrd.identity.auth.CustomUserDetails;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author ducnh
 */
@Setter
@Getter
@NoArgsConstructor
public class AuthorizationResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String uuid;
    private String username;
    private String email;
    private String mobile;
    private String fullName;
    private Integer status;
    private String avatar;
    private String address;
    private String birthDay;
    private Integer gender;
    private String description;
    private String createdBy;
    private String modifyBy;
    private Timestamp createdAt;
    private Timestamp modifyAt;
    private Integer isDelete;

    public AuthorizationResponseDTO(CustomUserDetails userDetails, String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.uuid = userDetails.getUser().getUuid();
        this.username = userDetails.getUser().getUsername();
        this.email = userDetails.getUser().getEmail();
        this.mobile = userDetails.getUser().getMobile();
        this.fullName = userDetails.getUser().getFullName();
        this.status = userDetails.getUser().getStatus();
        this.avatar = userDetails.getUser().getAvatar();
        this.address = userDetails.getUser().getAddress();
        this.birthDay = userDetails.getUser().getBirthDay();
        this.gender = userDetails.getUser().getGender();
        this.description = userDetails.getUser().getDescription();
        this.createdBy = userDetails.getUser().getCreatedBy();
        this.modifyBy = userDetails.getUser().getModifyBy();
        this.createdAt = userDetails.getUser().getCreatedAt();
        this.modifyAt = userDetails.getUser().getModifyAt();
        this.isDelete = userDetails.getUser().getIsDelete();
    }

}
