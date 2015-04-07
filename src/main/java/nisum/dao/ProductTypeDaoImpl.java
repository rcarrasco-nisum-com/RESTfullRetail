package nisum.dao;

import nisum.model.Product;
import nisum.model.ProductType;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
public class ProductTypeDaoImpl implements ProductTypeDao {

    static Logger LOGGER = LoggerFactory.getLogger(ProductTypeDaoImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<ProductType> getAll() {

        List<ProductType> productTypes = new ArrayList<ProductType>();
        try{
            Session session = getSession();
            Criteria criteria = session.createCriteria(ProductType.class);
            productTypes = criteria.list();
        }
        catch(HibernateException e){
            LOGGER.error("getAll() :" + e);
        }

        return productTypes;
    }

    @Override
    public ProductType findById(int id) {

        ProductType productType = new ProductType();
        try{
            productType = (ProductType)getSession().createCriteria(ProductType.class)
                    .add(Restrictions.eq("id", id)).uniqueResult();
        }
        catch(HibernateException e){
            LOGGER.error("findById() :" + e);
        }

        return productType;
    }

    @Override
    public int save(ProductType productType) {

        try{
            getSession().save(productType);
        }
        catch(HibernateException e){
            LOGGER.error("save() :" + e);
            return 0;
        }

        return productType.getId();
    }

    @Override
    public boolean update(ProductType productType) {

        try{
            getSession().update(productType);
        }
        catch(HibernateException e){
            LOGGER.error("update() :" + e);
            return false;
        }

        return true;
    }

    @Override
    public boolean delete(ProductType productType) {

        try{

            Object persistentInstance = getSession().load(ProductType.class, productType.getId());
            if (persistentInstance != null) {
                getSession().delete(persistentInstance);
            }

        }
        catch(HibernateException e){
            LOGGER.error("delete() :" + e);
            return false;
        }

        return true;
    }

    private Session getSession(){

        return this.sessionFactory.getCurrentSession();
    }
}
