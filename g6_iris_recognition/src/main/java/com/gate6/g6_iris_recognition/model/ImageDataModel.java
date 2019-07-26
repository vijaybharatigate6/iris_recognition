package com.gate6.g6_iris_recognition.model;

import java.util.List;

public class ImageDataModel implements AppBeanData {



    public List getEncodings() {
        return encodings;
    }

    public void setEncodings(List encodings) {
        this.encodings = encodings;
    }

    public List encodings;
    public String _userName;

    public String get_userName() {
        return _userName;
    }

    public void set_userName(String _userName) {
        this._userName = _userName;
    }











}
