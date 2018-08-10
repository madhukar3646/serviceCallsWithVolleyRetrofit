package com.m.retrofitexample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.m.retrofitexample.models.GetServiceModel;
import com.m.retrofitexample.models.ImageUploadResponseModel;
import com.m.retrofitexample.models.PostServiceModel;
import com.mikelau.croperino.Croperino;
import com.mikelau.croperino.CroperinoConfig;
import com.mikelau.croperino.CroperinoFileUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VolleyCallsActivity extends AppCompatActivity {

    @BindView(R.id.btn_getcall)
    Button btn_getcall;
    @BindView(R.id.btn_postcall) Button btn_postcall;
    @BindView(R.id.btn_imageuploadcall) Button btn_imageuploadcall;
    @BindView(R.id.iv_testimage)
    ImageView iv_testimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_calls);
        ButterKnife.bind(this);
        init();
    }

    private void init()
    {
        new CroperinoConfig("IMG_" + System.currentTimeMillis() + ".jpg", "/MikeLau/Pictures", "/sdcard/MikeLau/Pictures");
        CroperinoFileUtil.verifyStoragePermissions(VolleyCallsActivity.this);
        CroperinoFileUtil.setupDirectory(VolleyCallsActivity.this);

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

                Croperino.prepareChooser(VolleyCallsActivity.this, "Capture photo...", ContextCompat.getColor(VolleyCallsActivity.this, android.R.color.background_dark));
            }
        });
    }

    private void getServiceFunction()
    {
         VolleyServiceCall.executeServiceCall(this, VolleyServiceCall.GET,AppConstants.BASE_URL+"getcuisines",new HashMap<String,String>(),new HashMap<String,String>(),new VolleyServiceCall.ServiceResponse(){

             @Override
             public void getResponse(String response) {
                 Gson gson=new Gson();
                 GetServiceModel model=gson.fromJson(response,GetServiceModel.class);
                 List<GetServiceModel.Cuisine> listofCusines=model.getCuisines();
                 Log.e("get response","volley is "+listofCusines.size()+" "+model.getResult());
                 for(int i=0;i<listofCusines.size();i++)
                 {
                     Log.e("get single item","volley is "+listofCusines.get(i).getCuisineName());
                 }
             }

             @Override
             public void getErrorResponse(String errormessage) {
                 Log.e("get response","getErrorResponse");
             }
         });
    }

    private void postServiceFunction()
    {
        Map<String,String> header_params=new HashMap<>();
        Map<String,String> params=new HashMap<>();
        params.put("user_id","4");
        VolleyServiceCall.executeServiceCall(this, VolleyServiceCall.POST,AppConstants.BASE_URL+"userProfile",header_params,params,new VolleyServiceCall.ServiceResponse(){

            @Override
            public void getResponse(String response) {
                Gson gson=new Gson();
                PostServiceModel model=gson.fromJson(response,PostServiceModel.class);
                Log.e("post service response","volley is "+model.getResult());
                Log.e("mobile number","volley is "+model.getUserInfo().getMobilenumber());
            }

            @Override
            public void getErrorResponse(String errormessage) {
                Log.e("post response","getErrorResponse");
            }
        });
    }

    public void uploadFile(String path)
    {
        Bitmap bitmap= BitmapFactory.decodeFile(path);
        Map<String,String> params=new HashMap<>();
        //params.put("user_id","4");
        VolleyServiceCall.uploadBitmap(this, AppConstants.BASE_URL+"eventImage", "image", bitmap, params, new VolleyServiceCall.ServiceResponse() {
            @Override
            public void getResponse(String response) {

                Gson gson=new Gson();
                ImageUploadResponseModel model=gson.fromJson(response,ImageUploadResponseModel.class);
                Log.e("upload response","volley is "+model.getResult());
            }

            @Override
            public void getErrorResponse(String errormessage) {
                Log.e("upload response"," failed ");
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
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(), VolleyCallsActivity.this, true, 1, 1, R.color.gray, R.color.gray_variant);
                }
                break;
            case CroperinoConfig.REQUEST_PICK_FILE:
                if (resultCode == Activity.RESULT_OK) {
                    CroperinoFileUtil.newGalleryFile(data, VolleyCallsActivity.this);
                    Croperino.runCropImage(CroperinoFileUtil.getTempFile(), VolleyCallsActivity.this, true, 0, 0, R.color.gray, R.color.gray_variant);
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

}
