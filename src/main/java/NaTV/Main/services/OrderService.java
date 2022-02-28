package NaTV.Main.services;

import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.entity.Order;
import NaTV.Main.models.objects.objectOrders.FinalResponse;
import NaTV.Main.models.objects.objectOrders.OrderData;

import java.util.List;

public interface OrderService {

    FinalResponse saveOrder(OrderData orderData);
    List<Order> getOrder();

}
