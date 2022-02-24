package NaTV.Main.services;


import NaTV.Main.models.entity.Channel;
import NaTV.Main.models.objects.OutputChannel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ChannelService {

    List<Channel> tvChannels();
    List<OutputChannel> outputTvChannels(int pageLimit);
    Channel saveTvChannel(Channel channel);

}
