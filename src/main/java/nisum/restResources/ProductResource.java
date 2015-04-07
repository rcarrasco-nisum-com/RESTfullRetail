package nisum.restResources;

import nisum.model.Product;
import org.springframework.hateoas.ResourceSupport;

public class ProductResource extends ResourceSupport {

    private int productid;
    private int productTypeId;
    private Float price;
    private String size;
    private String description;

    public ProductResource(Product product) {
        this.productid = product.getId();
        this.productTypeId = product.getProductType().getId();
        this.price = product.getPrice();
        this.size = product.getSize();
        this.description = product.getDescription();
    }

    public ProductResource(ProductIn productIn) {
        this.productTypeId = productIn.getProductTypeId();
        this.price = productIn.getPrice();
        this.size = productIn.getSize();
        this.description = productIn.getDescription();
    }

    public ProductResource(){
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        productid = productid;
    }

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
