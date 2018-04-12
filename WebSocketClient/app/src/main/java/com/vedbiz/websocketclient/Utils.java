package com.vedbiz.websocketclient;

/**
 * Created by user on 10-02-2018.
 */

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class Utils {

    private Context context;
    private SharedPreferences sharedPref;

    private static final String KEY_SHARED_PREF = "ANDROID_WEB_CHAT";
    private static final int KEY_MODE_PRIVATE = 0;
    private static final String KEY_SESSION_ID = "sessionId",
            FLAG_MESSAGE = "message";

    public Utils(Context context) {
        this.context = context;
        sharedPref = this.context.getSharedPreferences(KEY_SHARED_PREF,
                KEY_MODE_PRIVATE);
    }

    public void storeSessionId(String sessionId) {
        Editor editor = sharedPref.edit();
        editor.putString(KEY_SESSION_ID, sessionId);
        editor.commit();
    }

    public String getSessionId() {
        return sharedPref.getString(KEY_SESSION_ID, null);
    }

    public String getSendMessageJSON(String key,String jsonStr) {
        String json = null;

        try {
            JSONObject jObj = new JSONObject();
            jObj.put(key, jsonStr);
            /*jObj.put("title",title);
            jObj.put("type",type);
            jObj.put("properties","properties");*/

            json = jObj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json;
    }



    public String getSendMessageJSONNext(JSONObject jsonStr) {
        String json = null;

        JSONObject jObj = new JSONObject();
        jObj = jsonStr;

        // jObj.put(key, jsonStr);
            /*jObj.put("title",title);
            jObj.put("type",type);
            jObj.put("properties","properties");*/

        json = jObj.toString();

        return json;
    }

}
