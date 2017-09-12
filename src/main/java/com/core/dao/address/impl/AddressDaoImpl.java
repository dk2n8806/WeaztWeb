package com.core.dao.address.impl;

import org.springframework.stereotype.Repository;

import com.common.entity.address.Address;
import com.core.dao.address.AddressDao;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class AddressDaoImpl extends GenericJpaRepository<Address, Long> implements AddressDao{

}
