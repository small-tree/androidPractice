package xianchao.com.practice.exploreRetrofit;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public class RetrofitClient {

    private static Retrofit retrofit;

    public static void init(){
        if (retrofit != null) {
            new IllegalStateException("只能初始化异常");
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.juheapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory();
                .build();
    }

    public static APIService getNetApi(){
        APIService service = retrofit.create(APIService.class);
        return service;
    }

    public interface APIService{
        @GET("japi/toh")
        Call<HistoricalTodayBean> getHistoricalToday(@QueryMap HashMap<String,String> hashMap);
    }

}
