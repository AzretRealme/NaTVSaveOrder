package NaTV.Main.controllers;

import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Price;
import NaTV.Main.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @PutMapping("/save-price")
    public Price savePrice(@RequestBody Price price){
        return priceService.savePrice(price);
    }
    @GetMapping("/all-prices")
    public List<Price> priceChannels(){
        return priceService.priceChannels();
    }
}
