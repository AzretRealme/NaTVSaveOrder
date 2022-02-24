package NaTV.Main.mappers.impl;

import NaTV.Main.enums.OrderStatus;
import NaTV.Main.mappers.OrderMapper;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.Order;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

public class OrderMapperImpl implements OrderMapper {
    @Override
    public Order toOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setText(orderDto.getText());
        order.setName(orderDto.getName());
        order.setEmail(orderDto.getEmail());
        order.setEditDate(orderDto.getEditDate());
        order.setAddDate(orderDto.getAddDate());
        order.setTotal_price(orderDto.getTotalPrice());
        order.setOrderStatus(orderDto.getOrderStatus());
        return order;
    }

    @Override
    public OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setText(orderDto.getText());
        orderDto.setName(order.getName());
        orderDto.setEmail(order.getEmail());
        orderDto.setEditDate(order.getEditDate());
        orderDto.setAddDate(order.getAddDate());
        orderDto.setTotalPrice(order.getTotal_price());
        orderDto.setOrderStatus(order.getOrderStatus());
        return orderDto;
    }
}
