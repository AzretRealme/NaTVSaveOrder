package NaTV.Main.mappers.impl;

import NaTV.Main.mappers.ChannelMapper;
import NaTV.Main.mappers.DiscountMapper;
import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.entity.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiscountMapperImpl implements DiscountMapper {
    @Autowired
    private ChannelMapper channelMapper;

    @Override
    public Discount toDiscount(DiscountDto discountDto) {
        Discount discount = new Discount();
        discount.setId(discountDto.getId());
        discount.setChannel(channelMapper.toChannel(discountDto.getChannel()));
        discount.setPercent(discountDto.getPercent());
        discount.setStartDate(discountDto.getStartDate());
        discount.setEndDate(discountDto.getEndDate());
        discount.setMinDay(discountDto.getMinDay());
        return discount;
    }

    @Override
    public DiscountDto toDiscountDto(Discount discount) {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setId(discount.getId());
        discountDto.setChannel(channelMapper.toChannelDto(discount.getChannel()));
        discountDto.setPercent(discount.getPercent());
        discountDto.setStartDate(discount.getStartDate());
        discountDto.setEndDate(discount.getEndDate());
        discountDto.setMinDay(discount.getMinDay());
        return discountDto;
    }

    @Override
    public List<Discount> toDiscounts(List<DiscountDto> discountDtos) {
        return discountDtos.stream()
                .map(x -> toDiscount(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<DiscountDto> toDiscountDtos(List<Discount> discounts) {
        return discounts.stream()
                .map(x -> toDiscountDto(x))
                .collect(Collectors.toList());
    }
}
