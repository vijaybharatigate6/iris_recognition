package com.gate6.g6_iris_recognition;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.VolleyError;
import com.gate6.g6_iris_recognition.model.AppBeanData;
import com.gate6.g6_iris_recognition.model.ImageUploadModel;
import com.gate6.g6_iris_recognition.utilsPkg.*;
import org.json.JSONException;
import org.json.JSONObject;
import pub.devrel.easypermissions.EasyPermissions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.HashMap;
import java.util.List;


//import com.android.volley.VolleyError;

/**
 * Created by ${Vijay_Bharati} on ${16_07_2019}.
 */

public class g6_irisRecognition extends AppCompatActivity implements NetworkListiners {

    public static g6_irisRecognition instance;
    Context mcontext;
    private byte[] imagearray;
    private CallBackListiners listiners;


    public static g6_irisRecognition getInstance() {
        if (instance == null) {
            instance = new g6_irisRecognition();
        }
        return instance;
    }
    public void iris_check(Context ctx, String imagePath, CallBackListiners listiners){
//        handler = new Handler();
        mcontext=ctx;
        this.listiners=listiners;


        File imgFile = new File(imagePath);
        String[] galleryPermissions = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            galleryPermissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }

        if (EasyPermissions.hasPermissions(ctx, galleryPermissions)) {
            if(imgFile.exists()){

                Bitmap src = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                src.compress(Bitmap.CompressFormat.PNG, 100, baos);
                imagearray = baos.toByteArray();
                src.recycle();
                if (imagearray != null && imagearray.length > 0) {
                    try {
                        irisImgCheck(ctx, imagearray, imagePath);
//                Log.v("image", "uri success");
                    }
                    catch (Exception e) {
                        // display an error message
                        e.printStackTrace();
                    }



                }

            }else{
                Log.v("image not found", "imagepath not exist");
            }
        } else {
            EasyPermissions.requestPermissions(ctx, "Access for storage",
                    101, galleryPermissions);
        }

    }


    public void iris_registration(Context ctx, String companyId, String userName, String imageDataList, CallBackListiners listiners) {
        mcontext=ctx;
        this.listiners=listiners;

            try {
                irisRestration(ctx, companyId, userName,imageDataList);
//                Log.v("image", "uri success");
            }
            catch (Exception e) {
                // display an error message
                e.printStackTrace();
            }
    }




    public void iris_recognition(Context ctx, String companyId, String imagePath, CallBackListiners listiners) {
        //        handler = new Handler();
        mcontext=ctx;
        this.listiners=listiners;
        File imgFile = new File(imagePath);
        String[] galleryPermissions = new String[0];
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            galleryPermissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        }

        if (EasyPermissions.hasPermissions(ctx, galleryPermissions)) {
            if(imgFile.exists()){

                Bitmap src = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                src.compress(Bitmap.CompressFormat.PNG, 100, baos);
                imagearray = baos.toByteArray();
                src.recycle();
                if (imagearray != null && imagearray.length > 0) {
                    try {
                        irisRecognition(ctx, imagearray, imagePath,companyId);
//                Log.v("image", "uri success");
                    }
                    catch (Exception e) {
                        // display an error message
                        e.printStackTrace();
                    }



                }

            }else{
                Log.v("image not found", "imagepath not exist");
            }
        } else {
            EasyPermissions.requestPermissions(ctx, "Access for storage",
                    101, galleryPermissions);
        }



    }














    private void irisRecognition(Context ctx, byte[] imageArray, String imagePath, String companyId) {
        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("fileName", imagePath);
        hmap.put("companyId", companyId);

        Communicator.getInstance().sendFileRequestPost(ctx, imageArray, "image", RequestType.IRIS_RECOGNITION_REQUEST,
                Constant.RECOGNITION_IRIS, hmap, this);
    }


    private void irisImgCheck(Context ctx, byte[] imageArray, String imagePath) {
        HashMap<String, String> hmap = new HashMap<String, String>();
        hmap.put("fileName", imagePath);
        Communicator.getInstance().sendFileRequestPost(ctx, imageArray, "image", RequestType.IRIS_IMAGE_CHECK_REQUEST,
                Constant.IRIS_IMAGE_CHECK, hmap, this);
    }




    private void irisRestration(Context ctx, String companyId, String userName, String imageDataList) {
        JSONObject hmap = new JSONObject();
        try {
            hmap.put("companyId", companyId);
            hmap.put("userName", userName);
            hmap.put("imageDataList", imageDataList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Communicator.getInstance().addJsonRequestPost(ctx, RequestType.IRIS_REGISTER_REQUEST, Constant.REGISTRATION_IRIS, hmap, this, true);
    }

    @Override
    public void onCompleteResponse(int RequestTypee, AppBeanData data) {
        if (RequestTypee == RequestType.IRIS_IMAGE_CHECK_REQUEST) {
            //    private static byte[] data;
            //    static Handler handler;
            ImageUploadModel imageModel = (ImageUploadModel) data;
            List irislist = null;
            JSONObject iris_img_check = new JSONObject();
            try {
                iris_img_check.put("message", imageModel.getMessage());
//                iris_img_check.put("encoding",imageModel.getData().getEncodings());
                irislist = imageModel.getData().getEncodings();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(listiners!=null){
                listiners.onCompleteResponse(RequestTypee,iris_img_check,irislist);
            }

        }else  if (RequestTypee == RequestType.IRIS_REGISTER_REQUEST) {
            ImageUploadModel registrationModel = (ImageUploadModel) data;
            JSONObject iris_img_regstration = new JSONObject();
            try {
                iris_img_regstration.put("message", registrationModel.getMessage());
                iris_img_regstration.put("userName",registrationModel.getData().get_userName());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(listiners!=null){
                listiners.onCompleteResponse(RequestTypee,iris_img_regstration, null);
            }

        }else if (RequestTypee == RequestType.IRIS_RECOGNITION_REQUEST) {
            ImageUploadModel recognitionModel = (ImageUploadModel) data;
            JSONObject iris_img_recognition = new JSONObject();
            try {
                iris_img_recognition.put("message", recognitionModel.getMessage());
                iris_img_recognition.put("userName",recognitionModel.getData().get_userName());
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(listiners!=null){
                listiners.onCompleteResponse(RequestTypee,iris_img_recognition, null);
            }

        }

    }

    @Override
    public void onError(VolleyError volleyError) {
    }





}
