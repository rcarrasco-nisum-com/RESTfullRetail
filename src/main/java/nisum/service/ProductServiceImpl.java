package nisum.service;

import nisum.CustomExceptions.ProductIdNotFoundException;
import nisum.dao.ProductDao;
import nisum.enums.ExecptionsEnum;
import nisum.model.Product;
import nisum.model.ProductType;
import nisum.restResources.ProductResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao;

    @Autowired
    public void setProductDao(ProductDao productDao){
        this.productDao = productDao;
    }

    @Override
    @Transactional
    public List<ProductResource> getAll() {

        List<ProductResource>  productResources = new ArrayList<ProductResource>();

        for (Product product : productDao.getAll()) {
            productResources.add(new ProductResource(product));
        }

        return productResources;
    }

    @Override
    @Transactional
    public ProductResource findById(int id) {

        Product product = productDao.findById(id);

        if (product ==  null) throw  new
                ProductIdNotFoundException(ExecptionsEnum.PRODUCT_ID_NOT_FOUND_EXCEPTION
                .getExceptionPhrase()+id);

        return new ProductResource(product);
    }

    @Override
    @Transactional
    public int save(ProductResource productResource) {

       if( productDao.findById(productResource.getProductid()) == null){
           ProductType productType = new ProductType();
           productType.setId(productResource.getProductTypeId());

           Product product = new Product();

           product.setPrice(productResource.getPrice());
           product.setSize(productResource.getSize());
           product.setDescription(productResource.getDescription());
           product.setProductType(productType);

           return productDao.save(product);
       }

        return 0;
    }

    @Override
    @Transactional
    public boolean update(ProductResource productResource) {

        if(productDao.findById(productResource.getProductid()) != null){
            ProductType productType = new ProductType();
            productType.setId(productResource.getProductTypeId());

            Product product = new Product();

            product.setId(productResource.getProductid());
            product.setPrice(productResource.getPrice());
            product.setSize(productResource.getSize());
            product.setDescription(productResource.getDescription());
            product.setProductType(productType);

            return productDao.update(product);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(ProductResource productResource) {

        if(productDao.findById(productResource.getProductid()) != null){
            ProductType productType = new ProductType();
            productType.setId(productResource.getProductTypeId());

            Product product = new Product();

            product.setId(productResource.getProductid());
            product.setPrice(productResource.getPrice());
            product.setSize(productResource.getSize());
            product.setDescription(productResource.getDescription());
            product.setProductType(productType);

            return productDao.delete(product);
        }

        return false;
    }
}
