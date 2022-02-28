package NaTV.Main.models.objects.objectOrders;

import NaTV.Main.enums.OrderStatus;
import lombok.Data;

import java.util.List;

@Data
public class FinalResponse {
    private OrderStatus orderStatus;
    private String message;
    private String adText;
    private double totalSum;

    private List<ChannelOrderResponse> channelOrders;
}
