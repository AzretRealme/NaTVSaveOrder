package NaTV.Main.services.impl;

import NaTV.Main.dao.PriceRepo;
import NaTV.Main.mappers.PriceMapper;
import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Price;
import NaTV.Main.services.ChannelService;
import NaTV.Main.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PriceServiceImpl implements PriceService {
    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private PriceMapper priceMapper;
    @Autowired
    private PriceService priceService;
    @Autowired
    private ChannelService channelService;

    @Override
    public PriceDto savePrice(Price price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setChannel(channelService.findChannelByIdForDiscount(price.getId()));
        priceDto.setPrice(price.getPrice());
        priceDto.setStartDate(price.getStartDate());
        priceDto.setEndDate(price.getEndDate());
        return priceMapper.toPriceDto(priceRepo.save(priceMapper.toPrice(priceDto)));
    }

    @Override
    public PriceDto findByChannelAndDate(Long id) {
        return priceMapper.toPriceDto(priceRepo.findByChannelAndDate(id));
    }

    @Override
    public List<Price> priceChannels() {
        return priceRepo.findAll();
    }

    @Override
    public List<PriceDto> allActiveChannelsPrices() {
        return priceMapper.toPriceDtos(priceRepo.allActiveChannelsPrices());
    }

}
