package com.arecmetafora.leilaoreceitafederal.di;

import android.app.Application;
import android.content.Context;

import com.arecmetafora.leilaoreceitafederal.R;
import com.arecmetafora.leilaoreceitafederal.model.ApiService;
import com.arecmetafora.leilaoreceitafederal.model.DateGsonDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This is a Dagger module. We use this to bind our CustomApplication class as a Context in the AppComponent
 * By using Dagger Android we do not need to pass our CustomApplication instance to any module,
 * we simply need to expose our CustomApplication as Context.
 * One of the advantages of Dagger.Android is that your
 * CustomApplication & Activities are provided into your graph for you.
 * {@link AppComponent}.
 */
@Module
public abstract class AppModule {

    @Binds
    abstract Context bindContext(Application application);

    @Provides
    @Singleton
    static DateGsonDeserializer provideDateGsonDeserializer() {
        return new DateGsonDeserializer();
    }

    @Provides
    @Singleton
    static Gson provideGson(DateGsonDeserializer dateGsonDeserializer) {
        return new GsonBuilder()
                .registerTypeAdapter(Date.class, dateGsonDeserializer)
                .create();
    }

    @Provides
    @Singleton
    static Retrofit providesRetrofit(Context context, Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .baseUrl(context.getString(R.string.base_url))
                .build();
    }

    @Provides
    @Singleton
    static ApiService provideProductService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
