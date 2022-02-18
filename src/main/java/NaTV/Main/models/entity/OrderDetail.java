package NaTV.Main.models.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "orderDetails")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_channels")
    private Channel channel;
    @ManyToOne
    @JoinColumn(name = "id_orders")
    private Order order;
    private double price;

}
