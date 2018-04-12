package com.vedbiz.websocketclient;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
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


public class MainActivityCMSResponse extends Activity {
    boolean mBounded;
    ServiceSendJSONCMSResponseToServer serviceSendJSONCMSResponseToServer;
    // LogCat tag
    private static final String TAG = MainActivityCMSResponse.class.getSimpleName();

    private Button btn_authorizeRequest;
    private Button btn_bootNotification;
    private Button btn_dataTransfer;
    private Button btn_diagnosticStatus;
    private Button btn_firmwareNotification;
    private Button btn_hrtBeat;
    private Button btn_mtrVal;
    private Button btn_startTrans;
    private Button btn_status;
    private Button btn_stopTrans;
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
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.button_layout_request);

        btn_authorizeRequest =  findViewById(R.id.btn_authorizeRequest);
        btn_bootNotification = findViewById(R.id.btn_bootNotification);
        btn_dataTransfer = findViewById(R.id.btn_dataTransfer);
        btn_diagnosticStatus = findViewById(R.id.btn_diagnosticStatus);
        btn_firmwareNotification= findViewById(R.id.btn_firmwareNotification);
        btn_hrtBeat= findViewById(R.id.btn_hrtBeat);
        btn_mtrVal= findViewById(R.id.btn_mtrVal);
        btn_startTrans= findViewById(R.id.btn_startTrans);
        btn_status= findViewById(R.id.btn_status);
        btn_stopTrans= findViewById(R.id.btn_stopTrans);


        utils = new Utils(getApplicationContext());

        // Getting the person name from previous screen
        Intent i = getIntent();
        name = i.getStringExtra("name");

        btn_stopTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(10));
            }
        });
        btn_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(9));
            }
        });
        btn_startTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(8));
            }
        });
        btn_mtrVal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(7));
            }
        });
        btn_hrtBeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(6));
            }
        });
        btn_firmwareNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(5));
            }
        });

        btn_diagnosticStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(4));
            }
        });
        btn_bootNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(2));
            }
        });

        btn_dataTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(3));
            }
        });

        btn_authorizeRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Sending message to web socket server
                //sendMessageToServer(utils.getSendMessageJSON(inputMsg.getText().toString()));
           /*     Intent mIntent = new Intent(MainActivityRequest.this, ServiceSendJSONRequestToServer.class);
                bindService(mIntent, mConnection, BIND_AUTO_CREATE);*/
                sendMessageToServer(serviceSendJSONCMSResponseToServer.getJSONRequest(1));
                // Clearing the input filed once message was sent
               // inputMsg.setText("");
            }
        });

        //listMessages = new ArrayList<Message>();

        //adapter = new MessagesListAdapter(this, listMessages);
      //  listViewMessages.setAdapter(adapter);

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
                Log.e("On Message Byte ==> ", String.format("Got binary message! %s", bytesToHex(data)));
                // Message will be in JSON format
                parseMessage(bytesToHex(data));
            }

            /**
             * Called when the connection is terminated
             * */
            @Override
            public void onDisconnect(int code, String reason) {
               // Toast.makeText(getApplicationContext(),"On DisConnect...",Toast.LENGTH_SHORT).show();
                String message = String.format(Locale.US,"Disconnected! Code: %d Reason: %s", code, reason);

                showToast(message);

                // clear the session id from shared preferences
                utils.storeSessionId(null);
            }

            @Override
            public void onError(Exception error) {
                //Toast.makeText(getApplicationContext(),"On Error...",Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error! : " + error);
                Log.e("On Error ==> ", String.valueOf(error));

                showToast("Error! : " + error);
            }

        }, null);
        client.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent mIntent = new Intent(this, ServiceSendJSONCMSResponseToServer.class);
        bindService(mIntent, mConnection, BIND_AUTO_CREATE);
    };

    ServiceConnection mConnection = new ServiceConnection() {
        @SuppressLint("WrongConstant")
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Toast.makeText(MainActivityCMSResponse.this, "Service is disconnected", 1000).show();
            mBounded = false;
            serviceSendJSONCMSResponseToServer = null;
        }

        @SuppressLint("WrongConstant")
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Toast.makeText(MainActivityCMSResponse.this, "Service is connected", 1000).show();
            mBounded = true;
            ServiceSendJSONCMSResponseToServer.LocalBinder mLocalBinder = (ServiceSendJSONCMSResponseToServer.LocalBinder) service;
            serviceSendJSONCMSResponseToServer = mLocalBinder.getServerInstance();
        }
    };

    /**
     * Method to send message to web socket server
     * */
    public void sendMessageToServer(String message) {
        if (client != null && client.isConnected()) {
            client.send(message);
        }
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
    protected void onDestroy() {
        super.onDestroy();

        if(client != null & client.isConnected()){
            client.disconnect();
        }

        stopService(new Intent(getApplicationContext(),ServiceSendJSONRequestToServer.class));
    }

    /**
     * Appending message to list view
     * */
    private void appendMessage(final Message m) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                //listMessages.add(m);

                //adapter.notifyDataSetChanged();

                // Playing device's notification
                playBeep();
            }
        });
    }

    private void showToast(final String message) {

        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), message,
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    /**
     * Plays device's default notification sound
     * */
    public void playBeep() {

        try {
            Uri notification = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(),
                    notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(mBounded) {
            unbindService(mConnection);
            mBounded = false;
        }
    };
}