package ananda.if4b.tulis_aja;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIService {
    @GET("post")
    Call<ValueData<List<Post>>> getPost();

    @FormUrlEncoded
    @POST("auth/login")
    Call<ValueData<User>> login(
                          @Field("username") String username,
                          @Field("password") String password);


    @FormUrlEncoded
    @POST("auth/register")
    Call<ValueData<User>> register(
                             @Field("username") String username,
                             @Field("password") String password);

    @FormUrlEncoded
    @POST("post")
    Call<ValueNoData> addPost(
            @Field("user_id") String userId,
            @Field("content") String content,
            @Field("foto") String foto,
            @Field("judul")String judul,
            @Field("lokasi")String lokasi);

    @FormUrlEncoded
    @PUT("post")
    Call<ValueNoData> updatePost(@Field("id") String id,
                              @Field("content") String content)
            ;

    @FormUrlEncoded
    @DELETE("post/{id}")
    Call<ValueNoData> deletePost(@Field("id") String id);
}
