package NaTV.Main.services.impl;

import NaTV.Main.dao.DiscountRepo;
import NaTV.Main.mappers.DiscountMapper;
import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.entity.Discount;
import NaTV.Main.services.ChannelService;
import NaTV.Main.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscountServiceImpl implements DiscountService {

    private DiscountRepo discountRepo;
    private DiscountMapper discountMapper;
    private ChannelService channelService;

    @Autowired
    @Lazy
    public DiscountServiceImpl(DiscountRepo discountRepo, DiscountMapper discountMapper, ChannelService channelService) {
        this.discountRepo = discountRepo;
        this.discountMapper = discountMapper;
        this.channelService = channelService;
    }

    @Override
    public List<Discount> discountChannels() {
        return discountRepo.findAll();
    }

    @Override
    public List<DiscountDto> allActiveChannelDiscounts(Long id) {
        return discountMapper.toDiscountDtos(discountRepo.allActiveChannelDiscounts(id));

    }

    @Override
    public DiscountDto saveDiscount(Discount discount) {
        DiscountDto discountDto = new DiscountDto();
        discountDto.setChannel(channelService.findChannelByIdForDiscount(discount.getId()));
        discountDto.setMinDay(discount.getMinDay());
        discountDto.setPercent(discount.getPercent());
        discountDto.setStartDate(discount.getStartDate());
        discountDto.setEndDate(discount.getEndDate());
        return discountMapper.toDiscountDto(discountRepo.save(discountMapper.toDiscount(discountDto)));
    }

    @Override
    public DiscountDto findByMinDay(int days, Long id) {

        Discount discount = discountRepo.findByMinDay(days, id);
        if (discount != null){
            return discountMapper.toDiscountDto(discount);
        }else {
            return null;
        }
    }
}
