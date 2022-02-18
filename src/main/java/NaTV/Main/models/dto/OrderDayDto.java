package NaTV.Main.models.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderDayDto {
    private Long id;
    private Date day;
    private OrderDetailDto orderDetail;

}
