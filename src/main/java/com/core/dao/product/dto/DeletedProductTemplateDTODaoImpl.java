package com.core.dao.product.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.common.dto.product.DeletedProductTemplateDTO;
import com.common.entity.category.Category_;
import com.common.entity.merchant.Merchant;
import com.common.entity.product.DeletedProduct;
import com.common.entity.product.DeletedProduct_;
import com.common.entity.product.Product;
import com.common.entity.product.Product_;
import com.common.entity.product.embeded.BasicInfo_;
import com.common.entity.support.embeded.ImagePath_;
import com.common.util.date.DateInterval;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class DeletedProductTemplateDTODaoImpl 
	extends GenericJpaRepository<DeletedProductTemplateDTO, Long>
implements DeletedProductTemplateDTODao
{

	@Override
	public List<DeletedProductTemplateDTO> getTemplates(
			Merchant merchant, DateInterval dateInterval, Pageable pageable) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DeletedProductTemplateDTO> query
							= builder.createQuery(DeletedProductTemplateDTO.class);
		Root<DeletedProduct> root = query.from(DeletedProduct.class);
		Join<DeletedProduct, Product> joinProduct = root.join(DeletedProduct_.product);
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(merchant != null)
			predicates.add(builder.equal(joinProduct.get(Product_.merchant), merchant));
		if(dateInterval != null)
			predicates.add(builder.between(root.get(DeletedProduct_.createdOn), dateInterval.getFrom(), dateInterval.getTo()));
		
		query.select(builder.construct(DeletedProductTemplateDTO.class
																	, joinProduct.get(Product_.basicInfo).get(BasicInfo_.title)
																	, joinProduct.get(Product_.displayImage).get(ImagePath_.path)
																	, joinProduct.get(Product_.category).get(Category_.name)
																	, root
												)
				).where(predicates.toArray(new Predicate[]{}))
				.orderBy(builder.desc(root));	
		return getResultList(query, pageable);
	}

}
