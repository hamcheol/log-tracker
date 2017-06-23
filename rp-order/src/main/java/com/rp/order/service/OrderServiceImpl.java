package com.rp.order.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rp.order.config.BizEnum;
import com.rp.order.model.Delivery;
import com.rp.order.model.Member;
import com.rp.order.model.Order;
import com.rp.order.model.OrderParam;
import com.rp.order.model.OrderProd;
import com.rp.order.model.Product;
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
	public Order settle(Member member, List<Product> products) {
		Order order = new Order();
		String ordNo = BizUtils.generateOrdNo();
		order.setOrdNo(ordNo);
		order.setMbrId(member.getId());
		order.setOrdYmdt(DateUtils.date("yyyyMMddHHmmss"));
		order.setOrdStatCd(BizEnum.ORDER_STAT.결제완료.getCode());

		int seq = 1;
		Long ordTotAmt = 0L;
		List<OrderProd> orderProds = new ArrayList<>();
		for (Product product : products) {
			OrderProd op = new OrderProd();
			op.setOrdNo(ordNo);
			op.setSeq(seq);
			op.setProdNm(product.getName());
			op.setProdNo(product.getId());
			op.setProdAmt(product.getPrice());
			op.setSellerId(product.getSeller().getId());
			int ordProdCnt = RandomUtils.nextInt(1, 5);
			op.setOrdProdCnt(ordProdCnt);
			ordTotAmt += ordProdCnt * product.getPrice();
			orderProds.add(op);
			seq++;
		}
		order.setOrdTotAmt(ordTotAmt);
		order.setOrderProds(orderProds);

		Delivery delivery = new Delivery();
		delivery.setOrdNo(ordNo);
		delivery.setAddr(member.getAddr());
		order.setDelivery(delivery);

		for (OrderProd orderProd : orderProds) {
			orderProdRepository.insertOrderProd(orderProd);
		}

		delvRepository.insertDelivery(delivery);

		orderRepository.insertOrder(order);

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
