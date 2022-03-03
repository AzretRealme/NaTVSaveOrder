package NaTV.Main.models.entity;

import lombok.Data;

import javax.persistence.*;
@Getter
@Setter
@ToString
@NoArgsConstructor
@Data
@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String channelName;
    @Column(length = 10000)
    private String photo;
    private boolean active;
    private int orderNum;
}
