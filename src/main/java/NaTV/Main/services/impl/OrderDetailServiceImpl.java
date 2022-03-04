package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderDetailRepo;
import NaTV.Main.mappers.OrderDetailMapper;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.entity.OrderDetail;
import NaTV.Main.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepo orderDetailRepo;
    private OrderDetailMapper orderDetailMapper;


    @Autowired
    public OrderDetailServiceImpl( OrderDetailRepo orderDetailRepo, OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepo = orderDetailRepo;
        this.orderDetailMapper = orderDetailMapper;
    }

    @Override
    public OrderDetailDto save(OrderDetail orderDetail) {
        return orderDetailMapper.toOrderDetailDto(orderDetailRepo.save(orderDetail));
    }

}
