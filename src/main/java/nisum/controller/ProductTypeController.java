package nisum.controller;

import com.wordnik.swagger.annotations.Api;
import nisum.enums.PreconditionMessage;
import nisum.errorResource.ErrorDescription;
import nisum.errorResource.ErrorResource;
import nisum.restResources.ProductTypeIn;
import nisum.restResources.ProductTypeResource;
import nisum.restResources.ProductTypeResources;
import nisum.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static nisum.utils.ValidationUtil.isValidInteger;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/productTypes")
@Api(value = "productTypes-api", description = "products api")
public class ProductTypeController {

    ProductTypeService productTypeService;

    @Autowired
    public void setProductService(ProductTypeService productTypeService){
        this.productTypeService = productTypeService;
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ProductTypeResources getProductTypeList() throws RuntimeException {
        List<ProductTypeResource> productTypeResourceList = productTypeService.getAll();
        addProductTypeLinks(productTypeResourceList);
        return new ProductTypeResources(productTypeResourceList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ProductTypeResource getProductTypeById(@PathVariable("id") String id) throws RuntimeException {
        Assert.isTrue(isValidInteger(id)
                ,PreconditionMessage.PRODUCT_TYPE_ID_MUST_BE_INT.getExceptionPhrase());

        ProductTypeResource productTypeResource = productTypeService.findById(Integer.parseInt(id));
        addProductTypeLinks(productTypeResource);
        return productTypeResource;
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProductType(@Valid @RequestBody ProductTypeIn productTypeIn
            ,HttpServletResponse response) throws IOException {

        Assert.notNull(productTypeIn
                ,PreconditionMessage.PRODUCT_TYPE_MUST_NOT_BE_NULL.getExceptionPhrase());

        Assert.hasText(productTypeIn.getType()
                ,PreconditionMessage.PRODUCT_TYPE_TYPE_MUST_NOT_BE_NULL.getExceptionPhrase());

        Assert.hasText(productTypeIn.getDescription()
                ,PreconditionMessage.PRODUCT_TYPE_DESCRIPTION_MUST_NOT_BE_NULL.getExceptionPhrase());

            int id = productTypeService.save(new ProductTypeResource(productTypeIn));
            String location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .pathSegment("{id}").buildAndExpand(id)
                    .toUriString();
            response.setHeader("Location",location);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public
    void updateProductType(@RequestBody ProductTypeIn productTypeIn) throws IOException {

        if(productTypeIn != null){
            productTypeService.update(new ProductTypeResource(productTypeIn));
        }
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE, produces = "application/json")
    public
    void deleteProductTypeById(@PathVariable("id") int id) throws Exception {

        if(id > 0){
            if(productTypeService.delete(productTypeService.findById(id))) {
                String a = "";
            }
        }
    }

    private void addProductTypeLinks(List<ProductTypeResource> productTypeResourceList) throws RuntimeException {
        if (!CollectionUtils.isEmpty(productTypeResourceList)) {
            for (ProductTypeResource productTypeResource : productTypeResourceList) {
                addProductTypeLinks(productTypeResource);
            }
        }
    }

    private void addProductTypeLinks(ProductTypeResource productTypeResource) throws RuntimeException {
        addSelfLink(productTypeResource);
    }

    private void addSelfLink(ProductTypeResource productTypeResource) throws RuntimeException {
        productTypeResource.add(linkTo(methodOn(ProductTypeController.class)
                .getProductTypeById(Integer.toString(productTypeResource.getProductTypeId()))).withSelfRel());
    }
}
