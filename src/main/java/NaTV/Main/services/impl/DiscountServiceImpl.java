package NaTV.Main.services.impl;

import NaTV.Main.dao.DiscountRepo;
import NaTV.Main.mappers.DiscountMapper;
import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.entity.Discount;
import NaTV.Main.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRepo discountRepo;

    @Override
    public List<Discount> discountChannels() {
        return discountRepo.findAll();
    }

    @Override
    public List<DiscountDto> allActiveChannelDiscounts(Long id) {
        return DiscountMapper.INSTANCE.toDiscountDtos(discountRepo.allActiveChannelDiscounts(id));

    }

    @Override
    public Discount saveDiscount(Discount discount) {
        discount = discountRepo.save(discount);
        return discount;
    }
}
