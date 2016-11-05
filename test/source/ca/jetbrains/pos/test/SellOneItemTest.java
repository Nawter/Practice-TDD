package ca.jetbrains.pos.test;

import org.junit.Ignore;
import org.junit.Test;

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
        public Sale(Display display) {
            this.display = display;
        }
        public void onBarcode(String barcode) {
            if("12345".equals(barcode))
            {
                display.setText("$7.95");
            }
            else if ("54321".equals(barcode))
            {
                display.setText("$12.50");
            }
<<<<<<< HEAD
            else
                display.setText("Product not found for 99999");
=======
>>>>>>> parent of 5f77e55... We can handle not finding a product, but only if its barcode is 99999.

        }
    }
}
