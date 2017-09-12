package com.core.dao.merchant.impl;

import org.springframework.stereotype.Repository;

import com.common.entity.merchant.MerchantProfile;
import com.core.dao.generic.GenericJpaRepository;
import com.core.dao.merchant.MerchantProfileDao;

@Repository
public class MerchantProfileDaoImpl extends GenericJpaRepository<MerchantProfile, Long> implements MerchantProfileDao {

}
