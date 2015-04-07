package nisum.dao;

import nisum.model.Product;
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
public class ProductDaoImpl implements ProductDao {

    static Logger LOGGER = LoggerFactory.getLogger(ProductDaoImpl.class);

   @Autowired
   private SessionFactory sessionFactory;

    @Transactional
    @SuppressWarnings("unchecked")
    @Override
    public List<Product> getAll() {

    List<Product> products = new ArrayList<Product>();
        try{
            Session session = getSession();
            Criteria criteria = session.createCriteria(Product.class);
            products = criteria.list();
        }
        catch(HibernateException e){
            LOGGER.error("getAll() :" + e);
        }

        return products;
    }

    @Transactional
    @Override
    public Product findById(int id) {

        Product product = new Product();
        try{
            product = (Product)getSession().createCriteria(Product.class)
                    .add(Restrictions.eq("id", id)).uniqueResult();
        }
        catch(HibernateException e){
            LOGGER.error("findById() :" + e);
        }

        return product;
    }

    @Transactional
    @Override
    public int save(Product product) {

        try{
            getSession().save(product);
        }
        catch(HibernateException e){
            LOGGER.error("save() :" + e);
            return 0;
        }

        return product.getId();
    }

    @Transactional
    @Override
    public boolean update(Product product) {

        try{
            getSession().update(product);
        }
        catch(HibernateException e){
            LOGGER.error("update() :" + e);
            return false;
        }

        return true;
    }

    @Transactional
    @Override
    public boolean delete(Product product) {

        try{

            Object persistentInstance = getSession().load(Product.class, product.getId());
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
