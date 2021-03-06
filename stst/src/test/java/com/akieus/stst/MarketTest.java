package com.akieus.stst;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MarketTest {

    @Test
    public void calculateMarketId() {
        assertThat(new Market(PriceSource.SOURCE1, null).getId(), is(0));
        assertThat(new Market(PriceSource.SOURCE2, null).getId(), is(1));
        assertThat(new Market(PriceSource.SOURCE30, null).getId(), is(29));
        assertThat(new Market(PriceSource.SOURCE1, PriceProvider.PROVIDER1).getId(), is(32));
        assertThat(new Market(PriceSource.SOURCE2, PriceProvider.PROVIDER1).getId(), is(33));
        assertThat(new Market(PriceSource.SOURCE30, PriceProvider.PROVIDER1).getId(), is(61));
        assertThat(new Market(PriceSource.SOURCE30, PriceProvider.PROVIDER50).getId(), is(1629));
    }

}
