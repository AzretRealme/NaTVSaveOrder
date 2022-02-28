package NaTV.Main.services;

import NaTV.Main.models.dto.OrderDayDto;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.entity.OrderDay;
import NaTV.Main.models.entity.OrderDetail;

import java.util.Date;
import java.util.List;

public interface OrderDayService {
    void saveOrderDay(Date date, OrderDetailDto orderDetailDto);
    List<OrderDayDto> findByOrderDetailId(Long id);

}
