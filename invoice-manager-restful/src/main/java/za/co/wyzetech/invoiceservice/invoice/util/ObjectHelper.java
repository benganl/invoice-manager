package za.co.wyzetech.invoiceservice.invoice.util;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class ObjectHelper {
    private static final Logger LOG = LoggerFactory.getLogger(ObjectHelper.class);

    public static void map(Object src, Object dest) {
        try {
            BeanUtils.copyProperties(dest, src);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOG.error("Error while copying object properties: ", e);
        }
    }
}
