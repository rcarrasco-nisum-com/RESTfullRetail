package nisum.enums;

public enum PreconditionMessage{

    PRODUCT_TYPE_ID_MUST_BE_INT("IllegalArgumentException",
            "The id of a product type must to be integer"),

    PRODUCT_ID_MUST_BE_INT("IllegalArgumentException",
            "The id of a product type must to be integer"),

    PRODUCT_MUST_NOT_BE_NULL("IllegalArgumentException",
                                   "A product cant be null"),

    PRODUCT_TYPE_MUST_NOT_BE_NULL("IllegalArgumentException",
                                     "A product type cant be null"),

    PRODUCT_TYPE_DESCRIPTION_MUST_NOT_BE_NULL("IllegalArgumentException",
            "The field description of a product type cant be null or empty"),

    PRODUCT_TYPE_TYPE_MUST_NOT_BE_NULL("IllegalArgumentException",
            "The field type of a product type cant be null or empty");

    private final String exceptionName;

    private final String exceptionPhrase;


    private PreconditionMessage(String exceptionName, String exceptionPhrase) {
        this.exceptionName = exceptionName;
        this.exceptionPhrase = exceptionPhrase;
    }

    public String getExceptionName() {
        return exceptionName;
    }

    public String getExceptionPhrase() {
        return exceptionPhrase;
    }
}
