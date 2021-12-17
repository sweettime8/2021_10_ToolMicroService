package com.mrd.identity.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author ducnh
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchForm {

    private String sort;
    private String keyword;

}
