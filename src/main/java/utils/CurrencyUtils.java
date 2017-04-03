package utils;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

public class CurrencyUtils {

    private static List<String> sundayStartCurrencies = Arrays.asList("AED", "SAR");

    public static boolean isSundayStartCurrency(Currency currency) {
        return getSundayStartCurrencies().contains(currency.getCurrencyCode().toUpperCase());
    }

    public static boolean isSundayStartCurrency(String currencyCode) {
        return isSundayStartCurrency(Currency.getInstance(currencyCode));
    }

    public static List<String> getSundayStartCurrencies() {
        return sundayStartCurrencies;
    }

}
