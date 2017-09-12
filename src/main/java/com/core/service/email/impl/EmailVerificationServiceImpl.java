package com.core.service.email.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.common.email.EmailTokenLinkBuilder;
import com.common.email.EmailTopLevel;
import com.common.email.MimeMailRule;
import com.common.email.TokenParam;
import com.common.email.VelocityEmailTemplate;
import com.common.entity.account.Account;
import com.common.entity.account.VerificationToken;
import com.core.service.account.VerificationTokenService;
import com.core.service.email.IEmailVerificationService;
import com.web.advice.UriPageRequestMapping;

@Service
public class EmailVerificationServiceImpl implements IEmailVerificationService{


	private static final String TITLE = "Verify your email";
	
	@Autowired private VerificationTokenService verificationService;
	@Autowired private JavaMailSender mailSender;
	@Autowired private VelocityEngine velocityEngine;
	
		
	/******************************************************************************************
	 * Send an email to an account to verify the email address
	 * 
	 * @service {@link IEmailVerificationService#sendVerfiicationEmail(String)}
	 * 
	 */
	@Override
	public void sendVerfiicationEmail(final Account account) {
		final String email = account.getEmail();
		final String username = account.getUsername();		
		final VerificationToken token = verificationService.generateToken(account);
		TokenParam[] params = {new TokenParam("id", account.getId())
							  ,new TokenParam("token", token.getToken()) };
	
		final String tokenLink = EmailTokenLinkBuilder.getTokenLink(
											UriPageRequestMapping.User.VERIFIED_ACCOUNT
											, params );
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, MimeMailRule.UTF_8);
			     message.setTo(email);
			     message.setFrom(EmailTopLevel.WEAZT_SUPPORT);
			     message.setSubject(TITLE);
			     message.setSentDate(new Date());
			     Map<String, Object> model = new HashMap<String, Object>();
			     model.put("username", username);
			     model.put("verifiedLink", tokenLink);
			     
			     String text = VelocityEngineUtils.mergeTemplateIntoString(
			        velocityEngine, VelocityEmailTemplate.VERIFICATION_ACCOUNT_TEMP
			        				, MimeMailRule.UTF_8, model);

			     message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}

}
