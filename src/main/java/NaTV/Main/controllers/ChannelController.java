package NaTV.Main.controllers;

import NaTV.Main.dao.ChannelRepo;
import NaTV.Main.mappers.ChannelMapper;
import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.objects.outputs.OutputChannel;
import NaTV.Main.services.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/TvChannel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private ChannelRepo channelRepo;
    @Autowired
    private ChannelMapper channelMapper;

    @PostMapping("/save-channels")
    public ChannelDto save(@RequestBody ChannelDto channelDto){
        return channelService.saveTvChannel(channelDto);
}
    @GetMapping("/all-outputChannels")
    public List<OutputChannel>outputTvChannels(@RequestParam int pageLimit){
        return channelService.outputTvChannels(pageLimit);
    }

    @GetMapping("/all-channels")
    public List<Channel> tvChannels(){
        return channelService.tvChannels();
    }



}
