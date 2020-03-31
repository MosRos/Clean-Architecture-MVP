package com.morostami.mvpsample.di;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory;
import com.morostami.mvpsample.BuildConfig;
import com.morostami.mvpsample.data.api.CoinGeckoService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private final String BASE_URL = "https://api.coingecko.com/api/v3/";
    @Singleton
    @Provides
    Cache provideOkHttpChache(Application app) {
        return new Cache(app.getApplicationContext().getCacheDir(), 10*1000*1000);
    }

    @Provides
    HttpLoggingInterceptor provideLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        if(BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return interceptor;
    }


    @Singleton
    @Provides
    OkHttpClient provideHttpClient(HttpLoggingInterceptor loggingInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();
    }

    @Singleton
    @Provides
    @Named("HttpClientWithCache")
    OkHttpClient provideHttpClientWithCache(HttpLoggingInterceptor loggingInterceptor, Cache cache) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .cache(cache)
                .build();
    }

    @Provides
    Gson provideGson() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    @Singleton
    @Provides
    Retrofit provideRetrofit(OkHttpClient httpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(new NetworkResponseAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    CoinGeckoService provideCoinGeckoService(Retrofit retrofit) {
        return retrofit.create(CoinGeckoService.class);
    }
}
