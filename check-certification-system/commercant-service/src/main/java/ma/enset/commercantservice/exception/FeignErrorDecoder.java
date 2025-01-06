package ma.enset.commercantservice.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.status() >= 400 && response.status() <= 499) {
            return new ResponseStatusException(
                    HttpStatus.valueOf(response.status()),
                    "Error calling external service"
            );
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
} 