package ch.m450.Converter;

import java.math.BigDecimal;

public class KursCurrencyConverter extends CurrencyConverter {
    public KursCurrencyConverter(String basisCurrency, String zielCurrency, BigDecimal quantitaet) {
        super(
                basisCurrency, zielCurrency, quantitaet,
                "d8d4b7ecb13e73dd5f554717", "https://v6.exchangerate-api.com/v6/"
        );
    }

}
