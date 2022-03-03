package NaTV.Main.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor@Entity
@Table(name = "order_days")
public class OrderDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date day;
    @ManyToOne
    @JoinColumn(name = "id_order_detail")
    private OrderDetail orderDetail;
}
