package NaTV.Main.controllers;

import NaTV.Main.dao.ChannelRepo;
import NaTV.Main.exceptions.ChannelNotFound;
import NaTV.Main.exceptions.OrderNotFound;
import NaTV.Main.mappers.ChannelMapper;
import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.objects.outputs.OutputChannel;
import NaTV.Main.services.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/TvChannel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private ChannelRepo channelRepo;
    @Autowired
    private ChannelMapper channelMapper;

    @PostMapping("/save-channels")
    public  ResponseEntity<?> save(@RequestBody ChannelDto channelDto) {
        try {
            log.info("Saving channel");
            return ResponseEntity.status(HttpStatus.CREATED).body(channelService.saveTvChannel(channelDto));
        } catch (ChannelNotFound exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
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

