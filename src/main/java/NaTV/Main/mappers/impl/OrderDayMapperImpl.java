package NaTV.Main.mappers.impl;

import NaTV.Main.mappers.OrderDayMapper;
import NaTV.Main.mappers.OrderDetailMapper;
import NaTV.Main.models.dto.OrderDayDto;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.Order;
import NaTV.Main.models.entity.OrderDay;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDayMapperImpl implements OrderDayMapper {
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Override
    public OrderDay toOrderDay(OrderDayDto orderDayDto) {
        OrderDay orderDay = new OrderDay();
        orderDay.setId(orderDayDto.getId());
        orderDay.setDay(orderDayDto.getDay());
        orderDay.setOrderDetail(orderDetailMapper.toOrderDetail(orderDayDto.getOrderDetail()));
        return orderDay;
    }

    @Override
    public OrderDayDto toOrderDayDto(OrderDay orderDay) {
        OrderDayDto orderDayDto = new OrderDayDto();
        orderDayDto.setId(orderDay.getId());
        orderDayDto.setDay(orderDay.getDay());
        orderDayDto.setOrderDetail(orderDetailMapper.toOrderDetailDto(orderDay.getOrderDetail()));
        return orderDayDto;
    }

    @Override
    public List<OrderDay> toOrderDays(List<OrderDayDto> orderDayDtos) {
        return orderDayDtos.stream()
                .map(x -> toOrderDay(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDayDto> toOrderDayDtos(List<OrderDay> orderDays) {
        return orderDays.stream()
                .map(x -> toOrderDayDto(x))
                .collect(Collectors.toList());
    }
}
