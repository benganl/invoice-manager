package za.co.digitalplatoon.invoiceservice.invoice.domain;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.digitalplatoon.invoiceservice.invoice.rest.InvoiceDto;
import za.co.digitalplatoon.invoiceservice.invoice.rest.LineItemDto;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDtoTest {
    private static final Logger LOG = LoggerFactory.getLogger(InvoiceDto.class);

    @Test
    public void generateInvoiceJson() {
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        InvoiceDto dto = new InvoiceDto();
        List<LineItemDto> lineItems = new ArrayList<LineItemDto>() {
            {
                add(new LineItemDto());
                add(new LineItemDto());
                add(new LineItemDto());
            }
        };

        dto.setLineItems(lineItems);

        String jsonString = gson.toJson(dto);
        LOG.info(jsonString);
    }
}
