package com.halfplatepoha.ubersurge;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by MacboolBro on 08/04/16.
 */
public class ServiceGenerator {

    private static final String BASE_URL = "https://api.uber.com";
    private static final String AUTHORISATION_KEY = "Authorization";
    private static final String AUTHORISATION_VALUE = "Token ";

    private static final String SERVER_TOKEN = "BM1OEjscBEofFXnyK66izYfzc8MTxLXbkXbz_0KG";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader(AUTHORISATION_KEY, AUTHORISATION_VALUE + SERVER_TOKEN)
                    .build();
            return chain.proceed(request);
        }
    };

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create());

    public static <Res> Res createService(Class<Res> serviceClass) {

        httpClient.networkInterceptors().add(headerInterceptor);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit.create(serviceClass);

    }

}
