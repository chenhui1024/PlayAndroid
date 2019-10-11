package cn.landi.playandroid.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author chenhui@landicorp.com
 * @description TODO
 * @date 2019/3/12
 * @edit TODO
 */
public interface RxHttpService {

    @GET("book/{id}")
    Observable<ResponseBody> getBook(@Path("id") int id);

}
