package com.rp.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rp.order.config.BizEnum.ORDER_STAT;
import com.rp.order.model.Delivery;
import com.rp.order.model.Member;
import com.rp.order.model.Order;
import com.rp.order.model.OrderParam;
import com.rp.order.model.OrderProd;
import com.rp.order.repository.MemberRepository;
import com.rp.order.service.OrderService;
import com.rp.order.utils.BizUtils;
import com.rp.order.utils.DateUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDataGenerator {
	private Logger logger = LoggerFactory.getLogger(getClass());

	private final static int ORDER_CNT = 20;
	private final static int THREAD_CNT = 4;

	@Autowired
	private OrderService orderService;

	@Autowired
	private MemberRepository memberRepository;

	@Test
	public void generate() throws InterruptedException, ExecutionException {
		// 배송중
		List<Order> delivers = deliver();

		// 배송완료
		List<Order> completeDeliverys = completeDelivery();

		// 구매확정
		List<Order> confirms = confirm();

		// 결제완료
		int settleCnt = ORDER_CNT - delivers.size() - completeDeliverys.size() - confirms.size();
		List<Order> settles = settle(settleCnt);

		List<Order> totOrders = new ArrayList<>();
		totOrders.addAll(settles);
		totOrders.addAll(delivers);
		totOrders.addAll(completeDeliverys);
		totOrders.addAll(confirms);

		logger.info("**********************************");
		logger.info("결제완료: {}", settles.size());
		logger.info("배송중  : {}", delivers.size());
		logger.info("배송완료: {}", completeDeliverys.size());
		logger.info("구매확정: {}", confirms.size());
		logger.info("**********************************");

		Collections.shuffle(totOrders);

		ExecutorService service = Executors.newFixedThreadPool(THREAD_CNT);

		for (int i = 0; i < THREAD_CNT; i++) {
			List<Order> orders = new ArrayList<>(totOrders.subList(i * 5, (i + 1) * 5));
			Future<?> future = service.submit(new Runnable() {
				@Override
				public void run() {
					for (Order order : orders) {
						if (StringUtils.isEmpty(order.getOrdStatCd())) {
							orderService.settle(order);
						} else if (ORDER_STAT.결제완료.getCode().equals(order.getOrdStatCd())) {
							orderService.deliver(order);
						} else if (ORDER_STAT.배송중.getCode().equals(order.getOrdStatCd())) {
							orderService.completeDelivery(order);
						} else if (ORDER_STAT.배송완료.getCode().equals(order.getOrdStatCd())) {
							logger.info("============>구매확정");
							orderService.confirm(order);
						}
					}
				}
			});
			future.get();
		}

		service.shutdown();

	}

	private List<Order> getOrderTargets(ORDER_STAT stat) {
		int maxCnt = RandomUtils.nextInt(2, 5);
		OrderParam param = new OrderParam();
		param.setOrdNoLike(DateUtils.date("yyyyMMdd"));
		param.setOrdStat(stat);
		List<Order> orderList = orderService.getOrders(param);

		if (CollectionUtils.isNotEmpty(orderList) && (maxCnt < orderList.size())) {
			return new ArrayList<>(orderList.subList(0, maxCnt));
		}
		return orderList;
	}

	private List<Order> deliver() {
		List<Order> orderList = getOrderTargets(ORDER_STAT.결제완료);

		for (Order order : orderList) {
			Delivery delivery = new Delivery();
			delivery.setOrdNo(order.getOrdNo());
			delivery.setShpNo(RandomStringUtils.randomAlphabetic(10));
			order.setDelivery(delivery);
		}

		return orderList;
	}

	private List<Order> completeDelivery() {
		List<Order> orderList = getOrderTargets(ORDER_STAT.배송중);
		return orderList;
	}

	private List<Order> confirm() {
		List<Order> orderList = getOrderTargets(ORDER_STAT.배송완료);

		for (Order order : orderList) {
			String ordNo = order.getOrdNo();
			List<OrderProd> orderProds = new ArrayList<>();
			for (int i = 1; i <= 3; i++) {
				OrderProd orderProd = new OrderProd();
				orderProd.setOrdNo(ordNo);
				orderProd.setSeq(i);
				orderProds.add(orderProd);
			}
			order.setOrderProds(orderProds);
		}
		return orderList;
	}

	private List<Order> settle(int settleCnt) {
		List<Order> orders = new ArrayList<>();
		List<Member> members = memberRepository.selectMembers(new Member());

		// 주문 10개 생성
		for (int i = 0; i < settleCnt; i++) {
			int mbrIdx = RandomUtils.nextInt(0, 4);
			Member ordMbr = members.get(mbrIdx);
			Order order = makeDummyRandomOrder(ordMbr);
			orders.add(order);
		}

		return orders;
	}

	private Order makeDummyRandomOrder(Member ordMbr) {
		Order order = new Order();
		order.setOrdNo(BizUtils.generateOrdNo());
		order.setMbrId(ordMbr.getMbrId());
		List<OrderProd> orderProds = new ArrayList<OrderProd>();
		for (int i = 0; i <= 3; i++) {
			OrderProd orderProd = new OrderProd();
			orderProd.setOrdNo(order.getOrdNo());
			orderProd.setSeq(i);
			orderProd.setProdNm("상품" + i);
			orderProd.setProdAmt(RandomUtils.nextLong(1000, 30000));
			orderProd.setOrdProdCnt(RandomUtils.nextInt(1, 5));
			orderProd.setSellerId("Seller" + i);
			orderProds.add(orderProd);
		}
		order.setOrderProds(orderProds);

		Delivery delivery = new Delivery();
		delivery.setOrdNo(order.getOrdNo());
		delivery.setAddr(ordMbr.getAddr());
		order.setDelivery(delivery);
		return order;
	}

}
