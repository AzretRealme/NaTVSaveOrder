package NaTV.Main.models.dto;

import NaTV.Main.enums.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderDto {
    private Long id;
    private String text;
    private String name;
    private String email;
    private double total_Price;
    private Date addDate;
    private Date editDate;
    private OrderStatus orderStatus;
}
