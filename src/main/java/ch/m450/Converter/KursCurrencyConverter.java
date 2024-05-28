package ch.m450.Converter;

import java.math.BigDecimal;

public class KursCurrencyConverter extends CurrencyConverter {
    public KursCurrencyConverter(String basisCurrency, String zielCurrency, BigDecimal quantitaet) {
        super(
                basisCurrency, zielCurrency, quantitaet,
                "a1a84b0482ebd77c44c258ad", "https://v6.exchangerate-api.com/v6/"
        );
    }

}
