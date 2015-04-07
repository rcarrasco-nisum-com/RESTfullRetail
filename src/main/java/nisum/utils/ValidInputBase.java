package nisum.utils;

import nisum.CustomExceptions.DetailException;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

public  class ValidInputBase<T> implements ValidInput<T> {

    ValidationUtil validationUtil;


    @Autowired
    public void setValidationUtil(ValidationUtil validationUtil){
        this.validationUtil = validationUtil;
    }


    @Override
    public void isNull(T t) {

        if(validationUtil.isNotNull(t)) throw new
                DetailException(null,"Cant be null" + t.getClass().getSimpleName());
    }

    @Override
    public void isValidInput(T t) {

        for (Field field : t.getClass().getFields()) {
            validationUtil.isNotNull(field.getName());
            validationUtil.isNotEmpty(field.getName());
        }

    }
}
