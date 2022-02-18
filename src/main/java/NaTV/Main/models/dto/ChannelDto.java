package NaTV.Main.models.dto;

import lombok.Data;

@Data
public class ChannelDto {
    private Long id;
    private String channelName;
    private String photo;
    private boolean active;
    private int orderNum;
}
