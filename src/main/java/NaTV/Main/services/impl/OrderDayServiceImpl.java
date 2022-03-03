package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderDayRepo;
import NaTV.Main.mappers.OrderDayMapper;
import NaTV.Main.models.dto.OrderDayDto;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.entity.OrderDay;
import NaTV.Main.services.OrderDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderDayServiceImpl implements OrderDayService {
    private OrderDayRepo orderDayRepo;
    private OrderDayMapper orderDayMapper;

    @Autowired
    @Lazy
    public OrderDayServiceImpl( OrderDayRepo orderDayRepo, OrderDayMapper orderDayMapper) {
        this.orderDayRepo = orderDayRepo;
        this.orderDayMapper = orderDayMapper;
    }

    @Override
    public OrderDayDto saveOrderDay(Date date, OrderDetailDto orderDetailDto) {
        OrderDayDto orderDayDto = new OrderDayDto();
        orderDayDto.setDay(date);
        orderDayDto.setOrderDetail(orderDetailDto);
        OrderDay orderDay = orderDayRepo.save(orderDayMapper.toOrderDay(orderDayDto));
        return orderDayMapper.toOrderDayDto(orderDay);
    }
}
