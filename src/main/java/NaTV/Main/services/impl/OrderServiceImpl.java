package NaTV.Main.services.impl;

import NaTV.Main.dao.OrderDetailRepo;
import NaTV.Main.dao.OrderRepo;
import NaTV.Main.enums.OrderStatus;
import NaTV.Main.mappers.OrderDetailMapper;
import NaTV.Main.mappers.OrderMapper;
import NaTV.Main.models.dto.*;
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

            //находим каналы по Id (findChannelByIdForDiscount)
            orderDetailDto.setChannel(channelService.findChannelByIdForDiscount(c.getChannelId()));

            //get size
            int days = c.getDays().size();

            //находим minDay с помощью метода findByMinDay
            DiscountDto discountDto = discountService.findByMinDay(days, c.getChannelId());

            //find priceDto id
            PriceDto priceDto = priceService.findByChannelAndDate(c.getChannelId());

            double pricePerSymbol = priceDto.getPrice();

            //длина текста
            int sizeAdText = textLength.length();

            OrderDetailDto orderDetailDto1;


            if (discountDto != null) {
                double percent = discountDto.getPercent();
                double withoutDiscount = pricePerSymbol * sizeAdText;
                double discountInSum = withoutDiscount * percent / 100;
                double sumForChannel = (withoutDiscount - discountInSum) * days;
                //выводим цену на каждый канал
                orderDetailDto.setPrice(sumForChannel);
                orderDetailDto1 = orderDetailService.save(orderDetailMapper.toOrderDetail(orderDetailDto));
            } else {
                double withoutDiscount = (pricePerSymbol * sizeAdText) * days;
                //выводим цену на каждый канал
                orderDetailDto.setPrice(withoutDiscount);
                orderDetailDto1 = orderDetailService.save(orderDetailMapper.toOrderDetail(orderDetailDto));
            }

            //выводим дату и сохраняем
            List<Date> dates = c.getDays();
            dates.forEach(x -> {
                orderDayService.saveOrderDay(x, orderDetailDto1);
            });

        }

         List<OrderDetailDto> orderDetailDtos = orderDetailService.findAllByOrder(orderDto1);

            //выводим общую сумму всех каналов
            totalSum = orderDetailDtos.stream()
                    .mapToDouble(x -> x.getPrice())
                    .sum();
            //сохраняем общую сумму в Entity
            orderDto1.setTotal_Price(totalSum);
            Order order1 = orderRepo.save(orderMapper.toOrder(orderDto1));
            //сохраняем общую сумму в finalResponse
        finalResponse.setTotalSum(totalSum);

            //ищем orderDetailDto по Id с помощью метода findByOrderId
            List<OrderDetailDto> orderDetailDtoList = orderDetailService.findByOrderId(order1.getId());
            List<ChannelOrderResponse> channelOrderResponseList = new ArrayList<>();
            for (OrderDetailDto o : orderDetailDtoList) {
                ChannelOrderResponse channelOrderResponse = new ChannelOrderResponse();

                //получаем price на каждый канал
                channelOrderResponse.setCostPerChannel(o.getPrice());

                //ищем канал по Id
                ChannelDto channelDto = channelService.findChannelByIdForDiscount(o.getChannel().getId());
                channelOrderResponse.setChannelName(channelDto.getChannelName());

                //ищем orderDayDto по Id
                List<OrderDayDto> orderDayDtos = orderDayService.findByOrderDetailId(o.getId());
                List<Date> dates1 = new ArrayList<>();
                for (OrderDayDto od : orderDayDtos) {
                    Date date;
                    date = od.getDay();
                    dates1.add(date);
                }
                channelOrderResponse.setDates(dates1);
                channelOrderResponseList.add(channelOrderResponse);
            }
            finalResponse.setChannelOrders(channelOrderResponseList);
            finalResponse.setOrderStatus(OrderStatus.ACTIVE);
            finalResponse.setMessage("Успешно сохранено");
            finalResponse.setAdText("Есть что посмотреть");

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
