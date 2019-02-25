package jp.tomiyama.noir.qiitasample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QiitaService {

//    @GET("users/takaishi78/items")
    @GET("users/{username}/items")
    Call<List<ItemEntity>> getItems(
            @Path("username") String user,
            @Query("page") int page,
            @Query("par_page") int perPage
    );

//    @GET("takaishi78")
    @GET("users/{username}")
    Call<User> getUser(
            @Path("username") String user
    );


}
