package com.youme.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetails {

    private Integer id;
    private String email;
    private Boolean verified_email;
    private String given_name;
    private String family_name;
    private String picture;
    private String locale;

}
