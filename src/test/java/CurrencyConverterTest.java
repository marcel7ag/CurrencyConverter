import ch.m450.Converter.KursCurrencyConverter;
import ch.m450.api.CurrencyAPI;
import ch.m450.api.CurrencyList;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CurrencyConverterTest {

    @Test
    public void testCurrencyListIsValidCurrency() {
        // true check ob usd eine valide währung ist
        assertTrue(CurrencyList.isValidCurrency("USD"));
        // true check ob usd eine invalide währung ist
        assertFalse(CurrencyList.isValidCurrency("XYZ"));
    }

    @Test
    // testen, ob die methode printCurrencies funktioniert
    public void testCurrencyListPrintCurrencies() {
        CurrencyList.printCurrencies();
    }

    @Test
    public void testValidCurrencyConversion() {
        // test pass wenn keine exception gethrown wird
        assertDoesNotThrow(() -> {
            KursCurrencyConverter converter = new KursCurrencyConverter("USD", "EUR", new BigDecimal("100"));
            converter.convert();
        });
    }

    @Test
    public void testInvalidCurrencyConversion() {
        // test pass wenn eine exception gethrown wird, weil eine währung ungültig ist
        assertThrows(Exception.class, () -> {
            KursCurrencyConverter converter = new KursCurrencyConverter("XYZ", "EUR", new BigDecimal("100"));
            converter.convert();
        });
    }

    @Test
    public void testLargeAmountConversion() throws Exception {
        // konvertierung mit einer sehr grossen eingabe testen
            KursCurrencyConverter converter = new KursCurrencyConverter("EUR", "USD", new BigDecimal("10000000000000000000000000"));
            converter.convert();
    }

    @Test
    public void testNegativeAmountConversion() throws Exception {
        // converter mit negativen
        KursCurrencyConverter converter = new KursCurrencyConverter("EUR", "USD", new BigDecimal("-100"));
        converter.convert();
    }

    @Test
    public void testKursRateHolenSuccess() throws Exception {
        // api aufsetzen
        CurrencyAPI currencyAPI = new CurrencyAPI("a1a84b0482ebd77c44c258ad", "https://v6.exchangerate-api.com/v6/");

        // Test KursRateHolen method mit zwei korrekten währungen
        BigDecimal rateUSD = currencyAPI.KursRateHolen("EUR", "USD");

        // check rateUSD soll nicht null sein und auch BigDecimal
        assertNotNull(rateUSD);
        assertEquals(BigDecimal.class, rateUSD.getClass()); // Ensure it's a BigDecimal
    }

    @Test
    public void testKursRateHolenFailure(){
        // api aufsetzen
        CurrencyAPI currencyAPI = new CurrencyAPI("a1a84b0482ebd77c44c258ad", "https://v6.exchangerate-api.com/v6/");

        // Test KursRateHolen, test pass wenn eine currency ungültig ist
        assertThrows(Exception.class, () -> {
            currencyAPI.KursRateHolen("XYZ", "USD");
        });
    }

}
