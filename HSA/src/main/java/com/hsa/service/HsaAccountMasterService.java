package com.hsa.service;

import java.util.List;

import com.hsa.model.HsaAccountMaster;
import com.hsa.model.UnlockAccount;

public interface HsaAccountMasterService {

	Integer  userRegistration(HsaAccountMaster  hsaMaster);
	
	HsaAccountMaster findByEmailId(String emailId);
	
	String updatePassword(HsaAccountMaster master);
	
	List<HsaAccountMaster> findByRole(String role);
	
	HsaAccountMaster findById(Integer userId);
	
	boolean deleteAccount(Integer userId);
	boolean updateAccount(Integer userId);
	
}
