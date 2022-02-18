package NaTV.Main.models.entity;

import NaTV.Main.enums.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private String name;
    private String phone;
    private String email;
    private double totalPrice;
    private Date addDate;
    private Date editDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
