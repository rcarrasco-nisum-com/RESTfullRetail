package nisum.controller;

import nisum.CustomExceptions.ProductIdNotFoundException;
import nisum.CustomExceptions.ProductTypeIdNotFoundException;
import nisum.errorResource.ErrorDescription;
import nisum.errorResource.ErrorResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@ControllerAdvice(annotations=RestController.class)
public class RestExceptionHandler //extends ResponseEntityExceptionHandler
{

    @ExceptionHandler(ProductTypeIdNotFoundException.class)
    public @ResponseBody
    ResponseEntity<ErrorResource> handleProductTypeId(Exception e) {

         ErrorResource errorResource = new ErrorResource
                (HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value()
                        ,e.getMessage(),e.getMessage(),"link",null);
        return new ResponseEntity<>(errorResource,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductIdNotFoundException.class)
    public @ResponseBody
    ResponseEntity<ErrorResource> handleProductId(Exception e) {

        ErrorResource errorResource = new ErrorResource
                (HttpStatus.NOT_FOUND,HttpStatus.NOT_FOUND.value()
                        ,e.getMessage(),e.getMessage(),"link",null);
        return new ResponseEntity<>(errorResource,HttpStatus.NOT_FOUND);
    }

    /*
    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody
    ResponseEntity<ErrorResource> handleProductTypeIdNotNull(RuntimeException e,ErrorResource errorResources) {

        ErrorResource errorResource = new ErrorResource
                (HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value()
                        ,e.getMessage(),e.getMessage(),"link",null);
        return new ResponseEntity<>(errorResource,HttpStatus.BAD_REQUEST);
    }*/

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResource> handleHibernateValidation(MethodArgumentNotValidException e){

        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        String errorString = "";
        List<ErrorDescription> errorDescriptionList = new ArrayList<>();

        for (FieldError error : errors) {

            ErrorDescription errorDescription = new ErrorDescription();
            errorDescription.setField(error.getField());
            errorDescription.setMessage(error.getDefaultMessage());
            errorDescription.setRejectedValue(error.getRejectedValue().toString());
            errorDescription.setResourceName(error.getObjectName());

            errorDescriptionList.add(errorDescription);
            errorString = errorString + error.getDefaultMessage();
        }

        ErrorResource errorResource = new ErrorResource
                (HttpStatus.BAD_REQUEST,HttpStatus.BAD_REQUEST.value()
                        ,e.getMessage(),errorString,"link",null);

        errorResource.setErrorList(errorDescriptionList);

        return new ResponseEntity<>(errorResource,HttpStatus.BAD_REQUEST);
    }
}
