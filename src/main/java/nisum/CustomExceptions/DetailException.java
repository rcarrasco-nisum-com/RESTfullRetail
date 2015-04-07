package nisum.CustomExceptions;

import nisum.errorResource.ErrorDescription;

public class DetailException extends RuntimeException {

    private ErrorDescription fieldError;

    public DetailException()
    {}

    public DetailException(String message)
    {
        super(message);
    }

    public DetailException(Throwable cause)
    {
        super(cause);
    }

    public DetailException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DetailException(String message, Throwable cause,
                                          boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DetailException(ErrorDescription fieldError)
    {
        this.fieldError = fieldError;
    }

    public DetailException(ErrorDescription fieldError, String message)
    {
        super(message);
        this.fieldError = fieldError;
    }
}
