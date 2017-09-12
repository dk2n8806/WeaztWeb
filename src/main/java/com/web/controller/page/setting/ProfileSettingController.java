package com.web.controller.page.setting;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.admin.config.anno.ActiveUserPrincipal;
import com.amazonaws.http.HttpResponse;
import com.common.adapter.shippo.ShippoAddressAdapter;
import com.common.entity.account.Account;
import com.common.entity.account.Profile;
import com.common.entity.address.Address;
import com.common.entity.support.embeded.BirthDate;
import com.common.entity.support.embeded.Phone;
import com.common.type.Gender;
import com.common.type.USAStates;
import com.core.service.account.AccountService;
import com.core.service.account.ProfileService;
import com.web.advice.PageAdvice;
import com.web.advice.UriActionRequestMapping;
import com.web.advice.UriPageRequestMapping;
import com.web.response.JsonResponse;

@Controller
public class ProfileSettingController implements IProfileSettingAdvise {

	private static final Logger logger = LogManager.getLogger();
	
	@Autowired private AccountService accountService;
	@Autowired private ProfileService profileService;

/**
 * Display profile page of the customer
 * 
 * {@link IProfileSettingAdvise#showPage}
 */
	@RequestMapping(value=UriPageRequestMapping.Setting.PROFILE_SETTING
					, method=RequestMethod.GET)
	@Override
	public String showPage(Model model
						, @ActiveUserPrincipal Account customer) {
		System.out.println();
		logger.info("Show profile setting page by the customer");
		try {
			Profile profile = profileService.getByAccount(customer);
			Assert.notNull("profile", "Unable to retrieve profile of a customer");
			model.addAttribute("profile", profile);
		} catch(IllegalArgumentException e) {
			logger.error("[ERROR] " + e.getMessage());
		}
		return PageAdvice.Setting.PROFILE_SETTING;
	}

	
	
		
	/**
	 * <p>Update profile by the customer</p>
	 * 
	 * {@link IProfileSettingAdvise#updateProfile}
	 */
	@RequestMapping(value=UriActionRequestMapping.Setting.UPDATE_PROFILE
					, method=RequestMethod.POST)
	@ResponseBody
	@Override
	public JsonResponse updateProfile(@ActiveUserPrincipal Account customer
								, HttpServletResponse response
								, @RequestParam(defaultValue="") String _name
								, @RequestParam(defaultValue="") String _gender
								, @RequestParam(defaultValue="") String _phone
								, @RequestParam(defaultValue="") String _street
								, @RequestParam(defaultValue="") String _city
								, @RequestParam(defaultValue="") String _state
								, @RequestParam(defaultValue="") String _zipcode
								, @RequestParam(defaultValue="") String _date
								, @RequestParam(defaultValue="") String _month
								, @RequestParam(defaultValue="") String _year) 
	{
		System.out.println();
		logger.info("update profile by the customer");
		JsonResponse jsonResponse = new JsonResponse();
		Gender gender = Gender.lookup(_gender);
		Phone phone = Phone.create(_phone);
		BirthDate birthDate = BirthDate.create(Integer.valueOf(_date)
										  , Integer.valueOf(_month)
										  , Integer.valueOf(_year));
		//Address address = ShippoAddressAdapter.create(_street, _city, USAStates.lookup(_state), _zipcode);
		Profile profile = profileService.getByAccount(customer);
		if(profile == null) {
			logger.error("[ERROR] Unable to retrieve profile of an account");
		}
		//profile.setBirthdate(birthDate);
		//profile.setAddressAdapter(AddressAdapter.create(_name, address));
		profile.setGender(gender);
		profile.setPhone(phone);
		profileService.update(profile);
		
		//response.addCookie(new Cookie("ll", profile.getAddressAdapter().getAddress().getCity()));
		logger.info("Updated profile info");
		jsonResponse.setState(true);
		return jsonResponse;
	}
}
