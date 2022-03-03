package NaTV.Main.controllers;

import NaTV.Main.exceptions.ChannelNotFound;
import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.services.ChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/TvChannel")
public class ChannelController {

    private ChannelService channelService;

    @Autowired
    public ChannelController(@Lazy ChannelService channelService) {
        this.channelService = channelService;
    }

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
    public ResponseEntity<?> outputTvChannels(@RequestParam int pageLimit){
        try{
            log.info("Get-Channels");
            return ResponseEntity.status(HttpStatus.CREATED).body(channelService.outputTvChannels(pageLimit));
        }catch (ChannelNotFound exception){
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }catch (Exception exception){
            log.error(exception.getMessage());
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @GetMapping("/all-channels")
    public List<Channel> tvChannels(){
        return channelService.tvChannels();
    }



}
