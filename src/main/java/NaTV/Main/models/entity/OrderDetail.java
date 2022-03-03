package NaTV.Main.models.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "order_details")
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

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        OrderDetail that = (OrderDetail) object;
        return java.lang.Double.compare(that.price, price) == 0 && java.util.Objects.equals(id, that.id) && java.util.Objects.equals(channel, that.channel) && java.util.Objects.equals(order, that.order);
    }

    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), id, channel, order, price);
    }
}
