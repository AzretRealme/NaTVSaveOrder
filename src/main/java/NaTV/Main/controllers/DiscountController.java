package NaTV.Main.controllers;

import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.entity.Discount;
import NaTV.Main.services.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/save-discount")
    public DiscountDto save(@RequestBody Discount discount){
            return discountService.saveDiscount(discount);

    }
    @GetMapping("/all-discounts")
    public List<Discount> discountChannels(){
        return discountService.discountChannels();
    }


}


