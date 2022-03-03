package NaTV.Main.services;

import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto save(OrderDetail orderDetail);


}
