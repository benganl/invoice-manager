package za.co.digitalplatoon.invoiceservice.invoice.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class ObjectHelper {
    public static void map(Object src, Object dest) {
	try {
	    BeanUtils.copyProperties(dest, src);
	} catch (IllegalAccessException | InvocationTargetException e) {
	    e.printStackTrace();
	}
    }
}
