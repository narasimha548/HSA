package com.hsa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hsa.entity.HsaAccountMasterEntity;



@Repository
public interface HsaAccountMasterRepository  extends JpaRepository<HsaAccountMasterEntity, Integer>{	
	
	  HsaAccountMasterEntity    findByEmailId(String emailId);
	  List<HsaAccountMasterEntity>  findByRole(String role);

}

