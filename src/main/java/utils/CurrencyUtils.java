package utils;

import java.util.Arrays;
import java.util.Currency;

public class CurrencyUtils {

    private static String[] sundayStartCurrencies = {"AED", "SAR"};

    public static boolean isSundayStartCurrency(Currency currency) {
        return Arrays.asList(sundayStartCurrencies).contains(currency.getCurrencyCode().toUpperCase());
    }

    public static boolean isSundayStartCurrency(String currencyCode) {
        return isSundayStartCurrency(Currency.getInstance(currencyCode));
    }

    public static String[] getSundayStartCurrencies() {
        return sundayStartCurrencies;
    }

}
