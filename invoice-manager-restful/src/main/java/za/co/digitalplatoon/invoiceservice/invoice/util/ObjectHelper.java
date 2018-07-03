package za.co.digitalplatoon.invoiceservice.invoice.util;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

public class ObjectHelper {
    public static <S, D> void map(S src, D dest) {
	try {
	    BeanUtils.copyProperties(dest, dest);
	} catch (IllegalAccessException | InvocationTargetException e) {
	    e.printStackTrace();
	}
    }
}
