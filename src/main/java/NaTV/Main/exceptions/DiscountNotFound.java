package NaTV.Main.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DiscountNotFound extends RuntimeException{
    public DiscountNotFound(String message) {
        super(message);
    }
}
