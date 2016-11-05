package ca.jetbrains.pos.test;

import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by zaid on 05/11/2016.
 */
public class SellOneItemTest {
    @Test
    public void productFound() throws Exception {
        final Display display=new Display();
        final Sale sale= new Sale(display);
        sale.onBarcode("12345");
        assertEquals("$7.95",display.getText());
    }

    @Test
    public void anotherProductFound() throws Exception {
        final Display display=new Display();
        final Sale sale= new Sale(display);
        sale.onBarcode("54321");
        assertEquals("$12.50",display.getText());
    }

    @Test
    public void productNotFound() throws Exception {
        final Display display=new Display();
        final Sale sale= new Sale(display);
        sale.onBarcode("99999");
        assertEquals("Product not found for 99999",display.getText());
    }

    @Test
    public void emptyBarcode() throws Exception {
        final Display display=new Display();
        final Sale sale= new Sale(display);
        sale.onBarcode("");
        assertEquals("Scanning error: empty barcode", display.getText());
    }

    public static class Display {

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }

    public static class Sale {
        private Display display;
        private Map<String, String> pricesByBarcode;

        public Sale(Display display) {
            this.display = display;
            this.pricesByBarcode = new HashMap<String, String>() {{
                put("12345","$7.95");
                put("54321","$12.50");
            }};
        }
        public void onBarcode(String barcode) {
            if ("".equals(barcode)){
                display.setText("Scanning error: empty barcode");
            }
            else
            {
                if (pricesByBarcode.containsKey(barcode)) {
                    display.setText(pricesByBarcode.get(barcode));
                } else
                    display.setText("Product not found for " +
                            barcode);
            }

        }
    }
}
