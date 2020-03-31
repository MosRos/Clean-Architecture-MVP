package com.morostami.mvpsample.domain;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;


@Entity
public class Coin {
    @SerializedName("id")
    @PrimaryKey
    private final String id;

    @SerializedName("symbol")
    private final String symbol;

    @SerializedName("name")
    private final String name;

    public Coin(String id, String symbol, String name) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
