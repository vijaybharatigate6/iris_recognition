package com.gate6.g6_iris_recognition.utilsPkg;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Owner on 11/23/2016.
 */

public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "COLLABYO";


    private final SharedPreferences mPref;
    private final Gson mGson;
    private static PreferencesHelper mPreferencesHelper = null;
    private Context mContext = null;

    public static PreferencesHelper getInstance(Context context) {
        if (mPreferencesHelper == null) {
            mPreferencesHelper = new PreferencesHelper(context.getApplicationContext());
        }
        return mPreferencesHelper;
    }

    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        mGson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSz")
                .create();
        mContext = context.getApplicationContext();
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    /**
     * Save object on the disk in JSON format
     *
     * @param obj modal to be serialized
     * @param key key to store modal with
     * @throws Exception
     */
    public void serialize(@NonNull Object obj, @NonNull String key) {
        mPref.edit().putString(key, mGson.toJson(obj)).apply();
        Log.i("Serialize", "serialization complete");
    }

    /**
     * Retrieve previously stored object from the disk with the given key
     *
     * @param key  key to retrieve object with
     * @param type type of the object to be retrieved (class)
     * @return de-serialized object of the given type
     */

    public Object deserialize(@NonNull String key, @NonNull Class type) {
        String serializedString = mPref.getString(key, null);
        if (TextUtils.isEmpty(serializedString)) return null;
        return mGson.fromJson(serializedString, type);
    }


    public int getInt(String key) {
        return mPref.getInt(key, 0);
    }

    public void putInt(String key, int value) {
        mPref.edit().putInt(key, value).apply();
    }

    public String getString(String key, String defalutValue) {
        return mPref.getString(key, "");
    }

    public void putString(String key, String value) {
        mPref.edit().putString(key, value).apply();
    }

    public void putBoolean(String key, boolean value) {
        mPref.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key) {
        return mPref.getBoolean(key, false);
    }

    public Long getLong(String key) {
        return mPref.getLong(key, 1);
    }

    public void putLong(String key, Long value) {
        mPref.edit().putLong(key, value).apply();
    }

}