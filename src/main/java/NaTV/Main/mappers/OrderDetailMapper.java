package NaTV.Main.mappers;

import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.entity.OrderDetail;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper
public interface OrderDetailMapper {
    OrderDetail toOrderDetail(OrderDetailDto orderDetailDto);
    OrderDetailDto toOrderDetailDto(OrderDetail orderDetail);

    List<OrderDetail> toOrderDetails(List<OrderDetailDto> orderDetailDtos);
    List<OrderDetailDto> toOrderDetailDtos(List<OrderDetail> orderDetails);
}
