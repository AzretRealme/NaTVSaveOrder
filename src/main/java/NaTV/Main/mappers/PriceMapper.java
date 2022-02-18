package NaTV.Main.mappers;

import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    Price toPrice(PriceDto priceDto);
    PriceDto toPriceDto(Price price);

    List<Price> toPrices(List<PriceDto> priceDtos);
    List<PriceDto> toPriceDtos(List<Price> prices);
}
