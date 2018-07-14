package za.co.digitalplatoon.invoiceservice.invoice.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class ObjectHelper {
    public static void map(Object src, Object dest) {
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
