package ru.blizzed.opensongkick;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.blizzed.opensongkick.models.Error;

import java.io.IOException;
import java.lang.annotation.Annotation;

public class OpenSongKickContext {

    private static final String ROOT_URL = "http://api.songkick.com/api/3.0/";

    private static OpenSongKickContext instance;
    private String apiKey;

    private Retrofit retrofit;

    private OpenSongKickContext(String apiKey) {
        this.apiKey = apiKey;
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor((chain -> {
                    Request request = chain.request();
                    HttpUrl url = request.url().newBuilder().addQueryParameter("apikey", apiKey).build();
                    request = request.newBuilder().url(url).build();
                    return chain.proceed(request);
                }))
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }

    public static OpenSongKickContext getInstance() {
        checkInit();
        return instance;
    }

    public static void initialize(String apiKey) {
        if (instance == null)
            instance = new OpenSongKickContext(apiKey);
    }

    public String getApiKey() {
        checkInit();
        return instance.apiKey;
    }

    /**
     * Checks if you (or someone else) initialized OpenSongKick
     *
     * @return true if OpenSongKick is initialized
     */
    public static boolean isInitialized() {
        return instance != null;
    }

    /**
     * Returns a full URL to the SongKick API service as {@link String}
     *
     * @return full URL to SongKick API
     */
    public String getRootUrl() {
        checkInit();
        return ROOT_URL;
    }

    public <T> T createService(Class<T> service) {
        checkInit();
        return retrofit.create(service);
    }

    public Error parseError(ResponseBody responseBody) throws IOException {
        Converter<ResponseBody, Error> converter = retrofit.responseBodyConverter(Error.class, new Annotation[0]);
        return converter.convert(responseBody);
    }

    private static void checkInit() {
        if (!isInitialized()) throw new RuntimeException("OpenSongKickContext must be initialized first.");
    }

}
