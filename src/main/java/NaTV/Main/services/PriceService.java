package NaTV.Main.services;

import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Price;

import java.util.List;

public interface PriceService{
    List<Price> priceChannels(); //done
    List<PriceDto> allActiveChannelsPrices(); //done

    PriceDto savePrice(Price price);
    PriceDto findByChannelAndDate(Long id); //done


}
