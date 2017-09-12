package com.web.advice;

import com.web.controller.page.article.AboutPageController;
import com.web.controller.page.article.HelpArticleController;

public interface UriArticleRequestMapping {

	/** ::: {@link AboutPageController#showAboutPage()} */
	String ABOUT 	= "/about";
	
	/** ::: {@link AboutPageController#showTOSPage()} */
	String TOS	 	= "/tos";
	
	/** ::: {@link AboutPageController#showPrivacyPage()} */
	String PRIVACY 	= "/privacy";
	
	
	/** {@link ContactUsPageController#showPage()} */
	String CONTACT 			= "/contact_us";
	/** {@link ContactUsPageController#sendMessage(String, String, String, String, String)} */
	String CONTACT_REQUEST 	= "/contact_us/send-message";
	
	
	/** ::: {@link HelpArticleController#showHelpPage()} */
	String HELP = "/help";
	
	/** ::: {@link HelpArticleController#showArticlePage(String)} */
	String HELP_ARTICLE = "/help/{name}";
	
	
	/** {@link PartnerIndexPageController#showMerchantIndextPage()} */
	String PARTNER = "/partner";
}
