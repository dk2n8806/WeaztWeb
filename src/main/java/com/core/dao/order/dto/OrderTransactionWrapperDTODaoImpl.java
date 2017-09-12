package com.core.dao.order.dto;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.common.dto.order.OrderTransactionWrapperDTO;
import com.common.entity.order.OrderBundle;
import com.common.entity.order.OrderTransaction;
import com.common.entity.order.OrderTransaction_;
import com.core.dao.generic.GenericJpaRepository;

@Repository
public class OrderTransactionWrapperDTODaoImpl 
	extends GenericJpaRepository<OrderTransactionWrapperDTO, Long>
implements OrderTransactionWrapperDTODao
{

	/** 
	 * <p>Retrieve order transaction template on an order bundle</p>
	 *
	 * @query
	 * [
	 * 		Select new {@link OrderTransactionWrapperDTO}
	 * 		From {@link OrderTransaction} OT
	 * 		Where OT.orderBundle = :orderBundle
	 * 		Order By OT desc
	 * ]
	 * 
	 * {@link OrderTransactionWrapperDTODao#getByOrder(OrderBundle)}
	 * 
	 * */
	@Override
	public List<OrderTransactionWrapperDTO> getByOrder(OrderBundle orderBundle) {
		final CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderTransactionWrapperDTO> query
							= builder.createQuery(OrderTransactionWrapperDTO.class);
		Root<OrderTransaction> root = query.from(OrderTransaction.class);
		
		query.select(builder.construct(OrderTransactionWrapperDTO.class
													, root
													, root.get(OrderTransaction_.shipment)
												)
				).where(builder.equal(root.get(OrderTransaction_.orderBundle), orderBundle))
				.orderBy(builder.desc(root));
		
		return entityManager.createQuery(query).getResultList();
	}

}
