package nisum.restResources;

import java.util.List;

public class ProductTypeResources {

    private List<ProductTypeResource> productTypes;

    public ProductTypeResources() {
        this.productTypes = null;
    }

    public ProductTypeResources(List<ProductTypeResource> productTypes) {
        this.productTypes = productTypes;
    }

    public List<ProductTypeResource> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductTypeResource> productTypes) {
        this.productTypes = productTypes;
    }
}
