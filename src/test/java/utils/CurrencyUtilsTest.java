package utils;


import org.junit.Test;

import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyUtilsTest {

    @Test
    public void testAedIsSundayStartCurrencyString() throws Exception {
        assertThat(CurrencyUtils.isSundayStartCurrency("AED")).isTrue();
    }

    @Test
    public void testAedIsSundayStartCurrency() throws Exception {
        Currency aed = Currency.getInstance("AED");
        assertThat(CurrencyUtils.isSundayStartCurrency(aed)).isTrue();
    }

    @Test
    public void testGetSundayStartCurrencies() throws Exception {
        assertThat(CurrencyUtils.getSundayStartCurrencies()).containsOnly("AED", "SAR");
    }

}
