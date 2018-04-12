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

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.Locale;

public class MainActivityResponse extends Activity implements View.OnClickListener {

    boolean mBounded;
    private ServiceSendJSONResponseToServer serviceSendJSONResponseToServer;
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
            JSONObject jObj = new JSONObject(message);

            // JSON node 'flag'
            //String flag = jObj.getString("flag");
            Log.e("JSON OBJ IS == > ",String.valueOf(jObj));
            Log.e("JSON TITLE IS ==> ",jObj.getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Excptn IS  ==> ", String.valueOf(e));
        }

    }
    @Override
    protected void onStart() {
        super.onStart();

        Intent mIntent = new Intent(this, ServiceSendJSONResponseToServer.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    };

    ServiceConnection mConnection = new ServiceConnection() {
        @SuppressLint("WrongConstant")
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivityResponse.this, "Service is disconnected", 1000).show();
            mBounded = false;
            serviceSendJSONResponseToServer = null;
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivityResponse.this, "Service is connected", 1000).show();
            mBounded = true;
            ServiceSendJSONResponseToServer.LocalBinder mLocalBinder = (ServiceSendJSONResponseToServer.LocalBinder) service;
            serviceSendJSONResponseToServer = mLocalBinder.getServerInstance();
        }
    };

    @Override
    public void onClick(View view) {


        if (view == btn_cancelReservation){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(1));
        }else if (view == btn_changeAvailability){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(2));
        }else if (view == btn_changeConfiguration){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(3));
        }else if (view == btn_clearCache){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(4));
        }else if (view == btn_dataTransfer){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(6));
        }else if (view == btn_clearChargingProfile){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(5));
        }else if (view == btn_getCompositeSchedule){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(7));
        }else if (view == btn_getConfiguration){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(8));
        }else if (view == btn_getDiagnostics){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(9));
        }else if (view == btn_getLocalListVersion){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(10));
        }else if (view == btn_remoteStartTransaction){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(11));
        }else if (view == btn_reserveNow){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(13));
        }else if (view == btn_resetResponse){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(14));
        }else if (view == btn_remoteStopTransaction){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(12));
        }else if (view == btn_sendLocalList){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(15));
        }else if (view == btn_setChargingProfile){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(16));
        }else if (view == btn_triggerMessage){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(17));
        }else if (view == btn_unlockConnector){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(18));
        }else if (view == btn_updateFirmware){
            sendMessageToServer(serviceSendJSONResponseToServer.getJSONResponse(19));
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
}