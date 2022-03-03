package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderDetailRepo;
import NaTV.Main.dao.OrderRepo;
import NaTV.Main.mappers.OrderDetailMapper;
import NaTV.Main.mappers.OrderMapper;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.OrderDetail;
import NaTV.Main.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public OrderDetailDto save(OrderDetail orderDetail) {
        return orderDetailMapper.toOrderDetailDto(orderDetailRepo.save(orderDetail));
    }

}
