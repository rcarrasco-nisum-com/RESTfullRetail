package nisum.restResources;

import java.util.List;

public class ProductResources {

    private List<ProductResource> products;

    public ProductResources() {
        this.products = null;
    }

    public ProductResources(List<ProductResource> products) {
        this.products = products;
    }

    public List<ProductResource> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResource> products) {
        this.products = products;
    }
}
