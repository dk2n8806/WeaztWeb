package com.core.dao.subscriber;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.product.BookmarkProductTemplateDTO;
import com.common.entity.account.Account;
import com.common.entity.category.Category_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.subscriber.Bookmark;
import com.common.entity.subscriber.Bookmark_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class BookmarkTemplateDTODAOImpl 
	extends GenericJpaRepository<BookmarkProductTemplateDTO, Long>
implements BookmarkTemplateDTODao
{

	@Override
	public List<BookmarkProductTemplateDTO> getTemplates(
			Account account, Product product, Boolean isActive, DateInterval dateInterval, Pageable pageable) 
	{
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<BookmarkProductTemplateDTO> query
											= builder.createQuery(BookmarkProductTemplateDTO.class);
		Root<Bookmark> root = query.from(Bookmark.class);
		Join<Bookmark, Product> joinProduct = root.join(Bookmark_.product);
		
		
		List<Predicate> predicates = new ArrayList<>();
		if(account != null)
			predicates.add(builder.equal(root.get(Bookmark_.account), account));
		if(product != null)
			predicates.add(builder.equal(root.get(Bookmark_.product), product));
		if(isActive != null)
			predicates.add(builder.equal(root.get(Bookmark_.active), isActive));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(Bookmark_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.construct(BookmarkProductTemplateDTO.class 
									, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
									, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
									, joinProduct.get(Product_.category).get(Category_.name)
									, joinProduct.get(Product_.basicInfo).get(BasicInfo_.price)
									, root
					)
					).where(predicates.toArray(new Predicate[]{}))
					.orderBy(builder.desc(root));	
		
		return getResultList(query, pageable);
	}

}
