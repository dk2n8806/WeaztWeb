package com.core.service.process.payout;

import java.util.List;

import com.common.entity.payout.Payout;

public interface ITransferPayoutService {

	void transfer(List<Payout> payouts);
}
