package NaTV.Main.mappers.impl;


import NaTV.Main.mappers.ChannelMapper;
import NaTV.Main.mappers.PriceMapper;
import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceMapperImpl implements PriceMapper {
    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public Price toPrice(PriceDto priceDto) {
        Price price = new Price();
        price.setPrice(priceDto.getPrice());
        price.setChannel(channelMapper.toChannel(priceDto.getChannel()));
        price.setEndDate(priceDto.getEndDate());
        price.setId(priceDto.getId());
        price.setStartDate(priceDto.getStartDate());
        return price;
    }

    @Override
    public PriceDto toPriceDto(Price price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(price.getPrice());
        priceDto.setChannel(channelMapper.toChannelDto(price.getChannel()));
        priceDto.setEndDate(price.getEndDate());
        priceDto.setId(price.getId());
        priceDto.setStartDate(price.getStartDate());
        return priceDto;
    }

    @Override
    public List<Price> toPrices(List<PriceDto> priceDtos) {
        return priceDtos.stream()
                .map(x -> toPrice(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<PriceDto> toPriceDtos(List<Price> prices) {
        return prices.stream()
                .map(x -> toPriceDto(x))
                .collect(Collectors.toList());
    }
}
