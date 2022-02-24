package NaTV.Main.controllers;

import NaTV.Main.dao.ChannelRepo;
import NaTV.Main.mappers.ChannelMapper;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.objects.OutputChannel;
import NaTV.Main.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/TvChannel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private ChannelRepo channelRepo;
    @Autowired
    private ChannelMapper channelMapper;

//    @PutMapping("/save-discount")
//    public OutputChannel inputTvChannels(@RequestBody InputDiscountData inputDiscountData){
//        return discountService.saveNewDiscountForChannel(inputDiscountData);
//    }
    @PutMapping("/save-discount")
    public Channel saveTvChannels(@RequestBody Channel channel){
    return channelService.saveTvChannel(channel);
}
//
    @GetMapping("/all-outputChannels")
    public List<OutputChannel>outputTvChannels(@RequestParam int pageLimit){
        return channelService.outputTvChannels(pageLimit);


    }

    @GetMapping("/all-channels")
    public List<Channel> tvChannels(){
        return channelService.tvChannels();
    }
//    @GetMapping("/page")
//    public Page<Channel> outputChannelsPage(Pageable pageable){
//        return channelRepo.findAll(pageable);
//    }

//
//    @GetMapping("/all-outputChannels")
//    public List<OutputChannel> outputChannels(){
//        return channelService.outputChannels();
//    }


}
