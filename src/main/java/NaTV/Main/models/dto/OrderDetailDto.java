package NaTV.Main.models.dto;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Long id;
    private ChannelDto channel;
    private OrderDto order;
    private double price;

}
