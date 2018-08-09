package com.m.retrofitexample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.m.retrofitexample.models.GetServiceModel;
import com.m.retrofitexample.models.ImageUploadResponseModel;
import com.m.retrofitexample.models.PostServiceModel;
import com.mikelau.croperino.Croperino;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCallsActivity extends AppCompatActivity {

    @BindView(R.id.btn_getcall) Button btn_getcall;
    @BindView(R.id.btn_postcall) Button btn_postcall;
    @BindView(R.id.btn_imageuploadcall) Button btn_imageuploadcall;
    @BindView(R.id.iv_testimage) ImageView iv_testimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofitcalls);
        ButterKnife.bind(this);
        init();
    }

    private void init()
    {
        new CroperinoConfig("IMG_" + System.currentTimeMillis() + ".jpg", "/MikeLau/Pictures", "/sdcard/MikeLau/Pictures");
        CroperinoFileUtil.verifyStoragePermissions(RetrofitCallsActivity.this);
        CroperinoFileUtil.setupDirectory(RetrofitCallsActivity.this);

       btn_getcall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               getServiceFunction();
           }
       });

       btn_postcall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               postServiceFunction();
           }
       });

       btn_imageuploadcall.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Croperino.prepareChooser(RetrofitCallsActivity.this, "Capture photo...", ContextCompat.getColor(RetrofitCallsActivity.this, android.R.color.background_dark));
           }
       });
    }

    private void getServiceFunction()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApis service = retrofit.create(RetrofitApis.class);
        Call<GetServiceModel> call = service.getServiceResponse();
        call.enqueue(new Callback<GetServiceModel>() {
            @Override
            public void onResponse(Call<GetServiceModel> call, Response<GetServiceModel> response) {

                GetServiceModel model=response.body();
                List<GetServiceModel.Cuisine> listofCusines=model.getCuisines();
                Log.e("get response","is "+listofCusines.size()+" "+model.getResult());
                for(int i=0;i<listofCusines.size();i++)
                {
                    Log.e("get single item","is "+listofCusines.get(i).getCuisineName());
                }
            }

            @Override
            public void onFailure(Call<GetServiceModel> call, Throwable t) {

                Log.e("get response","onFailure");
            }
        });
    }

    private void postServiceFunction()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApis service = retrofit.create(RetrofitApis.class);
        Call<PostServiceModel> call = service.getPostServiceResponse("4");
        call.enqueue(new Callback<PostServiceModel>() {
            @Override
            public void onResponse(Call<PostServiceModel> call, Response<PostServiceModel> response) {
                PostServiceModel model=response.body();
                Log.e("post service response","is "+model.getResult());
                Log.e("mobile number","is "+model.getUserInfo().getMobilenumber());
            }

            @Override
            public void onFailure(Call<PostServiceModel> call, Throwable t) {
                Log.e("get response","onFailure");
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case CroperinoConfig.REQUEST_TAKE_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                /* Parameters of runCropImage = File, Activity Context, Image is Scalable or Not, Aspect Ratio X, Aspect Ratio Y, Button Bar Color, Background Color */
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(), RetrofitCallsActivity.this, true, 1, 1, R.color.gray, R.color.gray_variant);
                }
                break;
            case CroperinoConfig.REQUEST_PICK_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    CroperinoFileUtil.newGalleryFile(data, RetrofitCallsActivity.this);
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(), RetrofitCallsActivity.this, true, 0, 0, R.color.gray, R.color.gray_variant);
                }
                break;
            case CroperinoConfig.REQUEST_CROP_PHOTO:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = Uri.fromFile(CroperinoFileUtil.getTempFile());
                    iv_testimage.setImageURI(uri);
                    String strFinalImagePath = String.valueOf(CroperinoFileUtil.getTempFile());
                    uploadFile(strFinalImagePath);
                }
                break;
            default:
                break;
        }
    }


    private void uploadFile(String path)
    {
        Log.e("selected path","is "+path);
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", file.getName(),requestBody); //"image" is parameter for photo in API.

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApis service = retrofit.create(RetrofitApis.class);
        Call<ImageUploadResponseModel> call = service.uploadFile(image);
        //Call<ImageUploadResponseModel> call = service.uploadFile(image,"madhukar","201");
        call.enqueue(new Callback<ImageUploadResponseModel>() {
            @Override
            public void onResponse(Call<ImageUploadResponseModel> call, Response<ImageUploadResponseModel> response) {
                ImageUploadResponseModel model=response.body();
                Log.e("upload response","is "+model.getResult());

                if(CroperinoFileUtil.getTempFile()!=null)
                  CroperinoFileUtil.getTempFile().delete();
            }

            @Override
            public void onFailure(Call<ImageUploadResponseModel> call, Throwable t) {
                Log.e("upload response"," failed ");
                if(CroperinoFileUtil.getTempFile()!=null)
                    CroperinoFileUtil.getTempFile().delete();
            }
        });
    }
}
