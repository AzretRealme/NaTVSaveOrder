package NaTV.Main.mappers;


import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.entity.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    Channel toChannel(ChannelDto channelDto);
    ChannelDto toChannelDto(Channel channel);

    List<Channel> toChannels(List<ChannelDto> channelDtos);
    List<ChannelDto>toChannelDtos(List<Channel> channels);
}
