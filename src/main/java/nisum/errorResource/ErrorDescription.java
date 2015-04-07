package nisum.errorResource;

public class ErrorDescription {

    private String field;
    private String rejectedValue;
    private String resourceName;
    private String message;

    public ErrorDescription(String field, String rejectedValue, String message, String resourceName) {
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
        this.resourceName = resourceName;
    }

    public ErrorDescription() {

    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getRejectedValue() {
        return rejectedValue;
    }

    public void setRejectedValue(String rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
