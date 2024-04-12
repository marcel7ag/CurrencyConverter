package ch.m450.Converter;

import java.math.BigDecimal;

public class KursCurrencyConverter extends CurrencyConverter {
    public KursCurrencyConverter(String basisCurrency, String zielCurrency, BigDecimal quantitaet) {
        super(basisCurrency, zielCurrency, quantitaet);
    }

}
