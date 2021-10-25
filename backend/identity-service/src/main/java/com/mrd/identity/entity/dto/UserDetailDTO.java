package com.mrd.identity.entity.dto;

import com.mrd.identity.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * @author ducnh
 */
@Setter
@Getter
public class UserDetailDTO {

    private String uuid;
    private String userName;
    private String email;
    private String mobile;
    private String fullName;
    private String avatar;
    private Integer status;
    private String address;
    private String skype;
    private String facebook;
    private String loginIp;
    private Integer signupType;
    private Timestamp createdAt;
    private Timestamp lastLogin;
    private String fbId;
    private String ggId;
    private String appleId;
    private String birthDay;
    private Integer gender;

    public UserDetailDTO() {
    }

    public UserDetailDTO(User user) {
        this.uuid = user.getUuid();
        this.userName = user.getUsername();
        this.email = user.getEmail();
        this.mobile = user.getMobile();
        this.fullName = user.getFullName();
        this.avatar = user.getAvatar();
        this.status = user.getStatus();
        this.address = user.getAddress();
        this.createdAt = user.getCreatedAt();
        this.birthDay = user.getBirthDay();
        this.gender = user.getGender();
    }


}
