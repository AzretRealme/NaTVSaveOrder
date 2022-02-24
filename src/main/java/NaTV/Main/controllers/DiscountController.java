package NaTV.Main.controllers;

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

    @PutMapping("/save-discount")
    public Discount saveTvChannels(@RequestBody Discount discount){
        return discountService.saveDiscount(discount);
    }
    @GetMapping("/all-discounts")
    public List<Discount> discountChannels(){
        return discountService.discountChannels();
    }

//    @GetMapping("/all-channels")
//    public List<Channel> tvChannels(){
//        return channelService.tvChannels();
//    }

}
