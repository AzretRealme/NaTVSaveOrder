package NaTV.Main.controllers;

import NaTV.Main.dao.OrderRepo;
import NaTV.Main.models.entity.Order;
import NaTV.Main.models.objects.objectOrders.FinalResponse;
import NaTV.Main.models.objects.objectOrders.OrderData;
import NaTV.Main.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderRepo orderRepo;


    @GetMapping("get-Order")
    public List<Order> getOrder(){
        return orderRepo.findAll();
    }

    @PostMapping("save-OrderData")
    public FinalResponse save(@RequestBody OrderData orderData){
        return orderService.saveOrder(orderData);
    }

}
