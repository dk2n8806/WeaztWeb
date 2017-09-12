package com.core.service.category.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.common.entity.category.GroupCategory;
import com.core.dao.category.GroupCategoryDao;
import com.core.service.category.GroupCategoryService;

@Service
@Transactional
public class GroupCategoryServiceImpl implements GroupCategoryService{

	@Autowired private GroupCategoryDao categoryDao;
	
	@Override
	public GroupCategory findById(Long id) {
		return categoryDao.findById(id);
	}

	@Override
	public GroupCategory getReference(Long id) {
		return categoryDao.getReference(id);
	}

	@Override
	public long getRowCount() {
		return categoryDao.getRowCount();
	}

	@Override
	public GroupCategory save(GroupCategory category) {
		if(category != null) 	categoryDao.persist(category);
		return category;
	}

	@Override
	public List<GroupCategory> getList() {
		return categoryDao.getList();
	}

	@Override
	public GroupCategory getByName(String type) {
		try {
			return categoryDao.getByDiscriminator(type.toLowerCase());
		} catch(NoResultException e){
			return null;
		}
	}

}
