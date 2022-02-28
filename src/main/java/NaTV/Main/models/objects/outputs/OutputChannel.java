package NaTV.Main.models.objects.outputs;


import lombok.Data;

import java.util.List;

@Data
public class OutputChannel {
    private Long id;
    private String channelName;
    private String photo;
    private double price;
    private List<OutputDiscount> discounts;
}
