package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderDetailRepo;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.entity.OrderDetail;
import NaTV.Main.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepo orderDetailRepo;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        orderDetail = orderDetailRepo.save(orderDetail);
        return orderDetail;
    }
}
