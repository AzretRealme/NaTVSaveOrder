package NaTV.Main.controllers;

import NaTV.Main.exceptions.ChannelNotFound;
import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Price;
import NaTV.Main.services.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @PostMapping("/save-price")
    public ResponseEntity<?> save(@RequestBody Price price){
        try {
            log.info("Saving channel");
            return ResponseEntity.status(HttpStatus.CREATED).body(priceService.savePrice(price));
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
    @GetMapping("/all-prices")
    public List<Price> priceChannels(){
        return priceService.priceChannels();
    }
}
