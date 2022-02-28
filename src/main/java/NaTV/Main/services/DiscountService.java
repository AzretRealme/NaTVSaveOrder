package NaTV.Main.services;


import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Discount;

import java.util.List;

public interface DiscountService{
    List<Discount> discountChannels();
    List<DiscountDto> allActiveChannelDiscounts(Long id);


    DiscountDto saveDiscount(Discount discount);
    DiscountDto findByMinDay(int days, Long id);



}
