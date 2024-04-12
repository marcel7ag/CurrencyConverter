package ch.m450.Converter;

import ch.m450.api.CurrencyAPI;

import java.math.BigDecimal;
import java.util.Scanner;

public class CurrencyConverter {
    protected String basisCurrency;
    protected String zielCurrency;
    protected BigDecimal quantitaet;

    public CurrencyConverter(String basisCurrency, String zielCurrency, BigDecimal quantitaet) {
        this.basisCurrency = basisCurrency;
        this.zielCurrency = zielCurrency;
        this.quantitaet = quantitaet;
    }

    public BigDecimal convert() throws Exception {
        CurrencyAPI currencyAPI = new CurrencyAPI();
        BigDecimal kurs = currencyAPI.KursRateHolen(basisCurrency, zielCurrency);
        return kurs.multiply(quantitaet);
    }
    public String history() throws Exception {
        System.out.println("Von welchem Jahr wollen Sie den Kurs sehen (2001-2024):");
        Scanner scanner = new Scanner(System.in);
        int historyJahr = Integer.parseInt(scanner.nextLine());

        CurrencyAPI currencyAPI = new CurrencyAPI();
        String output = "1 " + basisCurrency.toUpperCase() +
                " in " + zielCurrency.toUpperCase() + " war im Jahr " + historyJahr + " " +
                String.valueOf(currencyAPI.KursVerlauf(basisCurrency, zielCurrency, historyJahr)) + " " + zielCurrency.toUpperCase();
        return output;
    }
}
