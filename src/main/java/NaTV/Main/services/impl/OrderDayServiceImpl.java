package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderDayRepo;
import NaTV.Main.mappers.OrderDayMapper;
import NaTV.Main.models.entity.OrderDay;
import NaTV.Main.models.entity.OrderDetail;
import NaTV.Main.services.OrderDayService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class OrderDayServiceImpl implements OrderDayService {
    @Autowired
    private OrderDayRepo orderDayRepo;
    @Autowired
    private OrderDayMapper orderDayMapper;

    @Override
    public OrderDay saveOrderDay(Date date, OrderDetail orderDetail) {
        OrderDay orderDay = new OrderDay();
        orderDay.setDay(date);
        orderDay.setOrderDetail(orderDetail);
        return orderDayRepo.save(orderDay);

//    save dto - return orderDerailMapper2.toOrderDetailDto(orderDetailRepository.save(orderDetail));

    }
}
