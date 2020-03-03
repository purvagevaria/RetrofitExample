package com.app.purvapractical.webservices;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class RestClient {
    private static Retrofit sRetrofit;
    private static volatile ApiInterface sServiceInstance;
    private static final TypeAdapter<Boolean> sBooleanAsIntAdapter = new TypeAdapter<Boolean>() {
        @Override
        public void write(JsonWriter out, Boolean value) throws IOException {
            if (value == null) {
                out.nullValue();
            } else {
                out.value(value);
            }
        }

        @Override
        public Boolean read(JsonReader in) throws IOException {
            JsonToken peek = in.peek();
            switch (peek) {
                case BOOLEAN:
                    return in.nextBoolean();
                case NULL:
                    in.nextNull();
                    return null;
                case NUMBER:
                    return in.nextInt() != 0;
                case STRING:
                    return Boolean.parseBoolean(in.nextString());
                default:
                    throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
            }
        }
    };


    public static ApiInterface get() {
        if (sServiceInstance == null) {
            synchronized (RestClient.class) {
                if (sServiceInstance == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .client(provideHttpClient())
                            .baseUrl("api url")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();


                    sServiceInstance = retrofit.create(ApiInterface.class);
                }
            }
        }
        return sServiceInstance;
    }

    static OkHttpClient provideHttpClient() {
        final OkHttpClient.Builder httpClient = new OkHttpClient().newBuilder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging); //Check other request params
        httpClient.addNetworkInterceptor(logging); //Check headers

        httpClient.connectTimeout(60, TimeUnit.SECONDS);
        httpClient.writeTimeout(60, TimeUnit.SECONDS);
        httpClient.readTimeout(60, TimeUnit.SECONDS);


        return httpClient.build();
    }

    public static <T> T get(Class<T> clazz) {
        return sRetrofit.create(clazz);
    }


    public interface ApiInterface {

        @POST("search?term=Michael+jackson")
        Observable<ResponseBean> callWebservice();

    }

}
