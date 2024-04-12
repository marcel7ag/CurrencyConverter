package ch.m450.Converter;

import ch.m450.api.CurrencyAPI;

import java.math.BigDecimal;

public class KryptoCurrencyConverter extends CurrencyConverter {
    public KryptoCurrencyConverter(String basisCurrency, String zielCurrency, BigDecimal quantitaet) {
        super(basisCurrency, zielCurrency, quantitaet);
    }

    @Override
    public BigDecimal convert() throws Exception {
        CurrencyAPI currencyAPI = new CurrencyAPI("exchange-rates?","https://api.coinbase.com/v2/");
        BigDecimal kurs = currencyAPI.KryptoKursRate(basisCurrency, zielCurrency);
        return kurs.multiply(quantitaet);
    }

}
