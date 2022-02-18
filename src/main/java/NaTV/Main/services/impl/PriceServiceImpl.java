package NaTV.Main.services.impl;

import NaTV.Main.dao.PriceRepo;
import NaTV.Main.mappers.PriceMapper;
import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Price;
import NaTV.Main.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceRepo priceRepo;

    @Override
    public Price savePrice(Price price) {
        price = priceRepo.save(price);
        return price;
    }
    @Override
    public List<Price> priceChannels() {
        return priceRepo.findAll();
    }

    @Override
    public List<PriceDto> allActiveChannelsPrices() {
        return PriceMapper.INSTANCE.toPriceDtos(priceRepo.allActiveChannelsPrices());

    }

}
