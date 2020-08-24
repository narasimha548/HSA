package com.hsa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hsa.entity.HsaAccountMasterEntity;
import com.hsa.model.HsaAccountMaster;
import com.hsa.model.UnlockAccount;
import com.hsa.repository.HsaAccountMasterRepository;
import com.hsa.utils.CommonUtils;
import com.hsa.utils.EmailUtils;


@Service
public class HsaAccountMasterServiceImpl  implements HsaAccountMasterService{

	private HsaAccountMasterRepository hsaMasterRepo;
	private EmailUtils utils;
	  


	public HsaAccountMasterServiceImpl(HsaAccountMasterRepository hsaMasterRepo, EmailUtils utils) {
		this.hsaMasterRepo = hsaMasterRepo;
		this.utils = utils;
	}





	@Override
	public Integer userRegistration(HsaAccountMaster hsaMaster) {
		HsaAccountMasterEntity entity=new HsaAccountMasterEntity();
		
		hsaMaster.setAccountStatus("LOCKED");
		hsaMaster.setDeleteSwitch("N");
		hsaMaster.setTempPassword(CommonUtils.getRandomPassword());
		
		BeanUtils.copyProperties(hsaMaster, entity);
		
		HsaAccountMasterEntity  reg = hsaMasterRepo.save(entity);
		
		
		if(reg.getUserId()!=null) {
		  boolean status= utils.sendMail(reg);
		  if(status) {
			  return reg.getUserId();
		  }
		}
		
		return null;
	}





	@Override
	public HsaAccountMaster findByEmailId(String emailId) {
		HsaAccountMaster hsaMaster=new HsaAccountMaster();
		HsaAccountMasterEntity masterEntity = hsaMasterRepo.findByEmailId(emailId);
		
		if(masterEntity.getEmailId() !=null) {
		   BeanUtils.copyProperties(masterEntity, hsaMaster);
		   return hsaMaster;
		}
		return null;
	}


	@Override
	public String updatePassword(HsaAccountMaster master) {
		HsaAccountMasterEntity entity=new HsaAccountMasterEntity();
		
		BeanUtils.copyProperties(master, entity);
		
		HsaAccountMasterEntity updatePwd = hsaMasterRepo.save(entity);
		
		 if(updatePwd.getUserId() !=null) {
			 return "Y";
		 }
		
		return null;
	}

	@Override
	public List<HsaAccountMaster> findByRole(String role) {
		List<HsaAccountMaster>  masterList=new  ArrayList<HsaAccountMaster>();
		
		System.out.println("roles ::::::   "+role);
		
	    List<HsaAccountMasterEntity> listEntity = hsaMasterRepo.findByRole(role);
		
	            for(HsaAccountMasterEntity entity:listEntity) {
	            	
	            	HsaAccountMaster m=new HsaAccountMaster();
	            	BeanUtils.copyProperties(entity, m);
	            	masterList.add(m);
	            }
	            
	            System.out.println("roles list::::::   "+masterList);
		
		
		return masterList;
	}

	@Override
	public HsaAccountMaster findById(Integer userId) {
	  
		
		 Optional<HsaAccountMasterEntity> id = hsaMasterRepo.findById(userId);
		 
		 if(id.isPresent()) {
			 HsaAccountMaster master=new HsaAccountMaster();
			 HsaAccountMasterEntity masterEntity = id.get();
			 
			 BeanUtils.copyProperties(masterEntity, master);
			 return master;
		 }
		
		return null;
	}
	@Override
	public boolean deleteAccount(Integer userId) {
		//HsaAccountMasterEntity entity=new HsaAccountMasterEntity();
		 //entity.setUserId(userId);
		 //entity.setDeleteSwitch("Y");
		 
		 Optional<HsaAccountMasterEntity> entityId= hsaMasterRepo.findById(userId);
		 if(entityId.isPresent()) {
			 HsaAccountMasterEntity masterEntity = entityId.get();
			//entity.setUserId(userId);
			 masterEntity.setDeleteSwitch("Y");
			return  hsaMasterRepo.save(masterEntity).getUserId()!=null;
			 
		 }
		
		 
		 
		
		return false;
	}
	
	@Override
	public boolean updateAccount(Integer userId) {
		
		 
		 Optional<HsaAccountMasterEntity> entityId= hsaMasterRepo.findById(userId);
		 if(entityId.isPresent()) {
			 HsaAccountMasterEntity masterEntity = entityId.get();
			//entity.setUserId(userId);
			 masterEntity.setDeleteSwitch("N");
			return  hsaMasterRepo.save(masterEntity).getUserId()!=null;
			 
		 }	
		return false;
	}
}
