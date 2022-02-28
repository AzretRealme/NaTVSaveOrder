package NaTV.Main.services;

import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto save(OrderDetail orderDetail);
    List<OrderDetailDto> findAllByOrder(OrderDto orderDto);
    List<OrderDetailDto> findByOrderId(Long id);


}
