package com.gate6.g6_iris_recognition.utilsPkg;

import org.json.JSONObject;

import java.util.List;

public interface CallBackListiners {
    void onCompleteResponse(int RequestType, JSONObject data, List irislist);


}