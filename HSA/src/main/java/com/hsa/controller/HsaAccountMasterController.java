package com.hsa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hsa.model.HsaAccountMaster;
import com.hsa.service.HsaAccountMasterService;
import com.hsa.utils.CommonUtils;

@Controller
public class HsaAccountMasterController {

	@Autowired
	private HsaAccountMasterService service;

	@GetMapping(value = { "/", "/createAccount" })
	public String home(ModelMap model) {
		model.addAttribute("hsa", new HsaAccountMaster());
		model.addAttribute("roles", CommonUtils.getRoles());
		model.addAttribute("genderList", CommonUtils.getGenders());
		return "accountcreation";
	}

	@PostMapping("/save")
	public String saveAccount(@ModelAttribute("hsa") HsaAccountMaster hsa, RedirectAttributes attribute) {

		Integer userRegistration = service.userRegistration(hsa);

		if (userRegistration != null) {
			attribute.addFlashAttribute("Sucess", "Account Created SuccessFully Please Check Your Email !!!");
		} else {
			attribute.addFlashAttribute("fail", "Account not created !!!");
		}

		return "redirect:/createAccount";
	}

	@GetMapping("/view")
	public String searchAccounts() {

		return "viewaccounts";
	}

	@GetMapping("/viewAllAccounts")
	@ResponseBody
	public List<HsaAccountMaster> viewAllAccounts(@RequestParam("role") String role) {

		return service.findByRole(role);
	}

	@GetMapping("/editAcc")
	public String editAccountDetails(@RequestParam("id") String userId, Model map) {

		HsaAccountMaster master= service.findById(Integer.parseInt(userId));
		
		map.addAttribute("hsa", master);
		map.addAttribute("roles", CommonUtils.getRoles());
		map.addAttribute("genderList", CommonUtils.getGenders());
		return "accountcreation";
	}
	
	@RequestMapping("/deleteAcc")
	public String deleteAccount(@RequestParam("id") String userId, Model map) {
		
		  boolean deleteAccount = service.deleteAccount(Integer.parseInt(userId));
		  
		  if(deleteAccount) {
			  map.addAttribute("msg", "Account status Updated");
		  }
		
		return "viewaccounts";
	}

	@RequestMapping("/activateAcc")
	public String activateAccount(@RequestParam("id") String userId, Model map) {
		
		  boolean deleteAccount = service.updateAccount(Integer.parseInt(userId));
		  
		  if(deleteAccount) {
			  map.addAttribute("msg", "Account status Updated !!! ");
		  }
		
		return "viewaccounts";
	}

	
}
