package ca.flearning.restfulgloom.rest.errors;

public class MalformedRequestException extends RuntimeException {
    // TODO: It's super bad security-wise to return stack traces, but I've done some googling
    // TODO: and I'm not sure how else to throw a 403 from a REST endpoint.
    // TODO: maybe the right way is to implement a filter for this endpoint?

	private static final long serialVersionUID = 2075627829818489189L;

	private String message;

    public MalformedRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}