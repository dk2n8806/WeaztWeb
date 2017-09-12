package com.web.controller.page.setting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.admin.config.anno.ActiveUserPrincipal;
import com.common.entity.account.Account;
import com.common.entity.account.Avatar;
import com.common.entity.account.Profile;
import com.common.entity.account.SimpleShipping;
import com.common.entity.support.embeded.ImagePath;
import com.common.type.Role;
import com.core.amazon.bucket.IS3ProfileUploadService;
import com.core.service.account.AccountService;
import com.core.service.account.AvatarService;
import com.core.service.account.ProfileService;
import com.core.service.account.SimpleShippingService;
import com.core.service.merchant.MerchantService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriManager;
import com.web.advice.UriPageRequestMapping;
import com.web.response.JsonResponse;

@Controller
public class AccountSettingController implements IAccountSettingAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private ProfileService profileService;
	@Autowired private AccountService accountService;
	@Autowired private IS3ProfileUploadService is3ProfileUpload;
	@Autowired private SimpleShippingService simpleShippingService;
	@Autowired private AvatarService avatarService;
	@Autowired private MerchantService merchantService;
	
	
	/**
	 * Display account_setting page;
	 * 
	 * {@link IAccountSettingAdvise#displayGeneralAccountSetting(Model, Account)}
	 */
	@RequestMapping(value=UriPageRequestMapping.Setting.ACCOUNT_SETTING
				, method=RequestMethod.GET)
	@Override
	public String displayGeneralAccountSetting(Model model
								, @ActiveUserPrincipal Account account) 
	{
		Profile profile = profileService.getByAccount(account);
		SimpleShipping shipping = simpleShippingService.getPrimaryByAccount(account);
		model.addAttribute("profile", profile);
		model.addAttribute("shipping", shipping);
		if(account.getRole() == Role.MERCHANT) {
			model.addAttribute("merchantProfile", merchantService.getProfile(account.getMerchant()));
		}
		return PageAdvice.Setting.ACCOUNT_SETTING;
	}
	
		
	/***************************************************************************
	 * Update account general info
	 * 
	 * {@link IAccountSettingAdvise#updateGeneralAccount}
	 */
	@RequestMapping(value=UriActionRequestMapping.Setting.UPDATE_ACCOUNT_INFO
					, method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse updateGeneralAccount(@ActiveUserPrincipal Account customer
			, @RequestParam(defaultValue="") String _username
			, @RequestParam(defaultValue="") String _email) {
		System.out.println();
		logger.info("Update account info");
		JsonResponse jsonResponse = new JsonResponse();
		
		
		if(_username.equals("") || _email.equals("")) {
			logger.error("username or email cant be empty");
			jsonResponse.setState(false);
			return jsonResponse;
		}
	
		Account entity = accountService.findById(customer.getId());
		entity.setEmail(_email);
		entity.setUsername(_username);
		accountService.update(entity);
		customer.setUsername(_username);
		customer.setEmail(_email);
	
		jsonResponse.setState(true);
		return jsonResponse;
	}
	

	
	/**
	 * Update profile info
	 * 
	 * {@link IAccountSettingAdvise#updatePhoto}
	 */
	@RequestMapping(value=UriActionRequestMapping.Setting.UPDATE_PROFILE_IMAGE)
	@Override
	public String updatePhoto(@ActiveUserPrincipal Account customer
											, @RequestParam MultipartFile _image) 
	{

		synchronized (this) {
			String path = is3ProfileUpload.uploadProfile(_image);
			if(path != null) {
				Account account = accountService.findById(customer.getId());
				
				avatarService.deactive(account.getAvatar().getId());
				
				account.setAvatar(Avatar.create(ImagePath.create(path)));
				accountService.update(account);
				customer.setAvatar(account.getAvatar());
			}
			return new UriManager().setRequest(UriPageRequestMapping.Setting.ACCOUNT_SETTING)
									.getRedirectRequest();
		}
	}
	
}
