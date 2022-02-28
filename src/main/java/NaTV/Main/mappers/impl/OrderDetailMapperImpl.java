package NaTV.Main.mappers.impl;

import NaTV.Main.mappers.ChannelMapper;
import NaTV.Main.mappers.OrderDetailMapper;
import NaTV.Main.mappers.OrderMapper;
import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderDetailMapperImpl implements OrderDetailMapper {
    @Autowired
    private ChannelMapper channelMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public OrderDetail toOrderDetail(OrderDetailDto orderDetailDto) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(orderDetailDto.getId());
        orderDetail.setPrice(orderDetailDto.getPrice());
        orderDetail.setChannel(channelMapper.toChannel(orderDetailDto.getChannel()));
        orderDetail.setOrder(orderMapper.toOrder(orderDetailDto.getOrder()));
        return orderDetail;
    }

    @Override
    public OrderDetailDto toOrderDetailDto(OrderDetail orderDetail)
    {
        OrderDetailDto orderDetailDto = new OrderDetailDto();
        orderDetailDto.setId(orderDetail.getId());
        orderDetailDto.setPrice(orderDetail.getPrice());
        orderDetailDto.setChannel(channelMapper.toChannelDto(orderDetail.getChannel()));
        orderDetailDto.setOrder(orderMapper.toOrderDto(orderDetail.getOrder()));
        return orderDetailDto;
    }

    @Override
    public List<OrderDetail> toOrderDetails(List<OrderDetailDto> orderDetailDtos) {
        return orderDetailDtos.stream()
                .map(x -> toOrderDetail(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDetailDto> toOrderDetailDtos(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(x -> toOrderDetailDto(x))
                .collect(Collectors.toList());
    }
}
