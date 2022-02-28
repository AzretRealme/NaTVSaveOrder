package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderDayRepo;
import NaTV.Main.mappers.OrderDayMapper;
import NaTV.Main.models.dto.OrderDayDto;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.entity.OrderDay;
import NaTV.Main.models.entity.OrderDetail;
import NaTV.Main.services.OrderDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderDayServiceImpl implements OrderDayService {
    @Autowired
    private OrderDayRepo orderDayRepo;
    @Autowired
    private OrderDayMapper orderDayMapper;

    @Override
    public void saveOrderDay(Date date, OrderDetailDto orderDetailDto) {
        OrderDayDto orderDayDto = new OrderDayDto();
        orderDayDto.setDay(date);
        orderDayDto.setOrderDetail(orderDetailDto);
        orderDayRepo.save(orderDayMapper.toOrderDay(orderDayDto));
    }

    @Override
    public List<OrderDayDto> findByOrderDetailId(Long id) {
        return orderDayMapper.toOrderDayDtos(orderDayRepo.findByOrderDetailId(id));
    }
}
