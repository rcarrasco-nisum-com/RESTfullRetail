package nisum.dao;

import nisum.model.ProductType;

import java.util.List;

public interface ProductTypeDao {

    public List<ProductType> getAll();

    public ProductType findById(int id);

    public int save(ProductType product);

    public boolean update(ProductType product);

    public boolean delete(ProductType product);
}
