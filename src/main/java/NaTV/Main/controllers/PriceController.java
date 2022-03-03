package NaTV.Main.controllers;

import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Price;
import NaTV.Main.services.PriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @PostMapping("/save-price")
    public PriceDto save(@RequestBody Price price){
            return priceService.savePrice(price);

    }
    @GetMapping("/all-prices")
    public List<Price> priceChannels(){

        return priceService.priceChannels();
    }
}


