package com.core.service.stripe.adapter.impl;

import org.springframework.stereotype.Service;

import com.common.adapter.stripe.CardAdapter;
import com.common.adapter.stripe.util.StripeRequestManage;
import com.core.service.stripe.adapter.CardAdapterService;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Card;
import com.stripe.model.Token;

@Service("stripeCardAdapterServiceImpl")
public class CardAdapterServiceImpl implements CardAdapterService {

	
	@Override
	public CardAdapter lookup(String tokenId) throws AuthenticationException, InvalidRequestException, APIConnectionException, CardException, APIException {
		Token token = Token.retrieve(tokenId, StripeRequestManage.getOptions());
		Card card = token.getCard();
		return CardAdapter.create(card);
	}

}
