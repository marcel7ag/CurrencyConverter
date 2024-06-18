package ch.m450.api;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;

public class CurrencyAPI {
    private static String API_KEY;
    private static String API_BASE_URL;

    public CurrencyAPI() {
    }

    public CurrencyAPI(String key, String apiUrl) {
        this.API_KEY = key;
        this.API_BASE_URL = apiUrl;
    }

    public BigDecimal KursRateHolen(String basisCurrency, String zielCurrency) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String urlString = API_BASE_URL + API_KEY + "/latest/" + basisCurrency.toUpperCase();

        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String stringResponse = response.body().string();
            JSONObject jsonObject = new JSONObject(stringResponse);
            JSONObject kursObject = jsonObject.getJSONObject("conversion_rates");
            return kursObject.getBigDecimal(zielCurrency.toUpperCase());
        }
    }

    /*
     * Kryptowährungskurs holen mit API, nicht fertig und falls wirs nicht brauchen, werden wirs nicht verwenden.
     */
    public BigDecimal KryptoKursRate(String basisCurrency, String zielCurrency) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String urlString = API_BASE_URL + API_KEY + "currency=" + basisCurrency.toUpperCase();

        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String stringResponse = response.body().string();
            JSONObject jsonObject = new JSONObject(stringResponse);
            JSONObject kursObject = jsonObject.getJSONObject("rates");
            return kursObject.getBigDecimal(zielCurrency.toUpperCase());
        }
    }


    public BigDecimal KursVerlauf(String basisCurrency, String zielCurrency, int Jahr) throws Exception {
        OkHttpClient client = new OkHttpClient();
        String urlString = API_BASE_URL + API_KEY + "/history/" + basisCurrency.toUpperCase() + "/" + Jahr + "/1/1";
        Request request = new Request.Builder()
                .url(urlString)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String stringResponse = response.body().string();
            JSONObject jsonObject = new JSONObject(stringResponse);
            JSONObject kursObject = jsonObject.getJSONObject("conversion_rates");
            return kursObject.getBigDecimal(zielCurrency.toUpperCase());
        }
    }


}

