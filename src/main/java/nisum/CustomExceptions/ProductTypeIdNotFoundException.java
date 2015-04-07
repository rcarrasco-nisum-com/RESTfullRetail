package nisum.CustomExceptions;

import org.springframework.validation.Errors;

public class ProductTypeIdNotFoundException extends RuntimeException {

    public ProductTypeIdNotFoundException()
    {}

    public ProductTypeIdNotFoundException(String message)
    {
        super(message);
    }

    public ProductTypeIdNotFoundException(Throwable cause)
    {
        super(cause);
    }

    public ProductTypeIdNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ProductTypeIdNotFoundException(String message, Throwable cause,
                           boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
