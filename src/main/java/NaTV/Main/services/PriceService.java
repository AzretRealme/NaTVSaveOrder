package NaTV.Main.services;

import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Price;

import java.util.List;

public interface PriceService{
    List<Price> priceChannels();
    List<PriceDto> allActiveChannelsPrices();

    Price savePrice(Price price);

}
