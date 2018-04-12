package com.vedbiz.websocketclient;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceSendJSONResponseToServer extends Service {

    IBinder mBinder = new LocalBinder();
    Utils utils;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public String getJSONResponse(int flag) {

        utils = new Utils(getApplicationContext());

        Log.e("Get JSONString =>  ","Called");
        if (flag == 1 )
        {
            String cancelReservation = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"CancelReservationResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(cancelReservation);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
           // return utils.getSendMessageJSON("CancelReservationResponse",cancelReservation) ;
        }
        else if (flag == 2)
        {
            String changeAvailability ="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ChangeAvailabilityResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\",\"Scheduled\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(changeAvailability);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("ChangeAvailabilityResponse",changeAvailability);
        }
        else if (flag == 3)
        {
            String changeConfiguration = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ChangeConfigurationResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\",\"RebootRequired\",\"NotSupported\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(changeConfiguration);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);

            //return utils.getSendMessageJSON("ChangeConfigurationResponse",changeConfiguration) ;

        }
        else if (flag == 4)
        {
            String clearCache = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ClearCacheResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(clearCache);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
           // return utils.getSendMessageJSON("ClearCacheResponse",clearCache) ;

        }
        else if (flag == 5)
        {
            String clearChargingProfile = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ClearChargingProfileResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Unknown\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(clearChargingProfile);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("ClearChargingProfile",clearChargingProfile) ;
        }
        else if (flag == 6)
        {
            String dataTransfer = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"DataTransferResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\",\"UnknownMessageId\",\"UnknownVendorId\"]},\"data\":{\"type\":\"string\"}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(dataTransfer);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
          //  return utils.getSendMessageJSON("DataTransferResponse",dataTransfer) ;
        }
        else if (flag == 7)
        {
            String getCompositeSchedule = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"GetCompositeScheduleResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\"]},\"connectorId\":{\"type\":\"integer\"},\"scheduleStart\":{\"type\":\"string\",\"format\":\"date-time\"},\"chargingSchedule\":{\"type\":\"object\",\"properties\":{\"duration\":{\"type\":\"integer\"},\"startSchedule\":{\"type\":\"string\",\"format\":\"date-time\"},\"chargingRateUnit\":{\"type\":\"string\",\"enum\":[\"A\",\"W\"]},\"chargingSchedulePeriod\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"startPeriod\":{\"type\":\"integer\"},\"limit\":{\"type\":\"number\",\"multipleOf\":0.1},\"numberPhases\":{\"type\":\"integer\"}},\"required\":[\"startPeriod\",\"limit\"]}},\"minChargingRate\":{\"type\":\"number\",\"multipleOf\":0.1}},\"required\":[\"chargingRateUnit\",\"chargingSchedulePeriod\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(getCompositeSchedule);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("GetCompositeScheduleResponse",getCompositeSchedule) ;
        }
        else if (flag == 8)
        {
            String getConfiguration = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"GetConfigurationResponse\",\"type\":\"object\",\"properties\":{\"configurationKey\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"key\":{\"type\":\"string\",\"maxLength\":50},\"readonly\":{\"type\":\"boolean\"},\"value\":{\"type\":\"string\",\"maxLength\":500}},\"required\":[\"key\",\"readonly\"]}},\"unknownKey\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"maxLength\":50}}},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(getConfiguration);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
           // return utils.getSendMessageJSON("GetConfigurationResponse",getConfiguration) ;
        }
        else if (flag == 9)
        {

            String getDiagnostics = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"GetDiagnosticsResponse\",\"type\":\"object\",\"properties\":{\"fileName\":{\"type\":\"string\",\"maxLength\":255}},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(getDiagnostics);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("GetDiagnosticsResponse",getDiagnostics) ;
        }
        else if (flag == 10)
        {
            String getLocalListVersion = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"GetLocalListVersionResponse\",\"type\":\"object\",\"properties\":{\"listVersion\":{\"type\":\"integer\"}},\"additionalProperties\":false,\"required\":[\"listVersion\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(getLocalListVersion);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("GetLocalListVersionResponse",getLocalListVersion) ;
        }
        else if (flag == 11)
        {
            String remoteStartTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"RemoteStartTransactionResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(remoteStartTransaction);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("RemoteStartTransactionResponse",remoteStartTransaction) ;
        }
        else if (flag == 12)
        {
            String remoteStopTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"RemoteStopTransactionResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(remoteStopTransaction);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("RemoteStopTransactionResponse",remoteStopTransaction) ;
        }
        else if (flag == 13)
        {
            String reserveNowResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ReserveNowResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Faulted\",\"Occupied\",\"Rejected\",\"Unavailable\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(reserveNowResponse);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("ReserveNowResponse",reserveNowResponse) ;
        }
        else if (flag == 14)
        {
            String resetResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ResetResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(resetResponse);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
           // return utils.getSendMessageJSON("ResetResponse",resetResponse) ;
        }
        else if (flag == 15)
        {
            String sendLocalListResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"SendLocalListResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Failed\",\"NotSupported\",\"VersionMismatch\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(sendLocalListResponse);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("SendLocalListResponse",sendLocalListResponse) ;
        }
        else if (flag == 16)
        {
            String setChargingProfileResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"SetChargingProfileResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\",\"NotSupported\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(setChargingProfileResponse);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
           // return utils.getSendMessageJSON("SetChargingProfileResponse",setChargingProfileResponse) ;

        }
        else if (flag == 17)
        {
            String triggerMessageResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"TriggerMessageResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\",\"NotImplemented\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(triggerMessageResponse);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
           // return utils.getSendMessageJSON("TriggerMessageResponse",triggerMessageResponse) ;

        }
        else if (flag == 18)
        {
            String unlockConnectorResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"UnlockConnectorResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Unlocked\",\"UnlockFailed\",\"NotSupported\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(unlockConnectorResponse);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
           // return utils.getSendMessageJSON("UnlockConnectorResponse",unlockConnectorResponse) ;

        }
        else if (flag == 19)
        {
            String updateFirmwareResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"UpdateFirmwareResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(updateFirmwareResponse);
                Log.e("JSON OBJ IN => ","Service");
                Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("Exception  => "+e,"Service");
            }
            return utils.getSendMessageJSONNext(jsonObject);
            //return utils.getSendMessageJSON("UpdateFirmwareResponse",updateFirmwareResponse) ;
        }
        else
        {
            return " NO Any Json";
        }
    }

    class LocalBinder extends Binder {
        ServiceSendJSONResponseToServer getServerInstance() {
            return ServiceSendJSONResponseToServer.this;
        }
    }
}
