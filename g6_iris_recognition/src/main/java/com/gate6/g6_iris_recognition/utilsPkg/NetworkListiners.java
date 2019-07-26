package com.gate6.g6_iris_recognition.utilsPkg;

import com.android.volley.VolleyError;
import com.gate6.g6_iris_recognition.model.AppBeanData;

public interface NetworkListiners {
    void onCompleteResponse(int RequestType, AppBeanData data);

    void onError(VolleyError volleyError);

}