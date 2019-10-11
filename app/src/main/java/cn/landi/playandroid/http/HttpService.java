package cn.landi.playandroid.http;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/3/12
 * @edit TODO
 */
public interface HttpService {

    @GET("book/{id}")
    Call<ResponseBody> getBook(@Path("id") int id);

}
