package NaTV.Main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ChannelNotFound extends RuntimeException{

    public ChannelNotFound(String message) {
        super(message);
    }
}
