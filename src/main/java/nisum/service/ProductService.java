package nisum.service;

import nisum.restResources.ProductResource;

import java.util.List;

public interface ProductService {

    public List<ProductResource> getAll();

    public ProductResource findById(int id);

    public int save(ProductResource product);

    public boolean update(ProductResource product);

    public boolean delete(ProductResource product);
}
