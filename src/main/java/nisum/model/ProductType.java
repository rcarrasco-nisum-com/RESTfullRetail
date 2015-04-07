package nisum.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Proxy;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product_type")
//@Proxy(lazy = false)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProductType implements java.io.Serializable {

    private int id;
    private String type;
    private String description;
    private Set<Product> products = new HashSet<Product>(0);

    public ProductType() {
    }

    public ProductType(int id) {
        this.id = id;
    }

    public ProductType(int id, String type, String description,
                       Set<Product> products) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.products = products;
    }

    @Id
    @SequenceGenerator(name = "product_type_seq", sequenceName = "product_type_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_type_seq")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "type")
    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "productType")
    @Fetch(FetchMode.JOIN)
    @JsonManagedReference
    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

}
