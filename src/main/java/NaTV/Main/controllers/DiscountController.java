package NaTV.Main.controllers;

import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Discount;
import NaTV.Main.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/save-discount")
    public DiscountDto saveTvChannels(@RequestBody Discount discount){
        return discountService.saveDiscount(discount);
    }
    @GetMapping("/all-discounts")
    public List<Discount> discountChannels(){
        return discountService.discountChannels();
    }


}
