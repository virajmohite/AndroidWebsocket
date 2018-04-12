package com.vedbiz.websocketclient;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceSendJSONCMSResponseToServer extends Service {

    public IBinder mBinder = new ServiceSendJSONCMSResponseToServer.LocalBinder();
    Utils utils;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public class LocalBinder extends Binder {
        public ServiceSendJSONCMSResponseToServer getServerInstance() {
            return ServiceSendJSONCMSResponseToServer.this;
        }
    }

    public String getJSONRequest(int flag) {

        utils = new Utils(getApplicationContext());

        Log.e("Get JSONString =>  ","Called");
        if (flag == 1 ){
            String authorizeResponse="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"AuthorizeResponse\",\"type\":\"object\",\"properties\":{\"idTagInfo\":{\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Blocked\",\"Expired\",\"Invalid\",\"ConcurrentTx\"]},\"expiryDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"parentIdTag\":{\"type\":\"string\",\"maxLength\":20}},\"required\":[\"status\"]}},\"additionalProperties\":false,\"required\":[\"idTagInfo\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(authorizeResponse);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            //return utils.getSendMessageJSON("AuthorizeRequest",authorizeRequest) ;
            return utils.getSendMessageJSONNext(jsonObject);
        }
        else if (flag == 2)
        {
            String bootNotification="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"BootNotificationResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Pending\",\"Rejected\"]},\"currentTime\":{\"type\":\"string\",\"format\":\"date-time\"},\"interval\":{\"type\":\"number\"}},\"additionalProperties\":false,\"required\":[\"status\",\"currentTime\",\"interval\"]}";

            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(bootNotification);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            //return utils.getSendMessageJSON("BootNotification",bootNotification);
            return utils.getSendMessageJSONNext(jsonObject);
        }
        else if (flag == 3)
        {
            String dataTransfer="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"DataTransferResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\",\"UnknownMessageId\",\"UnknownVendorId\"]},\"data\":{\"type\":\"string\"}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(dataTransfer);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("DataTransfer",dataTransfer) ;
        }
        else if (flag == 4)
        {
            String diagnosticsStatusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"DiagnosticsStatusNotificationResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(diagnosticsStatusNotification);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            // return utils.getSendMessageJSON("DiagnosticsStatusNotification",diagnosticsStatusNotification) ;
        }
        else if (flag == 5)
        {
            String firmwareStatusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"FirmwareStatusNotificationResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(firmwareStatusNotification);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            // return utils.getSendMessageJSON("FirmwareStatusNotification",firmwareStatusNotification) ;
        }
        else if (flag == 6)
        {
            String heartbeatResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"HeartbeatResponse\",\"type\":\"object\",\"properties\":{\"currentTime\":{\"type\":\"string\",\"format\":\"date-time\"}},\"additionalProperties\":false,\"required\":[\"currentTime\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(heartbeatResponse);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("HeartbeatRequest",heartbeatRequest) ;
        }
        else if (flag == 7)
        {
            String  meterValues = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"MeterValuesResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(meterValues);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            //return utils.getSendMessageJSON("MeterValues",meterValues) ;
            return utils.getSendMessageJSONNext(jsonObject);
        }
        else if (flag == 8)
        {
            String startTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StartTransactionResponse\",\"type\":\"object\",\"properties\":{\"idTagInfo\":{\"type\":\"object\",\"properties\":{\"expiryDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"parentIdTag\":{\"type\":\"string\",\"maxLength\":20},\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Blocked\",\"Expired\",\"Invalid\",\"ConcurrentTx\"]}},\"required\":[\"status\"]},\"transactionId\":{\"type\":\"integer\"}},\"additionalProperties\":false,\"required\":[\"idTagInfo\",\"transactionId\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(startTransaction);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("StartTransaction",startTransaction) ;
        }
        else if (flag == 9)
        {
            String statusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StatusNotificationResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(statusNotification);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            // return utils.getSendMessageJSON("StatusNotification",statusNotification) ;
        }
        else if (flag == 10)
        {
            String stopTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StopTransactionResponse\",\"type\":\"object\",\"properties\":{\"idTagInfo\":{\"type\":\"object\",\"properties\":{\"expiryDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"parentIdTag\":{\"type\":\"string\",\"maxLength\":20},\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Blocked\",\"Expired\",\"Invalid\",\"ConcurrentTx\"]}},\"required\":[\"status\"]}},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(stopTransaction);
                Log.e("JSON OBJ IN => ","Service");
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            // return utils.getSendMessageJSON("StopTransaction",stopTransaction) ;
        }
        else
        {
            return " NO JSON";
        }
    }
}