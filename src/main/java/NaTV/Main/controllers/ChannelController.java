package NaTV.Main.controllers;

import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.objects.OutputChannel;
import NaTV.Main.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/TvChannel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

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
    public List<OutputChannel>outputTvChannels(){
        return channelService.outputTvChannels();
    }

    @GetMapping("/all-channels")
    public List<Channel> tvChannels(){
        return channelService.tvChannels();
    }

//
//    @GetMapping("/all-outputChannels")
//    public List<OutputChannel> outputChannels(){
//        return channelService.outputChannels();
//    }


}
