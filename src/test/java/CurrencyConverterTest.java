import ch.m450.Converter.KursCurrencyConverter;
import ch.m450.api.CurrencyAPI;
import ch.m450.api.CurrencyList;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {

    @Test
    public void testCurrencyListIsValidCurrency() {
        assertTrue(CurrencyList.isValidCurrency("USD"));
        assertFalse(CurrencyList.isValidCurrency("XYZ"));
    }

    @Test
    public void testCurrencyListPrintCurrencies() {
        // This test would verify if the currencies list is printed correctly.
        // It is more of a visual inspection rather than an automated check.
        CurrencyList.printCurrencies();
    }

    @Test
    public void testInvalidCurrencyConversion() {
        assertThrows(Exception.class, () -> {
            KursCurrencyConverter converter = new KursCurrencyConverter("XYZ", "EUR", new BigDecimal("100"));
            converter.convert();
        });
    }

    @Test
    public void testLargeAmountConversion() throws Exception {
            KursCurrencyConverter converter = new KursCurrencyConverter("EUR", "USD", new BigDecimal("10000000000000"));
            converter.convert();
    }

    @Test
    public void testNegativeAmountConversion() throws Exception {
        KursCurrencyConverter converter = new KursCurrencyConverter("EUR", "USD", new BigDecimal("-100"));
        converter.convert();
    }

    @Test
    public void testHistoryWithInvalidYear() throws Exception {
        assertThrows(Exception.class, () -> {
            CurrencyAPI currencyAPI = new CurrencyAPI("", "https://invalid.url/");
            currencyAPI.KursVerlauf("USD", "EUR", 1999);
        });
    }

}
