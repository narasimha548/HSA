package com.hsa.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "HAS_ACCOUNTS_MASTER")
public class HsaAccountMasterEntity {
	
@Id
@Column(name = "HSA_USER_ID")
@GeneratedValue(generator="hsa_id_gen")
@GenericGenerator(name="hsa_id_gen",strategy="increment")
private Integer userId;

@Column(name = "FIRST_NAME")
private String firstName;

@Column(name = "LAST_NAME")
private String lastName;

@Column(name = "EMAIL_ID")
private String emailId;

@Column(name = "GENDER")
private String gender;

@Column(name = "ROLE")
private String role;

@Column(name = "ACC_STATUS")
private String accountStatus;

@Column(name = "DELETE_SW")
private String deleteSwitch;

@Column(name = "USER_PWD")
private String tempPassword;

@CreationTimestamp
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "CREATED_DATE",updatable = false)
private Date creationDate;


@UpdateTimestamp
@Temporal(TemporalType.TIMESTAMP)
@Column(name = "UPDATED_DATE",insertable =  false)
private Date updationDate;

}
