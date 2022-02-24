package NaTV.Main.mappers;

import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.Order;

public interface OrderMapper {
    Order toOrder(OrderDto orderDto);
    OrderDto toOrderDto(Order order);
}
