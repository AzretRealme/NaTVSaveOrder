package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderRepo;
import NaTV.Main.enums.OrderStatus;
import NaTV.Main.mappers.OrderMapper;
import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.dto.OrderDetailDto;
import NaTV.Main.models.dto.OrderDto;
import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Order;
import NaTV.Main.models.objects.objectOrders.ChannelOrder;
import NaTV.Main.models.objects.objectOrders.FinalResponse;
import NaTV.Main.models.objects.objectOrders.OrderData;
import NaTV.Main.services.ChannelService;
import NaTV.Main.services.DiscountService;
import NaTV.Main.services.OrderService;
import NaTV.Main.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepo orderRepo;
    private OrderMapper orderMapper;
    private PriceService priceService;
    private DiscountService discountService;
    private ChannelService channelService;

    @Autowired
    public OrderServiceImpl( OrderRepo orderRepo, OrderMapper orderMapper, PriceService priceService, DiscountService discountService, ChannelService channelService) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
        this.priceService = priceService;
        this.discountService = discountService;
        this.channelService = channelService;
    }

    @Override
    public FinalResponse saveOrder(OrderData orderData) {

        FinalResponse finalResponse = new FinalResponse();

        Order order = new Order();

        //сохраняем Entity
        order.setOrderStatus(OrderStatus.ACTIVE);
        order.setText(orderData.getText());
        order.setName(orderData.getName());
        order.setEmail(orderData.getEmail());
        order.setAddDate(new Date());
        order.setEditDate(new Date());
        //паттерн, который убирает пробелы
        String textLength = order.getText().replaceAll("\\s+", "");
        OrderDto orderDto1 = orderMapper.toOrderDto(orderRepo.save(order));

        List<ChannelOrder> channelOrders = orderData.getChannels();
        for (ChannelOrder c : channelOrders) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setOrder(orderDto1);
            //get size
            int days = c.getDays().size();
            //находим minDay с помощью метода findByMinDay
            DiscountDto discountDto = discountService.findByMinDay(days, c.getChannelId());
            //find priceDto id
            PriceDto priceDto = priceService.findByChannelAndDate(c.getChannelId());
            double pricePerSymbol = priceDto.getPrice();
            int sizeAdText = textLength.length();//длина текста
            if (discountDto != null) {
                double percent = discountDto.getPercent();
                double withoutDiscount = pricePerSymbol * sizeAdText;
                double discountInSum = withoutDiscount * percent / 100;
                double sumForChannel = (withoutDiscount - discountInSum) * days;
                finalResponse.setPrice(pricePerSymbol);
                finalResponse.setTotal_sum(sumForChannel);
            } else {
                double withoutDiscount = (pricePerSymbol * sizeAdText) * days;
                finalResponse.setPrice(pricePerSymbol);
                finalResponse.setTotal_sum(withoutDiscount);
            }

            finalResponse.setChannelDtos(channelService.findChannelByIdForDiscount(c.getChannelId()));
            finalResponse.setMessage("Успешно сохранено");
            finalResponse.setAdText("Есть что посмотреть");
        }
        return finalResponse;
    }

    @Override
    public List<Order> getOrder() {

        return orderRepo.findAll();
    }

}
