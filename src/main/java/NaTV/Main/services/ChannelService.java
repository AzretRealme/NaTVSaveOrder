package NaTV.Main.services;


import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.objects.outputs.OutputChannel;

import java.util.List;

public interface ChannelService {

    List<Channel> tvChannels();
    List<OutputChannel> outputTvChannels(int pageLimit);
    ChannelDto saveTvChannel(ChannelDto channelDto);
    ChannelDto findChannelByIdForDiscount(Long id);

}
