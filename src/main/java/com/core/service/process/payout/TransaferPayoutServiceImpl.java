package com.core.service.process.payout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.merchant.Merchant;
import com.common.entity.payout.Payout;
import com.common.entity.stripe.StripeAccount;
import com.common.entity.stripe.StripeTransfer;
import com.common.exception.PayoutException;
import com.common.util.date.DateInterval;
import com.core.service.payout.PayoutService;
import com.core.service.stripe.StripeAccountService;
import com.core.service.stripe.StripeCreatorService;

@Service
@Transactional
public class TransaferPayoutServiceImpl {

	@Autowired private StripeCreatorService stripeCreatorService;
	@Autowired private StripeAccountService stripeAccountService;
	@Autowired private PayoutService payoutService;
	
	private void handle(DateInterval dateInterval) throws PayoutException {
		
	}
}
