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
import com.common.entity.account.PasswordResetToken;
import com.core.service.account.PasswordResetTokenService;
import com.core.service.email.IResetPasswrodEmailService;
import com.web.advice.UriPageRequestMapping;

@Service
public class ResetPasswordEmailServiceImpl implements IResetPasswrodEmailService {

	@Autowired private JavaMailSender mailSender;
	@Autowired private VelocityEngine velocityEngine;
	@Autowired private PasswordResetTokenService tokenService;
	

/***************************************************************************
 * 
 * @see {@link IResetPasswrodEmailService#sendPasswordResetEmail(String)}
 * 
 **************************************************************************/
	@Override
	public void sendPasswordResetEmail(final Account account) {
		final String TITLE = "Reset password request";
		final PasswordResetToken token = tokenService.generateToken(account);

		TokenParam[] params = {new TokenParam("email", account.getEmail())
							  ,new TokenParam("token", token.getToken()) };

		final String tokenLink = EmailTokenLinkBuilder.getTokenLink(
											UriPageRequestMapping.User.RESET_PASSWORD
											, params );
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, MimeMailRule.UTF_8);
			     message.setTo(account.getEmail());
			     message.setFrom(EmailTopLevel.WEAZT_SUPPORT);
			     message.setSubject(TITLE);
			     message.setSentDate(new Date());
			     Map<String, Object> model = new HashMap<String, Object>();
			     model.put("resetLink", tokenLink);
			     
			     String text = VelocityEngineUtils.mergeTemplateIntoString(
			        velocityEngine, VelocityEmailTemplate.FORGOT_PASSWORD_TEMP, MimeMailRule.UTF_8, model);

			     message.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}




	
	
}
