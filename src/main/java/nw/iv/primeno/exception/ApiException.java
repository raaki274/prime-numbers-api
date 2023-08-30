package nw.iv.primeno.exception;

@SuppressWarnings("serial")
public class ApiException extends Exception {
	public ApiException(String errorMessage) {
        super(errorMessage);
    }
}
