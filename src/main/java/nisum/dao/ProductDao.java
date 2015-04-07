package nisum.dao;

import nisum.model.Product;
import nisum.model.ProductType;

import java.util.List;

public interface ProductDao {

    public List<Product> getAll();

    public Product findById(int id);

    public int save(Product product);

    public boolean update(Product product);

    public boolean delete(Product product);
}
