
import nisum.dao.ProductDao;
import nisum.model.Product;
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
@TransactionConfiguration(defaultRollback=true,transactionManager="transactionManager")
@ContextConfiguration("file:src/main/webapp/WEB-INF/test-mvc-servlet.xml")
public class ProductDaoTest {

    static Logger LOGGER = LoggerFactory.getLogger(ProductDaoTest.class);

    @Autowired
    private ProductDao productDao;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void saveProduct(){

        LOGGER.debug("saveProduct()");

        Product newProduct = createNewProduct();

        Assert.assertNotNull(productDao.save(newProduct));
        Assert.assertNotNull(productDao.findById(newProduct.getId()));
    }


    @Test
    public void getProductById(){

        LOGGER.debug("getProductById()");

        Product existingProductproduct = createExistingProduct();

        Assert.assertEquals(existingProductproduct.getId(),
                productDao.findById(existingProductproduct.getId()).getId());
    }

    @Test
    public void findAll(){

        LOGGER.debug("findAll()");

        /* In the future use an inmemory db */
        Assert.assertNotNull(productDao.getAll());
    }

    @Test
    public void update(){

        LOGGER.debug("update()");

        Product UpdatedProduct = createUpdateProduct();

        Assert.assertEquals(true, productDao.update(UpdatedProduct));
        Assert.assertEquals(UpdatedProduct.getDescription(),
                productDao.findById(UpdatedProduct.getId()).getDescription());
        Assert.assertEquals(UpdatedProduct.getSize(),
                productDao.findById(UpdatedProduct.getId()).getSize());
        Assert.assertEquals(UpdatedProduct.getPrice()
                ,productDao.findById(UpdatedProduct.getId()).getPrice());
    }

    @Test
    public void delete(){

        LOGGER.debug("delete()");

        Product existingProduct = createExistingProduct();

        Assert.assertEquals(true, productDao.delete(existingProduct));
        Assert.assertNull(productDao.findById(existingProduct.getId()));
    }

    public Product createNewProduct(){

        ProductType productType = new ProductType();
        productType.setId(11);

        Product product = new Product();
        product.setSize("XXL");
        product.setDescription("");
        product.setPrice((float) 333);
        product.setProductType(productType);

        return product;
    }

    public Product createExistingProduct(){

        ProductType productType = new ProductType();
        productType.setId(7);

        Product product = new Product();
        product.setId(18);
        product.setSize("M");
        product.setDescription("");
        product.setPrice((float) 170);
        product.setProductType(productType);

        return product;
    }

    public Product createUpdateProduct(){

        ProductType productType = new ProductType();
        productType.setId(7);

        Product product = new Product();
        product.setId(18);
        product.setSize("S");
        product.setDescription("UPDATED");
        product.setPrice((float) 10);
        product.setProductType(productType);

        return product;
    }

}
