package nisum.utils;

import org.springframework.stereotype.Component;

@Component
public final class ValidationUtil {

    public static boolean isValidInteger(String field){
        try {
            Integer.parseInt(field);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isNotNull(Object object){
        return (object != null);
    }

    public static boolean isNotEmpty(String field){
        return (field != null && field.isEmpty());
    }
}
