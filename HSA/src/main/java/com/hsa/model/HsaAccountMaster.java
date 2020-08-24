package com.hsa.model;


import lombok.Data;


@Data
public class HsaAccountMaster {

private Integer userId;
private String firstName;
private String lastName;
private String emailId;
private String gender;
private String role;
private String accountStatus;
private String deleteSwitch;
private String tempPassword;

}
