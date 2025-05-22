package mph.parser;
import mph.annotation.FixedFieldAnnotations;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
public class FixedWidthParser {
    public static <T> T parseLine(String line, Class<T> clazz) throws Exception {
        List<Object> fieldValues=new ArrayList<>();
        int currentIndex = 0;
        for (Field field : clazz.getDeclaredFields()) {
            FixedFieldAnnotations annotation = field.getAnnotation(FixedFieldAnnotations.class);
            if (annotation != null) {
                int length = annotation.length();
                if (currentIndex + length > line.length()) {
                    throw new Exception("Line too short for field: " + field.getName());
                }

                String value = line.substring(currentIndex, currentIndex + length).trim();
                currentIndex += length;
                //System.out.println(value);
                if (annotation.required() && value.isEmpty()) {
                    throw new Exception("Missing required field: " + field.getName());
                }

                if (!annotation.allowedValues().isEmpty()) {
                    String[] allowed = annotation.allowedValues().split(",");
                    boolean match = false;
                    for (String a : allowed) {
                        if (a.equalsIgnoreCase(value)) {
                            match = true;
                            break;
                        }
                    }
                    if (!match) {
                        throw new Exception("Invalid value for field '" + field.getName() + "': " + value);
                    }
                }
                if (!annotation.format().isEmpty() && !value.isEmpty()) {
                    if (!value.matches(annotation.format())) {
                        throw new Exception("Invalid format for field '" + field.getName() + "': " + value);
                    }
                }
                Object convertedValue = convertValue(value,field.getType());
                //System.out.println("Values: "+value+" field: "+field.getType());
                fieldValues.add(convertedValue);
            }
        }
        Class<?>[] paramTypes = new Class[fieldValues.size()];
        for(int i=0;i<fieldValues.size();i++){
            paramTypes[i]=fieldValues.get(i).getClass();
            //System.out.print(paramTypes[i]+" ");
        }

        Constructor<T> constructor = clazz.getDeclaredConstructor(paramTypes);
        return constructor.newInstance(fieldValues.toArray());

    }

    private static Object convertValue(String value, Class<?> targetType){
        if(value==null || value.isEmpty())return null;

        try{
            if(targetType==String.class){
                return value;
            }else if(targetType==Integer.class||targetType==int.class){
                return Integer.parseInt(value);
            }else if(targetType==Long.class||targetType==long.class){
                return Long.parseLong(value);
            }else if(targetType==Double.class||targetType==double.class){
                return Double.parseDouble(value);
            }else if(targetType== BigDecimal.class) {
                return new BigDecimal(value);
            }else if(targetType == LocalDate.class) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                return LocalDate.parse(value,formatter);
            }else if(targetType== LocalDateTime.class) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                return LocalDateTime.parse(value,formatter);
            }else if(targetType== LocalTime.class){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                return LocalTime.parse(value,formatter);
            }
            else if(targetType.isRecord())
            {    return parseLine(value,targetType);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to convert value '"+ value+"'to type "+ targetType.getSimpleName(),e);
        }

        return value;
    }
}
