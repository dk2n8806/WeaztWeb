package com.web.controller.page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.common.entity.promo.NewLetter;
import com.core.service.promo.NewLetterService;
import com.web.response.JsonResponse;


/**
 * 
 * @author dk2n_
 *
 */
@Controller
public class NewLetterFormController {

	@Autowired private NewLetterService service;
	
	/** :::
	 * Handle new letter subscribe by the user
	 * 
	 * @param _se The email value input
	 * @return
	 * ::: */
	@RequestMapping(value="subscribe-new-letter", method=RequestMethod.POST)
	@ResponseBody
	public JsonResponse newLetterSubscribe(@RequestParam String _se) {
		synchronized (this) {
			JsonResponse json = new JsonResponse();
			NewLetter letter = service.save(_se);
			if(letter != null) {
				json.setState(true);
			}
			return json;	
		}
	}
}
