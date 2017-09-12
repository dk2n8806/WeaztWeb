package com.core.dao.account;

import com.common.entity.account.Avatar;
import com.core.dao.generic.GenericRepository;

public interface AvatarDao extends GenericRepository<Avatar, Long>
{

	void setActive(Long id, boolean active);
}
