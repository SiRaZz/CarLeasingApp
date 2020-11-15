package DanskeBank.exception;

import DanskeBank.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class SameRuleFoundException extends RuntimeException  {

    public SameRuleFoundException(HttpStatus status, String errorMessage) {
        new ApiError(status, errorMessage);
    }

}
