package nisum.errorResource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.Collections;
import java.util.List;

public class ErrorResource extends ResourceSupport {

    private final HttpStatus status;
    private final int code;
    private final String message;
    private final String developerMessage;
    private final String moreInfoUrl;
    private final Throwable throwable;
    private List<ErrorDescription> errorList = Collections.EMPTY_LIST;

    public ErrorResource(HttpStatus status, int code, String message, String developerMessage, String moreInfoUrl, Throwable throwable) {
        if (status == null) {
            throw new NullPointerException("HttpStatus argument cannot be null.");
        }
        this.status = status;
        this.code = code;
        this.message = message;
        this.developerMessage = developerMessage;
        this.moreInfoUrl = moreInfoUrl;
        this.throwable = throwable;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getMoreInfoUrl() {
        return moreInfoUrl;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public List<ErrorDescription> getErrorList() {
        return errorList;
    }

    public List<ErrorDescription> setErrorList(List<ErrorDescription> errorList) {
        return this.errorList = errorList;
    }
}
