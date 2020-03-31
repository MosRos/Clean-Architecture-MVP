package com.morostami.mvpsample.data.api.responses;

import com.google.gson.annotations.SerializedName;

public class CoinGeckoErrorResponse {
    @SerializedName("error")
    private final String error;

    public CoinGeckoErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
