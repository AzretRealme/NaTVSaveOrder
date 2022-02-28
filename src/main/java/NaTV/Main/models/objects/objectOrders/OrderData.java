package NaTV.Main.models.objects.objectOrders;

import lombok.Data;

import java.util.List;

@Data
public class OrderData {
    private String text;
    private String Name;
    private String Phone;
    private String Email;
    private List<ChannelOrder> channels;

}
