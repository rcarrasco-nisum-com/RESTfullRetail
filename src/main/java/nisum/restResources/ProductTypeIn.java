package nisum.restResources;


import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.NotBlank;

public class ProductTypeIn {

    @NotEmpty
    private String type;

    @NotBlank
    @Length(min=5, max=10, message="description should be between 5 - 10 charactes")
    private String description;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
