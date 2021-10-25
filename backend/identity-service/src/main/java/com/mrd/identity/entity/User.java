package com.mrd.identity.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Builder
@Table(name = "user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "uuid")
    private String uuid;

    @Size(max = 100)
    @Column(name = "username")
    private String username;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 20)
    @Column(name = "mobile")
    private String mobile;

    @Size(max = 255)
    @Column(name = "fullname")
    private String fullName;

    @Size(max = 255)
    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column(name = "status")
    private Integer status;

    @Column(name = "email_verify")
    private Integer emailVerify;

    @Column(name = "mobile_verify")
    private Integer mobileVerify;

    @Size(max = 255)
    @Column(name = "skype")
    private String skype;

    @Size(max = 255)
    @Column(name = "facebook")
    private String facebook;

    @Size(max = 1000)
    @Column(name = "avatar")
    private String avatar;

    @Size(max = 255)
    @Column(name = "address")
    private String address;

    @Size(max = 255)
    @Column(name = "birthday")
    private String birthDay;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "description")
    private String description;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modify_by")
    private String modifyBy;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "modify_at")
    private Timestamp modifyAt;

    @Size(max = 255)
    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "last_login")
    private Timestamp lastLogin;

    @Column(name = "signup_type")
    private int signupType;

    @Size(max = 255)
    @Column(name = "fb_id")
    private String fbId;

    @Size(max = 255)
    @Column(name = "gg_id")
    private String ggId;

    @Size(max = 255)
    @Column(name = "apple_id")
    private String appleId;

    @Column(name = "is_delete")
    private Integer isDelete;

    @JsonIgnore
    public static final Integer STATUS_ACTIVE = 1;
    @JsonIgnore
    public static final Integer STATUS_LOCK = -1;

    @PrePersist
    void preInsert() {
        if (this.getCreatedAt() == null) {
            this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }
        if (this.getUuid() == null) {
            this.setUuid(UUID.randomUUID().toString());
        }
        if (this.getModifyAt() == null) {
            this.setModifyAt(new Timestamp(System.currentTimeMillis()));
        }
    }

    @PreUpdate
    void preUpdate(){

    }

}
