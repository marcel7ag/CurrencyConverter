package ch.m450;

import ch.m450.Converter.CurrencyConverter;
import ch.m450.Converter.KursCurrencyConverter;
import ch.m450.api.CurrencyList;

import java.math.BigDecimal;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;

        do {
            System.out.println("\nHallo und willkommen zum Währungkonverter!" +
                    "\n------------------" +
                    "\nWollen Sie die Liste aller auswählbaren Währungen sehen? (ja/nein):");
            String listSelection = scanner.nextLine();
            if (listSelection.equals("ja")) {
                try {
                    CurrencyList.printCurrencies();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            String basisCurrency;
            while (true) {
                System.out.println("\nGeben Sie nun eine Währung ein, welche Sie konvertieren möchten:");
                basisCurrency = scanner.nextLine().toUpperCase();
                if (CurrencyList.isValidCurrency(basisCurrency)) {
                    break;
                } else {
                    System.out.println("Ungültige Währung. Bitte wählen Sie eine gültige Währung aus der Liste.");
                }
            }
            String zielCurrency;
            while (true) {
                System.out.println("\nGeben Sie nun die Zielwährung ein, in welche sie konvertieren wollen:");
                zielCurrency = scanner.nextLine().toUpperCase();
                if (CurrencyList.isValidCurrency(zielCurrency)) {
                    break;
                } else {
                    System.out.println("Ungültige Währung. Bitte wählen Sie eine gültige Währung aus der Liste.");
                }
            }
            System.out.println("\nWollen Sie den Kursverlauf von " +
                    basisCurrency.toUpperCase() + " zu " + zielCurrency.toUpperCase() +
                    " sehen? (ja/nein):");
            String historySelect = scanner.nextLine();
            if (historySelect.equals("ja")) {
                KursCurrencyConverter converter = new KursCurrencyConverter(basisCurrency, zielCurrency, BigDecimal.ZERO);
                try {
                    String history = converter.history();
                    System.out.println(history);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("\nGeben Sie nun die Menge Ihrer Basiswährung (" + basisCurrency.toUpperCase() +
                    ") zum Konvertieren in " + zielCurrency.toUpperCase() + " ein:");
            BigDecimal quantitaet = scanner.nextBigDecimal();
            scanner.nextLine();

            KursCurrencyConverter converter = new KursCurrencyConverter(basisCurrency, zielCurrency, quantitaet);
            try {
                BigDecimal resultat = converter.convert();
                System.out.println(quantitaet + " " + basisCurrency.toUpperCase() + " sind heute " + resultat + " " + zielCurrency.toUpperCase());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("\nMöchten Sie den Währungskonverter erneut verwenden? (ja/nein):");
            userInput = scanner.nextLine();
        } while (userInput.equalsIgnoreCase("ja"));
        System.out.println("Vielen Dank für die Nutzung des Währungskonverters. Auf Wiedersehen!");
    }
}
