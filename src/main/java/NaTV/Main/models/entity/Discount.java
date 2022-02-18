package NaTV.Main.models.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_channels")
    private Channel channel;
    private double percent;
    private Date startDate;
    private Date endDate;
    private int minDay;
}
