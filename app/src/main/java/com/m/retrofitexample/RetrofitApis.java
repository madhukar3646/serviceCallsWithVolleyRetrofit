package com.m.retrofitexample;

import com.m.retrofitexample.models.GetServiceModel;
import com.m.retrofitexample.models.ImageUploadResponseModel;
import com.m.retrofitexample.models.PostServiceModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by madhu on 8/2/2018.
 */

public interface RetrofitApis {

    @GET("getcuisines")
    Call<GetServiceModel> getServiceResponse();

    @FormUrlEncoded
    @POST("userProfile")
    Call<PostServiceModel> getPostServiceResponse(@Field("user_id") String userid);

    @Multipart
    @POST("eventImage")
    Call<ImageUploadResponseModel> uploadFile(@Part MultipartBody.Part imageFile);

    @Multipart
    @POST("eventImage")
    Call<ImageUploadResponseModel> uploadFileWithOtherParams(@Part MultipartBody.Part imageFile,@Part("user_name") String username,@Part("user_id") String userid);

}
