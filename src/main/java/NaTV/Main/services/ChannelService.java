package NaTV.Main.services;


import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.objects.OutputChannel;

import java.util.List;

public interface ChannelService {

    List<Channel> tvChannels();
    List<OutputChannel> outputTvChannels();
    Channel saveTvChannel(Channel channel);

}
