package NaTV.Main.services;

import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.entity.OrderDetail;

public interface OrderDetailService {
    OrderDetail save(OrderDetail orderDetail);
}
