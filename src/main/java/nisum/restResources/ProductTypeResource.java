package nisum.restResources;

import nisum.model.ProductType;
import org.springframework.hateoas.ResourceSupport;

public class ProductTypeResource extends ResourceSupport {

    private int productTypeId;
    private String type;
    private String description;

    public ProductTypeResource(ProductType productType) {
        this.productTypeId = productType.getId();
        this.type = productType.getType();
        this.description = productType.getDescription();
    }

    public ProductTypeResource(ProductTypeIn productTypeIn) {
        this.type = productTypeIn.getType();
        this.description = productTypeIn.getDescription();
    }

    public ProductTypeResource() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
