package com.hsa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hsa.model.HsaAccountMaster;
import com.hsa.model.UnlockAccount;
import com.hsa.service.HsaAccountMasterService;

@Controller
public class UnlockAccountController {
	
	private HsaAccountMasterService service;
	
	public UnlockAccountController(HsaAccountMasterService service) {
		this.service = service;
	}


	@GetMapping("/unlockAcc")
	public String unlockAccountForm(@RequestParam("email")String email,Model map) {
		System.out.println(email+"  email id ::::::::: ");
		map.addAttribute("email", email);
		map.addAttribute("unlockAcc", new UnlockAccount());
		return "unlockAccForm";
	}

	
	@PostMapping("/unlockUserAcc")
	public String unlockUserAcc(@ModelAttribute("unlockAcc") UnlockAccount acc, Model model) {
		System.out.println(acc);
            
		HsaAccountMaster master = service.findByEmailId(acc.getEmail());
		
		if(master!=null) {
			
			master.setAccountStatus("UN-LOCKED");
			master.setTempPassword(acc.getConfirmPwd());
			String msg=service.updatePassword(master);
			if(msg.equals("Y")) {
				model.addAttribute("msg", "Updated password successfully.......");
			}
		}
		   
		return "unlockAccForm";
	}

	
}
