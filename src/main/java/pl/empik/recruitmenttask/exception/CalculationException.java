package pl.empik.recruitmenttask.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@ResponseBody
public class CalculationException extends RuntimeException {
    public CalculationException() {
        super();
    }

    public CalculationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculationException(String message) {
        super(message);
    }

    public CalculationException(Throwable cause) {
        super(cause);
    }
}
