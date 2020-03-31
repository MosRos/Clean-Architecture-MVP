package com.morostami.mvpsample.data.api.responses;

import com.google.gson.annotations.SerializedName;

public class CoinGeckoPingResponse {
    @SerializedName("gecko_says")
    private final String geckoSays;

    public CoinGeckoPingResponse(String geckoSays) {
        this.geckoSays = geckoSays;
    }

    public String getGeckoSays() {
        return geckoSays;
    }
}
