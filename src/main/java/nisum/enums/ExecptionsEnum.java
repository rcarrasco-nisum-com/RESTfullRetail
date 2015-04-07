package nisum.enums;

public enum ExecptionsEnum {

    PRODUCT_TYPE_ID_NOT_FOUND_EXCEPTION("ProductTypeIdNotFoundException",
            "Does not exist a product type with the id: "),

    PRODUCT_ID_NOT_FOUND_EXCEPTION("ProductIdNotFoundException",
                                                "Does not exist a product with the id: ");

    private final String exceptionName;

    private final String exceptionPhrase;


    private ExecptionsEnum(String exceptionName, String exceptionPhrase) {
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
