package NaTV.Main.controllers;

import NaTV.Main.exceptions.ChannelNotFound;
import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Discount;
import NaTV.Main.services.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/save-discount")
    public ResponseEntity<?> save(@RequestBody Discount discount){
        try {
            log.info("Saving channel");
            return ResponseEntity.status(HttpStatus.CREATED).body(discountService.saveDiscount(discount));
        } catch (ChannelNotFound exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }
    @GetMapping("/all-discounts")
    public List<Discount> discountChannels(){
        return discountService.discountChannels();
    }


}

