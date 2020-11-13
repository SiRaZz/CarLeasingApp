package DanskeBank.exception;

import DanskeBank.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class RuleNotFoundException extends RuntimeException  {

    public RuleNotFoundException(HttpStatus status, String errorMessage) {
        new ApiError(status, errorMessage);
    }

}
