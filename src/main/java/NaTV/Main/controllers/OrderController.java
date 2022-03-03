package NaTV.Main.controllers;

import NaTV.Main.dao.OrderRepo;
import NaTV.Main.exceptions.OrderNotFound;
import NaTV.Main.models.entity.Order;
import NaTV.Main.models.objects.objectOrders.OrderData;
import NaTV.Main.services.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    private OrderService orderService;
    private OrderRepo orderRepo;

    @Autowired
    public OrderController(@Lazy OrderService orderService, OrderRepo orderRepo) {
        this.orderService = orderService;
        this.orderRepo = orderRepo;
    }

    @GetMapping("get-Order")
    public List<Order> getOrder() {

        return orderRepo.findAll();
    }

    @PostMapping("save-OrderData")
    public ResponseEntity<?> save(@RequestBody OrderData orderData) {
        try {
            log.info("Saving order");
            return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(orderData));
        } catch (OrderNotFound exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
}
