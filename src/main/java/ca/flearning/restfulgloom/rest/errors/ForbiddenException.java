package ca.flearning.restfulgloom.rest.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    // TODO: It's super bad security-wise to return stack traces, but I've done some googling
    // 		and I'm not sure how else to throw a 403 from a REST endpoint.
    // 		maybe the right way is to implement a filter for this endpoint?

	private static final long serialVersionUID = 2075627824298489189L;
	
	private String message;

    public ForbiddenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}