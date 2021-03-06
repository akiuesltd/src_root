package com.akieus.stst;

import javax.annotation.Nullable;

public class FxPriceImpl implements FxPrice {

    private final double bid;
    private final double offer;
    private final boolean stale;
    private final PriceSource source;
    private final PriceProvider provider;

    public FxPriceImpl(final double mid) {
        this(mid, mid, false, null, null);
    }

    public FxPriceImpl(final double bid, final double offer, final PriceSource source, final PriceProvider provider) {
        this(bid, offer, false, source, provider);
    }

    public FxPriceImpl(final double bid, final double offer, final boolean stale, final PriceSource source, final PriceProvider provider) {
        this.bid = bid;
        this.offer = offer;
        this.stale = stale;
        this.source = source;
        this.provider = provider;
    }

    @Override
    public double getBid() {
        return bid;
    }

    @Override
    public double getOffer() {
        return offer;
    }

    @Override
    public boolean isStale() {
        return stale;
    }

    @Override
    public PriceSource getSource() {
        return source;
    }

    @Nullable
    @Override
    public PriceProvider getProvider() {
        return provider;
    }
}
