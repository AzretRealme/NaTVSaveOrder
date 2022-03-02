package NaTV.Main.services.impl;


import NaTV.Main.dao.ChannelRepo;
import NaTV.Main.dao.DiscountRepo;
import NaTV.Main.dao.PriceRepo;
import NaTV.Main.exceptions.ChannelNotFound;
import NaTV.Main.mappers.ChannelMapper;
import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.dto.DiscountDto;
import NaTV.Main.models.dto.PriceDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.objects.outputs.OutputChannel;
import NaTV.Main.models.objects.outputs.OutputDiscount;
import NaTV.Main.services.ChannelService;
import NaTV.Main.services.DiscountService;
import NaTV.Main.services.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private DiscountRepo discountRepo;
    @Autowired
    private PriceService priceService;
    @Autowired
    private DiscountService discountService;
    @Autowired
    private ChannelRepo channelRepo;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private PriceRepo priceRepo;
    @Autowired
    private ChannelMapper channelMapper;


    @Override
    public List<Channel> tvChannels() {
        return channelRepo.findAll();

    }

    @Override
    public ChannelDto saveTvChannel(ChannelDto channelDto) {
        channelDto.setActive(true);
        ChannelDto lastRowFromDb = findLastRowFromDbForOrderNum();
        if (lastRowFromDb == null){
            channelDto.setOrderNum(1);
            Channel tvChannel = channelRepo.save(channelMapper.toChannel(channelDto));
            return channelMapper.toChannelDto(tvChannel);
        }
        channelDto.setOrderNum(lastRowFromDb.getOrderNum() + 1);
        Channel channel = channelRepo.save(channelMapper.toChannel(channelDto));
        return channelMapper.toChannelDto(channel);

    }

    @Override
    public List<OutputChannel> outputTvChannels(int pageLimit) {
        List<PriceDto> activeChannelPrice = priceService.allActiveChannelsPrices();
        List<PriceDto> activeChannelPriceFilterByActive = activeChannelPrice.stream()
                .filter(x -> x.getChannel().isActive())
                .collect(Collectors.toList());
        List<OutputChannel> outputChannels = new ArrayList<>();
        for (PriceDto p : activeChannelPriceFilterByActive) {
            OutputChannel outputTvChannelData = new OutputChannel();
            outputTvChannelData.setId(p.getChannel().getId());
            outputTvChannelData.setChannelName(p.getChannel().getChannelName());
            outputTvChannelData.setPhoto(p.getChannel().getPhoto());
            outputTvChannelData.setPrice(p.getPrice());
            List<DiscountDto> activeChannelDiscounts = discountService.allActiveChannelDiscounts(p.getChannel().getId());
            List<OutputDiscount> discountDataList = new ArrayList<>();
            for (DiscountDto d : activeChannelDiscounts) {
                OutputDiscount outputDiscount = new OutputDiscount();
                outputDiscount.setPercent(d.getPercent());
                outputDiscount.setMinDay(d.getMinDay());
                discountDataList.add(outputDiscount);
            }
            outputTvChannelData.setDiscounts(discountDataList);
            outputChannels.add(outputTvChannelData);
        }
        return outputChannels.stream()
                .limit(pageLimit)
                .collect(Collectors.toList());
    }

    @Override
    public ChannelDto findChannelByIdForDiscount(Long id) {

        return channelMapper.toChannelDto(channelRepo.findById(id).orElseThrow(()->
                new ChannelNotFound("Канал по такому ID не найден!")));
    }

    private ChannelDto findLastRowFromDbForOrderNum(){
        Channel channel = channelRepo.findLastRow();
        if (channel == null){
            return null;
        }
        return channelMapper.toChannelDto(channel);
    }



}
