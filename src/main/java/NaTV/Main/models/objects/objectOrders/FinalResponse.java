package NaTV.Main.models.objects.objectOrders;

import NaTV.Main.enums.OrderStatus;
import NaTV.Main.models.dto.ChannelDto;
import lombok.Data;

import java.util.List;

@Data
public class FinalResponse {
    private String message;
    private String adText;
    private double total_sum;
    private double price;

    private ChannelDto channelDtos;
}
