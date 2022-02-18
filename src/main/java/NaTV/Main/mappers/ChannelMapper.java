package NaTV.Main.mappers;


import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.entity.Channel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ChannelMapper {

    ChannelMapper INSTANCE = Mappers.getMapper(ChannelMapper.class);

    Channel toChannel(ChannelDto tvChannelDto);
    ChannelDto toChannelDto(Channel tvChannel);

    List<Channel> toChannels(List<ChannelDto> tvChannelDtos);
    List<ChannelDto>toChannelDtos(List<Channel> tvChannels);
}
