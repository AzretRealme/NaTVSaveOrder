package NaTV.Main.mappers;

import NaTV.Main.models.dto.OrderDayDto;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.Order;
import NaTV.Main.models.entity.OrderDay;

import java.util.List;

public interface OrderDayMapper {
    OrderDay toOrderDay(OrderDayDto orderDayDto);
    OrderDayDto toOrderDayDto(OrderDay orderDay);

    List<OrderDay> toOrderDays(List<OrderDayDto> orderDayDtos);
    List<OrderDayDto> toOrderDayDtos(List<OrderDay> orderDays);
}
