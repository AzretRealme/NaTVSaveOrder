package NaTV.Main.mappers.impl;


import NaTV.Main.mappers.ChannelMapper;
import NaTV.Main.models.dto.ChannelDto;
import NaTV.Main.models.entity.Channel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChannelMapperImpl implements ChannelMapper {
    @Override
    public Channel toChannel(ChannelDto channelDto) {
        Channel channel = new Channel();
        channel.setId(channelDto.getId());
        channel.setChannelName(channelDto.getChannelName());
        channel.setActive(channelDto.isActive());
        channel.setPhoto(channelDto.getPhoto());
        channel.setOrderNum(channelDto.getOrderNum());
        return channel;
    }

    @Override
    public ChannelDto toChannelDto(Channel channel) {
        ChannelDto channelDto = new ChannelDto();
        channelDto.setId(channel.getId());
        channelDto.setChannelName(channel.getChannelName());
        channelDto.setActive(channel.isActive());
        channelDto.setPhoto(channel.getPhoto());
        channelDto.setOrderNum(channel.getOrderNum());
        return channelDto;
    }

    @Override
    public List<Channel> toChannels(List<ChannelDto> channelDtos) {
        return channelDtos.stream()
                .map(x -> toChannel(x))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChannelDto> toChannelDtos(List<Channel> channels) {
        return channels.stream()
                .map(x -> toChannelDto(x))
                .collect(Collectors.toList());
    }
}
