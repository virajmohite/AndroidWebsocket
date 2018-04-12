package com.vedbiz.websocketclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.codebutler.android_websockets.WebSocketClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class MainActivityCMSRequest extends Activity implements View.OnClickListener {

    boolean mBounded;
    private ServiceSendJSONCMSRequestToServer serviceSendJSONCMSRequestToServer;
    // LogCat tag

    private Button btn_cancelReservation;
    private Button btn_changeAvailability;
    private Button btn_changeConfiguration;
    private Button btn_clearCache;
    private Button btn_dataTransfer;
    private Button btn_clearChargingProfile;
    private Button btn_getCompositeSchedule;
    private Button btn_getConfiguration;
    private Button btn_getDiagnostics;
    private Button btn_getLocalListVersion;
    private Button btn_remoteStartTransaction;
    private Button btn_reserveNow;
    private Button btn_resetResponse;
    private Button btn_remoteStopTransaction;
    private Button btn_sendLocalList;
    private Button btn_setChargingProfile;
    private Button btn_triggerMessage;
    private Button btn_unlockConnector;
    private Button btn_updateFirmware;


    // private EditText inputMsg;

    private WebSocketClient client;

    // Chat messages list adapter
    // private MessagesListAdapter adapter;
    // private List<Message> listMessages;
    // private ListView listViewMessages;

    private Utils utils;

    // Client name
    private String name = null;

    // JSON flags to identify the kind of JSON response
    private static final String TAG_SELF = "self", TAG_NEW = "new",
            TAG_MESSAGE = "message", TAG_EXIT = "exit";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout_response);

        utils = new Utils(getApplicationContext());
        btn_cancelReservation = findViewById(R.id.btn_cancelReservation);
        btn_changeAvailability = findViewById(R.id.btn_changeAvailability);
        btn_changeConfiguration = findViewById(R.id.btn_changeConfiguration);
        btn_clearCache = findViewById(R.id.btn_clearCache);
        btn_dataTransfer = findViewById(R.id.btn_dataTransfer);
        btn_clearChargingProfile = findViewById(R.id.btn_clearChargingProfile);
        btn_getCompositeSchedule = findViewById(R.id.btn_getCompositeSchedule);
        btn_getConfiguration = findViewById(R.id.btn_getConfiguration);
        btn_getDiagnostics = findViewById(R.id.btn_getDiagnostics);
        btn_getLocalListVersion = findViewById(R.id.btn_getLocalListVersion);
        btn_remoteStartTransaction = findViewById(R.id.btn_remoteStartTransaction);
        btn_reserveNow = findViewById(R.id.btn_reserveNow);
        btn_resetResponse = findViewById(R.id.btn_resetResponse);
        btn_remoteStopTransaction = findViewById(R.id.btn_remoteStopTransaction);
        btn_sendLocalList = findViewById(R.id.btn_sendLocalList);
        btn_setChargingProfile = findViewById(R.id.btn_setChargingProfile);
        btn_triggerMessage = findViewById(R.id.btn_triggerMessage);
        btn_unlockConnector = findViewById(R.id.btn_unlockConnector);
        btn_updateFirmware = findViewById(R.id.btn_updateFirmware);


        btn_cancelReservation.setOnClickListener(this);
        btn_changeAvailability.setOnClickListener(this);
        btn_changeConfiguration.setOnClickListener(this);
        btn_clearCache.setOnClickListener(this);
        btn_dataTransfer.setOnClickListener(this);
        btn_clearChargingProfile.setOnClickListener(this);
        btn_getCompositeSchedule.setOnClickListener(this);
        btn_getConfiguration.setOnClickListener(this);
        btn_getDiagnostics.setOnClickListener(this);
        btn_getLocalListVersion.setOnClickListener(this);
        btn_remoteStartTransaction.setOnClickListener(this);
        btn_reserveNow.setOnClickListener(this);
        btn_resetResponse.setOnClickListener(this);
        btn_remoteStopTransaction.setOnClickListener(this);
        btn_sendLocalList.setOnClickListener(this);
        btn_setChargingProfile.setOnClickListener(this);
        btn_triggerMessage.setOnClickListener(this);
        btn_unlockConnector.setOnClickListener(this);
        btn_updateFirmware.setOnClickListener(this);


        /**
         * Creating web socket client. This will have callback methods
         * */
        client = new WebSocketClient(URI.create(WsConfig.URL_WEBSOCKET), new WebSocketClient.Listener() {
            @Override
            public void onConnect() {
                // Toast.makeText(getApplicationContext(),"Connected...",Toast.LENGTH_SHORT).show();
                Log.e("On Connect ==> ", "On Connect...");
            }

            /**
             * On receiving the message from web socket server
             * */
            @Override
            public void onMessage(String message) {
                // Toast.makeText(getApplicationContext(),"On Message...",Toast.LENGTH_SHORT).show();
                Log.e("On Message ==> ", String.format("Got string message! %s", message));

                parseMessage(message);

            }

            @Override
            public void onMessage(byte[] data) {
               // Log.e("On Message Byte ==> ", String.format("Got binary message! %s", bytesToHex(data)));
                // Message will be in JSON format
               // parseMessage(bytesToHex(data));
            }

            /**
             * Called when the connection is terminated
             * */
            @Override
            public void onDisconnect(int code, String reason) {
                // Toast.makeText(getApplicationContext(),"On DisConnect...",Toast.LENGTH_SHORT).show();
                String message = String.format(Locale.US,"Disconnected! Code: %d Reason: %s", code, reason);

               // showToast(message);

                // clear the session id from shared preferences
                utils.storeSessionId(null);
            }

            @Override
            public void onError(Exception error) {
                //Toast.makeText(getApplicationContext(),"On Error...",Toast.LENGTH_SHORT).show();
                Log.e(" ", "Error! : " + error);
                Log.e("On Error ==> ", String.valueOf(error));

                //showToast("Error! : " + error);
            }

        }, null);
        client.connect();

    }

    /**
     * Parsing the JSON message received from server The intent of message will
     * be identified by JSON node 'flag'. flag = self, message belongs to the
     * person. flag = new, a new person joined the conversation. flag = message,
     * a new message received from server. flag = exit, somebody left the
     * conversation.
     * */
    @SuppressLint("LongLogTag")
    private void parseMessage(String message) {
        Log.e("Parse Message ==> ",message);
        Log.e("Before Parse Message ==> ",message);
        message  = message.replace("\n", "");
        message  = message.replace("   ", "");
        //authJsonToServer  = authJsonToServer.replace("\"", " ");
        message  = message.replace("\\", "");
        message  = message.replaceAll("\\/","");
        message = message.substring(1, message.length() - 1);
        // message  = message.replaceAll(m,"");
        // message  = message.replaceAll("\\\","");
        Log.e("After Parse Message ==> ",message);

        try {
            JSONArray jArray = new JSONArray(message);
            int messageId = jArray.getInt(0);
            Log.e("ParseMessage MSGID ==> ", String.valueOf(messageId));
            String uniqueId = jArray.getString(1);
            Log.e("ParseMessage UNQID ==> ", uniqueId);
            String action = jArray.getString(2);
            Log.e("ParseMessage ACTN ==> ", action);
            JSONObject jsonObj = jArray.getJSONObject(3);
            Log.e("ParseMessage JSON ==> ", jsonObj.toString());

            JSONObject jsonObject = new JSONObject();//For Further Process
            JSONArray jsonArray =  new JSONArray();//For Further Process


            if (messageId == 2 ){
                switch (action) {
                    case "StopTransaction":
                        try {
                            JSONObject idTagInfo = new JSONObject();
                            idTagInfo.put("status", "Accepted");
                            idTagInfo.put("expiryDate", getCurrentUTC());
                            idTagInfo.put("parentIdTag",JSONObject.NULL);

                            jsonObject.put("idTagInfo",idTagInfo);

                            jsonArray.put(3);
                            jsonArray.put(uniqueId);
                            jsonArray.put(jsonObject);
                            String jsonString = jsonArray.toString();

                            sendMessageToServer(jsonString);
                            Log.e("ParseMessage RSPNS ==> ", jsonString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        break;

                    case "Authorize":
                        try {
                            JSONObject idTagInfo = new JSONObject();
                            idTagInfo.put("status", "Accepted");
                            idTagInfo.put("expiryDate", getCurrentUTC());
                            idTagInfo.put("parentIdTag",JSONObject.NULL);

                            jsonObject.put("idTagInfo",idTagInfo);

                            jsonArray.put(3);
                            jsonArray.put(uniqueId);
                            jsonArray.put(jsonObject);
                            String jsonString = jsonArray.toString();

                            sendMessageToServer(jsonString);
                            Log.e("ParseMessage RSPNS ==> ", jsonString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        break;
                    case "BootNotification":
                        try {

                            jsonObject.put("status","Accepted");
                            jsonObject.put("currentTime",getCurrentUTC());
                            jsonObject.put("interval",30);//maybe Heart Beat Interval

                            jsonArray.put(3);
                            jsonArray.put(uniqueId);
                            jsonArray.put(jsonObject);
                            String jsonString = jsonArray.toString();

                            sendMessageToServer(jsonString);

                            Log.e("ParseMessage RSPNS ==> ", jsonString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }                        break;
                    case "DataTransfer":
                        try {

                            jsonObject.put("status","Accepted");
                            jsonObject.put("data",JSONObject.NULL);

                            jsonArray.put(3);
                            jsonArray.put(uniqueId);
                            jsonArray.put(jsonObject);
                            String jsonString = jsonArray.toString();

                            sendMessageToServer(jsonString);

                            Log.e("ParseMessage RSPNS ==> ", jsonString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "DiagnosticsStatusNotification":

                            jsonArray.put(3);
                            jsonArray.put(uniqueId);
                            jsonArray.put(jsonObject);
                            String jsonStringdiagno = jsonArray.toString();

                            sendMessageToServer(jsonStringdiagno);

                            Log.e("ParseMessage RSPNS ==> ", jsonStringdiagno);

                        break;
                    case "FirmwareStatusNotification":

                        jsonArray.put(3);
                        jsonArray.put(uniqueId);
                        jsonArray.put(jsonObject);
                        String jsonStringfrm = jsonArray.toString();

                        sendMessageToServer(jsonStringfrm);

                        Log.e("ParseMessage RSPNS ==> ", jsonStringfrm);

                        break;
                    case "Heartbeat":
                        try {
                            jsonObject.put("currentTime",getCurrentUTC());

                            jsonArray.put(3);
                            jsonArray.put(uniqueId);
                            jsonArray.put(jsonObject);
                            String jsonString = jsonArray.toString();

                            sendMessageToServer(jsonString);

                            Log.e("ParseMessage RSPNS ==> ", jsonString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case "MeterValues":

                            jsonArray.put(3);
                            jsonArray.put(uniqueId);
                            jsonArray.put(jsonObject);
                            String jsonStringmtrVal = jsonArray.toString();

                            sendMessageToServer(jsonStringmtrVal);

                            Log.e("ParseMessage RSPNS ==> ", jsonStringmtrVal);

                        break;
                    case "StartTransaction":
                        try {
                            JSONObject idTagInfo = new JSONObject();
                            idTagInfo.put("status", "Accepted");
                            idTagInfo.put("expiryDate", getCurrentUTC());
                            idTagInfo.put("parentIdTag",JSONObject.NULL);

                            jsonObject.put("idTagInfo",idTagInfo);
                            jsonObject.put("transactionId",123564);

                            jsonArray.put(3);
                            jsonArray.put(uniqueId);
                            jsonArray.put(jsonObject);
                            String jsonString = jsonArray.toString();

                            sendMessageToServer(jsonString);
                            Log.e("ParseMessage RSPNS ==> ", jsonString);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        break;
                    case "StatusNotification":
                        jsonArray.put(3);
                        jsonArray.put(uniqueId);
                        jsonArray.put(jsonObject);
                        String jsonStringstsNtfctn = jsonArray.toString();

                        sendMessageToServer(jsonStringstsNtfctn);

                        Log.e("ParseMessage RSPNS ==> ", jsonStringstsNtfctn);
                        break;

                }
            }

            // JSON node 'flag'
            //String flag = jObj.getString("flag");
           // Log.e("JSON OBJ IS == > ",String.valueOf(jObj));
          //  Log.e("JSON TITLE IS ==> ",jObj.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Excptn IS  ==> ", String.valueOf(e));
        }

    }

    public void performFunction() {
        Toast.makeText(MainActivityCMSRequest.this, "Autorizeresponse", Toast.LENGTH_LONG).show();

        Log.e("performFunction","performFunction");
    }
    @Override
    protected void onStart() {
        super.onStart();

        Intent mIntent = new Intent(this, ServiceSendJSONCMSRequestToServer.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    };

    ServiceConnection mConnection = new ServiceConnection() {
        @SuppressLint("WrongConstant")
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivityCMSRequest.this, "Service is disconnected", 1000).show();
            mBounded = false;
            serviceSendJSONCMSRequestToServer = null;
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivityCMSRequest.this, "Service is connected", 1000).show();
            mBounded = true;
            ServiceSendJSONCMSRequestToServer.LocalBinder mLocalBinder = (ServiceSendJSONCMSRequestToServer.LocalBinder) service;
            serviceSendJSONCMSRequestToServer = mLocalBinder.getServerInstance();
        }
    };

    @Override
    public void onClick(View view) {


        if (view == btn_cancelReservation){
            JSONObject jsonObject = new JSONObject();

            try {

                jsonObject.put("reservationId",123456);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("CancelReservation");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //sendMessageToServer(serviceSendJSONCMSRequestToServer.getJSONResponse(CMSRequestStrings.cancelReservation));
        }else if (view == btn_changeAvailability){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("connectorId", 001);
                jsonObject.put("type", "Operative");

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("ChangeAvailability");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_changeConfiguration){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("key", "KEY123");
                jsonObject.put("value", "Some Value Here");

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("ChangeConfiguration");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_dataTransfer){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("vendorId", "M0001");
                jsonObject.put("messageId", JSONObject.NULL);
                jsonObject.put("data", JSONObject.NULL);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("DataTransfer");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_clearChargingProfile){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("id", JSONObject.NULL);
                jsonObject.put("connectorId", JSONObject.NULL);
                jsonObject.put("chargingProfilePurpose", JSONObject.NULL);
                jsonObject.put("stackLevel", JSONObject.NULL);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("ClearChargingProfile");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_getCompositeSchedule){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("connectorId", 0001);
                jsonObject.put("duration", 1);
                jsonObject.put("schedulingUnit", JSONObject.NULL);
                // jsonObject.put("chargingRateUnit", JSONObject.NULL);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("GetCompositeSchedule");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_getConfiguration){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("key", JSONObject.NULL);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("GetConfiguration");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else if (view == btn_getDiagnostics){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("location", "Location Here In URI Format");
                jsonObject.put("retries", JSONObject.NULL);
                jsonObject.put("retryInterval", JSONObject.NULL);
                jsonObject.put("startTime", JSONObject.NULL);
                jsonObject.put("stopTime", JSONObject.NULL);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("GetDiagnostics");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else if (view == btn_getLocalListVersion){

            JSONObject jsonObject = new JSONObject();

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("GetLocalListVersion");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);

        }else if (view == btn_remoteStartTransaction){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("idTag", "666035");
                jsonObject.put("connectorId", JSONObject.NULL);
                jsonObject.put("chargingProfile", JSONObject.NULL);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("RemoteStartTransaction");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_reserveNow){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("connectorId", 001);
                jsonObject.put("expiryDate", getCurrentUTC());
                jsonObject.put("idTag", "666035");
                jsonObject.put("parentIdTag", JSONObject.NULL);
                jsonObject.put("reservationId", 003456);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("ReserveNow");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_resetResponse){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("type", "Soft"); //Or Hard

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("Reset");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_remoteStopTransaction){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("transactionId", 3450656);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("RemoteStopTransaction");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_sendLocalList){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("listVersion", 1);
                jsonObject.put("localAuthorizationList",JSONObject.NULL);
                jsonObject.put("updateType", "Full"); //or Differential

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("SendLocalList");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_setChargingProfile){ //-------------- Not Completed ----------//
            JSONObject jsonObject = new JSONObject();
            try {

                JSONObject csChargingProfiles = new JSONObject();
                csChargingProfiles.put("csChargingProfiles",123123);
                jsonObject.put("connectorId", 001);
                jsonObject.put("csChargingProfiles","None");
                jsonObject.put("updateType", "Full"); //or Differential

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("SetChargingProfile");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_triggerMessage){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("requestedMessage", "BootNotification");//or -> "DiagnosticsStatusNotification","FirmwareStatusNotification","Heartbeat","MeterValues","StatusNotification"
                jsonObject.put("connectorId",JSONObject.NULL);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("TriggerMessage");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_unlockConnector){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("connectorId", 001);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("UnlockConnector");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if (view == btn_updateFirmware){
            JSONObject jsonObject = new JSONObject();
            try {

                jsonObject.put("location", "Here is Location In URI Format");
                jsonObject.put("retrieveDate", getCurrentUTC());
                jsonObject.put("retries", JSONObject.NULL);
                jsonObject.put("retryInterval", JSONObject.NULL);

                JSONArray jsonArray =  new JSONArray();
                jsonArray.put(2);
                jsonArray.put("19223201");
                jsonArray.put("UpdateFirmware");
                jsonArray.put(jsonObject);
                String jsonString = jsonArray.toString();

                sendMessageToServer(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to send message to web socket server
     * */
    public void sendMessageToServer(String message) {
        if (client != null && client.isConnected()) {
            client.send(message);
        }
    }

    public String getCurrentUTC(){
        Date time = Calendar.getInstance().getTime();
        SimpleDateFormat outputFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        outputFmt.setTimeZone(TimeZone.getTimeZone("UTC"));
        return outputFmt.format(time);
    }
}