package nisum.service;

import nisum.CustomExceptions.ProductTypeIdNotFoundException;
import nisum.dao.ProductTypeDao;
import nisum.enums.ExecptionsEnum;
import nisum.model.ProductType;
import nisum.restResources.ProductTypeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeServiceImpl implements ProductTypeService{

    private ProductTypeDao productTypeDao;

    @Autowired
    public void setProductTypeDao(ProductTypeDao productTypeDao){
        this.productTypeDao = productTypeDao;
    }

    @Override
    @Transactional
    public List<ProductTypeResource> getAll() {

        List<ProductTypeResource>  productTypeResources = new ArrayList<ProductTypeResource>();

        for (ProductType productType : productTypeDao.getAll()) {
            productTypeResources.add(new ProductTypeResource(productType));
        }

        return productTypeResources;

    }

    @Override
    @Transactional
    public ProductTypeResource findById(int id)throws RuntimeException {

        ProductType productType = productTypeDao.findById(id);

        if (productType ==  null) throw  new
                ProductTypeIdNotFoundException(ExecptionsEnum.PRODUCT_TYPE_ID_NOT_FOUND_EXCEPTION
                .getExceptionPhrase()+id);

        return new ProductTypeResource(productType);
    }

    @Override
    @Transactional
    public int save(ProductTypeResource productTypeResource) {

        if(productTypeDao.findById(productTypeResource.getProductTypeId()) == null){
            ProductType productType = new ProductType();

            productType.setType(productTypeResource.getType());
            productType.setDescription(productTypeResource.getDescription());

            return productTypeDao.save(productType);
        }

        return 0;
    }

    @Override
    public boolean update(ProductTypeResource productTypeResource) {

        if(productTypeDao.findById(productTypeResource.getProductTypeId()) == null){
            ProductType productType = new ProductType();

            productType.setId(productTypeResource.getProductTypeId());
            productType.setType(productTypeResource.getType());
            productType.setDescription(productTypeResource.getDescription());

            return productTypeDao.update(productType);
        }

        return false;
    }

    @Override
    @Transactional
    public boolean delete(ProductTypeResource productTypeResource) {

        if(productTypeDao.findById(productTypeResource.getProductTypeId()) == null){
            ProductType productType = new ProductType();

            productType.setId(productTypeResource.getProductTypeId());
            productType.setType(productTypeResource.getType());
            productType.setDescription(productTypeResource.getDescription());

            return productTypeDao.update(productType);
        }

        return false;
    }
}
