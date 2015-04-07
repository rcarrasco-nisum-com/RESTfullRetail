import nisum.dao.ProductTypeDao;
import nisum.model.ProductType;
import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-mvc-servlet.xml")
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
public class ProductTypeDaoTest {

    static Logger LOGGER = LoggerFactory.getLogger(ProductTypeDaoTest.class);

    @Autowired
    private ProductTypeDao productTypeDao;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void testSave(){

        LOGGER.debug("testSave()");

        ProductType productType = createNewProductType();

        Assert.assertNotNull(productTypeDao.save(productType));
        Assert.assertNotNull(productTypeDao.findById(productType.getId()));
    }

    @Test
    public void testFindById(){

        LOGGER.debug("testFindById()");

        ProductType existingProductType = createExistingProductType();

        Assert.assertEquals(existingProductType.getId(),
                productTypeDao.findById(existingProductType.getId()).getId());
    }

    @Test
    public void testFindAll(){

        LOGGER.debug("testFindAll()");

        /* In the future use an inmemory db */
        Assert.assertNotNull(productTypeDao.getAll());
    }

    @Test
    public void testUpdate(){

        LOGGER.debug("testUpdate()");

        ProductType UpdatedProductType = createUpdateProductType();

        Assert.assertEquals(true, productTypeDao.update(UpdatedProductType));
        Assert.assertEquals(UpdatedProductType.getDescription(),
                productTypeDao.findById(UpdatedProductType.getId()).getDescription());
        Assert.assertEquals(UpdatedProductType.getDescription(),
                productTypeDao.findById(UpdatedProductType.getId()).getDescription());
        Assert.assertEquals(UpdatedProductType.getType()
                ,productTypeDao.findById(UpdatedProductType.getId()).getType());
    }

    @Test
    public void testDelete(){

        LOGGER.debug("testDelete()");

        ProductType existingProductType = createUpdateProductType();

        Assert.assertEquals(true, productTypeDao.delete(existingProductType));
        Assert.assertNull(productTypeDao.findById(existingProductType.getId()));
    }

    public ProductType createNewProductType(){

        ProductType productType = new ProductType();
        productType.setDescription("cup hat");
        productType.setType("hat");

        return productType;
    }

    public ProductType createExistingProductType(){

        ProductType productType = new ProductType();
        productType.setId(3);
        productType.setDescription("belt");
        productType.setType("big belt");

        return productType;
    }

    public ProductType createUpdateProductType(){

        ProductType productType = new ProductType();
        productType.setId(3);
        productType.setDescription("leather belt");
        productType.setType("belt");

        return productType;
    }

}
