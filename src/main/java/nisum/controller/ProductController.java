package nisum.controller;

import com.wordnik.swagger.annotations.Api;

import nisum.enums.PreconditionMessage;
import nisum.restResources.ProductIn;
import nisum.restResources.ProductResource;
import nisum.restResources.ProductResources;
import nisum.service.ProductService;
import nisum.utils.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static nisum.utils.ValidationUtil.*;

@Controller
@RequestMapping("/products")
@Api(value = "products-api", description = "products api")
public class ProductController {

    ProductService productService;

    @Autowired
    ValidationUtil validationUtil;

    @Autowired
    public void setProductService(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ProductResources getProductList() throws Exception {
        List<ProductResource> productResourceList = productService.getAll();
        addProductLinks(productResourceList);
        return new ProductResources(productResourceList);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    ProductResource getProductById(@PathVariable("id") String id) throws RuntimeException {

        Assert.isTrue(isValidInteger(id)
                ,PreconditionMessage.PRODUCT_ID_MUST_BE_INT.getExceptionPhrase());

        ProductResource productResource = productService.findById(Integer.parseInt(id));
        addProductLinks(productResource);
        return productResource;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody
    void saveProduct(@RequestBody ProductIn productIn
            ,HttpServletResponse response) throws Exception {

        Assert.notNull(productIn
                ,PreconditionMessage.PRODUCT_MUST_NOT_BE_NULL.getExceptionPhrase());

            int id = productService.save(new ProductResource(productIn));
            String location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .pathSegment("{id}").buildAndExpand(id)
                    .toUriString();
            response.setHeader("Location",location);

    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public @ResponseBody
    void updateProduct(@RequestBody ProductIn productIn) throws IOException {

        if(productIn != null){
            productService.update(new ProductResource(productIn));
        }
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody
    void deleteProductById(@PathVariable("id") int id) throws IOException {

        if(id > 0){
            if(productService.delete(productService.findById(id))) {
                String a = "";
            }
        }
    }

    private void addProductLinks(List<ProductResource> productResourceList) throws RuntimeException {
        if (!CollectionUtils.isEmpty(productResourceList)) {
            for (ProductResource productResource : productResourceList) {
                addProductLinks(productResource);
            }
        }
    }

    private void addProductLinks(ProductResource productResource) throws RuntimeException {
        addSelfLink(productResource);
        addProductTypeLink(productResource);
    }

    private void addSelfLink(ProductResource productResource) throws RuntimeException {
        productResource.add(linkTo(methodOn(ProductController.class)
                .getProductById(Integer.toString(productResource.getProductid()))).withSelfRel());
    }

    private void addProductTypeLink(ProductResource productResource) throws RuntimeException {
        productResource.add(linkTo(methodOn(ProductTypeController.class)
                .getProductTypeById(Integer.toString(productResource.getProductTypeId()))).withRel("ProducType"));
    }
}