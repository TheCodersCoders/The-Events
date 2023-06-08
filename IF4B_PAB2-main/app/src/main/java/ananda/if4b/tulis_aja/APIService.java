package ananda.if4b.tulis_aja;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService {
    @GET("post")
    Call<ValueData<List<Post>>> getPost();

    @FormUrlEncoded
    @POST("auth/login")
    Call<ValueData> login(
                          @Field("username") String username,
                          @Field("password") String password);


    @FormUrlEncoded
    @POST("auth/register")
    Call<ValueData> register(
                             @Field("username") String username,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("post")
    Call<ValueNoData> addPost(
            @Field("username") String username,
            @Field("content") String content, String foto, String judul, String lokasi);

    @FormUrlEncoded
    @POST("post")
    Call<ValueNoData> updatePost(@Field("id") String id,
                              @Field("content") String content);

    @FormUrlEncoded
    @POST("post/{id}")
    Call<ValueNoData> deletePost(@Field("id") String id);
}
