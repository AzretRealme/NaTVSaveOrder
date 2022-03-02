package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderDetailRepo;
import NaTV.Main.dao.OrderRepo;
import NaTV.Main.enums.OrderStatus;
import NaTV.Main.mappers.OrderDetailMapper;
import NaTV.Main.mappers.OrderMapper;
import NaTV.Main.models.dto.*;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Order;
import NaTV.Main.models.objects.objectOrders.ChannelOrder;
import NaTV.Main.models.objects.objectOrders.ChannelOrderResponse;
import NaTV.Main.models.objects.objectOrders.FinalResponse;
import NaTV.Main.models.objects.objectOrders.OrderData;
import NaTV.Main.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private PriceService priceService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private OrderDayService orderDayService;
    @Autowired
    private OrderService orderService;



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

        double totalSum;
        List<ChannelOrder> channelOrders = orderData.getChannels();
        for (ChannelOrder c : channelOrders) {
            OrderDetailDto orderDetailDto = new OrderDetailDto();
            orderDetailDto.setOrder(orderDto1);
            orderDetailDto.setChannel(channelService.findChannelByIdForDiscount(c.getChannelId()));
            //get size
            int days = c.getDays().size();
            //находим minDay с помощью метода findByMinDay
            DiscountDto discountDto = discountService.findByMinDay(days, c.getChannelId());
            //find priceDto id
            PriceDto priceDto = priceService.findByChannelAndDate(c.getChannelId());
            double pricePerSymbol = priceDto.getPrice();
            int sizeAdText = textLength.length();//длина текста
            OrderDetailDto orderDetailDto1;
            if (discountDto != null) {
                double percent = discountDto.getPercent();
                double withoutDiscount = pricePerSymbol * sizeAdText;
                double discountInSum = withoutDiscount * percent / 100;
                double sumForChannel = (withoutDiscount - discountInSum) * days;
                finalResponse.setPrice(pricePerSymbol);
                finalResponse.setTotal_sum(sumForChannel);
//                orderDetailDto.setPrice(sumForChannel);
//                orderDetailDto1 = orderDetailService.save(orderDetailMapper.toOrderDetail(orderDetailDto));
            } else {
                double withoutDiscount = (pricePerSymbol * sizeAdText) * days;
                finalResponse.setPrice(pricePerSymbol);
                finalResponse.setTotal_sum(withoutDiscount);

            }
            finalResponse.setChannelDtos(channelService.findChannelByIdForDiscount(c.getChannelId()));
            finalResponse.setMessage("Успешно сохранено");
            finalResponse.setAdText("Есть что посмотреть");
//            List<Date> dates = c.getDays(); //выводим дату и сохраняем
//            dates.forEach(x -> {
//                orderDayService.saveOrderDay(x, orderDetailDto1);
//            });
//            List<OrderDetailDto> orderDetailDtos = orderDetailService.findAllByOrder(orderDto1);
//            totalSum = orderDetailDtos.stream()   //выводим общую сумму всех каналов
//                    .mapToDouble(x -> x.getPrice())
//                    .sum();
//            orderDto1.setTotal_Price(totalSum); //сохраняем общую сумму в Entity
//            Order order1 = orderRepo.save(orderMapper.toOrder(orderDto1));
//            finalResponse.setTotal_sum(totalSum); //сохраняем общую сумму в finalResponse
//            //ищем order по Id с помощью метода findByOrderId
//            List<OrderDetailDto> orderDetailDtoList = orderDetailService.findByOrderId(order1.getId());
//            List<ChannelOrderResponse> channelOrderResponseList = new ArrayList<>();
//            for (OrderDetailDto o : orderDetailDtoList) {
//                ChannelOrderResponse channelOrderResponse = new ChannelOrderResponse();
//                channelOrderResponse.setCostPerChannel(o.getPrice());
//                ChannelDto channelDto = channelService.findChannelByIdForDiscount(o.getChannel().getId());
//                channelOrderResponse.setChannelName(channelDto.getChannelName());
//                List<OrderDayDto> orderDayDtos = orderDayService.findByOrderDetailId(o.getId());
//                List<Date> dates1 = new ArrayList<>();
//                for (OrderDayDto od : orderDayDtos) {
//                    Date date;
//                    date = od.getDay();
//                    dates1.add(date);
//                }
//                channelOrderResponse.setDates(dates1);
//                channelOrderResponseList.add(channelOrderResponse);
//            }

        }
        return finalResponse;

    }


    @Override
    public List<Order> getOrder() {

        return orderRepo.findAll();
    }

//    @Override
//    public List<OrderDto> allActiveOrders() {
//        return orderMapper.toOrderDtos(orderRepo.allActiveOrders());
//    }


}
