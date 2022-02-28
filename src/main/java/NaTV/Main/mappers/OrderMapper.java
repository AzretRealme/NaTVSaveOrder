package NaTV.Main.mappers;

import NaTV.Main.models.dto.OrderDayDto;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.Order;
import NaTV.Main.models.entity.OrderDay;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Order toOrder(OrderDto orderDto);
    OrderDto toOrderDto(Order order);

    List<Order> toOrders(List<OrderDto> orderDtos);
    List<OrderDto> toOrderDtos(List<Order> orders);
}
