package com.rp.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.rp.order.config.BizEnum;
import com.rp.order.model.Delivery;
import com.rp.order.model.Order;
import com.rp.order.model.OrderParam;
import com.rp.order.model.OrderProd;
import com.rp.order.repository.DeliveryRepository;
import com.rp.order.repository.OrderProdRepository;
import com.rp.order.repository.OrderRepository;
import com.rp.order.utils.BizUtils;
import com.rp.order.utils.DateUtils;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderProdRepository orderProdRepository;

	@Autowired
	private DeliveryRepository delvRepository;

	@Transactional
	@Override
	public Order settle(Order order) {
		String ordNo = BizUtils.generateOrdNo();
		order.setOrdNo(ordNo);
		order.setOrdStatCd(BizEnum.ORDER_STAT.결제완료.getCode());

		List<OrderProd> orderProds = order.getOrderProds();
		int orderProdSeq = 1;
		for (OrderProd orderProd : orderProds) {
			orderProd.setOrdNo(ordNo);
			orderProd.setSeq(orderProdSeq++);
			orderProdRepository.insertOrderProd(orderProd);
		}

		Long ordTotAmt = 0L;
		if (!CollectionUtils.isEmpty(orderProds)) {
			for (OrderProd orderProd : orderProds) {
				ordTotAmt += (orderProd.getProdAmt() * orderProd.getOrdProdCnt());
			}
			order.setOrdTotAmt(ordTotAmt);
		}
		orderRepository.insertOrder(order);

		Delivery delivery = order.getDelivery();
		delivery.setOrdNo(ordNo);
		delvRepository.insertDelivery(delivery);

		return order;
	}

	@Transactional
	@Override
	public Order deliver(Order order) {
		String ordNo = order.getOrdNo();
		order.setOrdStatCd(BizEnum.ORDER_STAT.배송중.getCode());
		orderRepository.updateOrder(order);

		Delivery delivery = order.getDelivery();
		delvRepository.updateDelivery(delivery);
		return order;
	}

	@Override
	public Order completeDelivery(Order order) {
		String ordNo = order.getOrdNo();
		order.setOrdStatCd(BizEnum.ORDER_STAT.배송완료.getCode());
		orderRepository.updateOrder(order);

		return order;
	}

	@Transactional
	@Override
	public Order confirm(Order order) {
		String ordNo = order.getOrdNo();
		order.setOrdStatCd(BizEnum.ORDER_STAT.구매확정.getCode());
		orderRepository.updateOrder(order);

		List<OrderProd> orderProds = order.getOrderProds();
		for (OrderProd orderProd : orderProds) {
			orderProd.setPurchCfrmDt(DateUtils.date("yyyyMMddHHmmss"));
			orderProdRepository.updateOrderProd(orderProd);
		}

		return order;
	}

	@Override
	public List<Order> getOrders(OrderParam param) {
		return orderRepository.selectOrders(param);
	}

}
