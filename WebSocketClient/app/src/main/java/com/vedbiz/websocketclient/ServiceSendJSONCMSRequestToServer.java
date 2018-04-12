package com.vedbiz.websocketclient;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

public class ServiceSendJSONCMSRequestToServer extends Service {

    IBinder mBinder = new ServiceSendJSONCMSRequestToServer.LocalBinder();
    Utils utils;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
    public String getJSONResponse(String flag) {
        utils = new Utils(getApplicationContext());
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(flag);
            Log.e("JSON OBJ IN => ","Service");
            Log.e("SSERVICE JSON ==> ", String.valueOf(jsonObject));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("Exception  => "+e,"Service");
        }
        return utils.getSendMessageJSONNext(jsonObject);
    }

    public String getJSONResponse(int flag) {

        utils = new Utils(getApplicationContext());

        Log.e("Get JSONString =>  ","Called");
        if (flag == 1 )
        {
            String cancelReservation = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"CancelReservationRequest\",\"type\":\"object\",\"properties\":{\"reservationId\":{\"type\":\"integer\"}},\"additionalProperties\":false,\"required\":[\"reservationId\"]}";
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
            String changeAvailability ="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ChangeAvailabilityRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"},\"type\":{\"type\":\"string\",\"enum\":[\"Inoperative\",\"Operative\"]}},\"additionalProperties\":false,\"required\":[\"connectorId\",\"type\"]}";
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
            String changeConfiguration = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ChangeConfigurationRequest\",\"type\":\"object\",\"properties\":{\"key\":{\"type\":\"string\",\"maxLength\":50},\"value\":{\"type\":\"string\",\"maxLength\":500}},\"additionalProperties\":false,\"required\":[\"key\",\"value\"]}";
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
            String clearCache = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ClearCacheRequest\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
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
            String clearChargingProfile = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ClearChargingProfileRequest\",\"type\":\"object\",\"properties\":{\"id\":{\"type\":\"integer\"},\"connectorId\":{\"type\":\"integer\"},\"chargingProfilePurpose\":{\"type\":\"string\",\"enum\":[\"ChargePointMaxProfile\",\"TxDefaultProfile\",\"TxProfile\"]},\"stackLevel\":{\"type\":\"integer\"}},\"additionalProperties\":false}";
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
            String dataTransfer = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"DataTransferRequest\",\"type\":\"object\",\"properties\":{\"vendorId\":{\"type\":\"string\",\"maxLength\":255},\"messageId\":{\"type\":\"string\",\"maxLength\":50},\"data\":{\"type\":\"string\"}},\"additionalProperties\":false,\"required\":[\"vendorId\"]}";
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
            String getCompositeSchedule = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"GetCompositeScheduleRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"},\"duration\":{\"type\":\"integer\"},\"chargingRateUnit\":{\"type\":\"string\",\"enum\":[\"A\",\"W\"]}},\"additionalProperties\":false,\"required\":[\"connectorId\",\"duration\"]}";
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
            String getConfiguration = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"GetConfigurationRequest\",\"type\":\"object\",\"properties\":{\"key\":{\"type\":\"array\",\"items\":{\"type\":\"string\",\"maxLength\":50}}},\"additionalProperties\":false}";
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

            String getDiagnostics = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"GetDiagnosticsRequest\",\"type\":\"object\",\"properties\":{\"location\":{\"type\":\"string\",\"format\":\"uri\"},\"retries\":{\"type\":\"integer\"},\"retryInterval\":{\"type\":\"integer\"},\"startTime\":{\"type\":\"string\",\"format\":\"date-time\"},\"stopTime\":{\"type\":\"string\",\"format\":\"date-time\"}},\"additionalProperties\":false,\"required\":[\"location\"]}";
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
            String getLocalListVersion = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"GetLocalListVersionRequest\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
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
            String remoteStartTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"RemoteStartTransactionRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"},\"idTag\":{\"type\":\"string\",\"maxLength\":20},\"chargingProfile\":{\"type\":\"object\",\"properties\":{\"chargingProfileId\":{\"type\":\"integer\"},\"transactionId\":{\"type\":\"integer\"},\"stackLevel\":{\"type\":\"integer\"},\"chargingProfilePurpose\":{\"type\":\"string\",\"enum\":[\"ChargePointMaxProfile\",\"TxDefaultProfile\",\"TxProfile\"]},\"chargingProfileKind\":{\"type\":\"string\",\"enum\":[\"Absolute\",\"Recurring\",\"Relative\"]},\"recurrencyKind\":{\"type\":\"string\",\"enum\":[\"Daily\",\"Weekly\"]},\"validFrom\":{\"type\":\"string\",\"format\":\"date-time\"},\"validTo\":{\"type\":\"string\",\"format\":\"date-time\"},\"chargingSchedule\":{\"type\":\"object\",\"properties\":{\"duration\":{\"type\":\"integer\"},\"startSchedule\":{\"type\":\"string\",\"format\":\"date-time\"},\"chargingRateUnit\":{\"type\":\"string\",\"enum\":[\"A\",\"W\"]},\"chargingSchedulePeriod\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"startPeriod\":{\"type\":\"integer\"},\"limit\":{\"type\":\"number\",\"multipleOf\":0.1},\"numberPhases\":{\"type\":\"integer\"}},\"required\":[\"startPeriod\",\"limit\"]}},\"minChargingRate\":{\"type\":\"number\",\"multipleOf\":0.1}},\"required\":[\"chargingRateUnit\",\"chargingSchedulePeriod\"]}},\"required\":[\"chargingProfileId\",\"stackLevel\",\"chargingProfilePurpose\",\"chargingProfileKind\",\"chargingSchedule\"]}},\"additionalProperties\":false,\"required\":[\"idTag\"]}";
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
            String remoteStopTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"RemoteStopTransactionRequest\",\"type\":\"object\",\"properties\":{\"transactionId\":{\"type\":\"integer\"}},\"additionalProperties\":false,\"required\":[\"transactionId\"]}";
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
            String reserveNowRequest = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ReserveNowRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"},\"expiryDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"idTag\":{\"type\":\"string\",\"maxLength\":20},\"parentIdTag\":{\"type\":\"string\",\"maxLength\":20},\"reservationId\":{\"type\":\"integer\"}},\"additionalProperties\":false,\"required\":[\"connectorId\",\"expiryDate\",\"idTag\",\"reservationId\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(reserveNowRequest);
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
            String resetRequest = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"ResetRequest\",\"type\":\"object\",\"properties\":{\"type\":{\"type\":\"string\",\"enum\":[\"Hard\",\"Soft\"]}},\"additionalProperties\":false,\"required\":[\"type\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(resetRequest);
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
            String sendLocalListRequest = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"SendLocalListRequest\",\"type\":\"object\",\"properties\":{\"listVersion\":{\"type\":\"integer\"},\"localAuthorizationList\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"idTag\":{\"type\":\"string\",\"maxLength\":20},\"idTagInfo\":{\"type\":\"object\",\"expiryDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"parentIdTag\":{\"type\":\"string\",\"maxLength\":20},\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Blocked\",\"Expired\",\"Invalid\",\"ConcurrentTx\"]}},\"required\":[\"status\"]}},\"required\":[\"idTag\"]}},\"updateType\":{\"type\":\"string\",\"enum\":[\"Differential\",\"Full\"]}},\"additionalProperties\":false,\"required\":[\"listVersion\",\"updateType\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(sendLocalListRequest);
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
            String setChargingProfileRequest = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"SetChargingProfileRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"},\"csChargingProfiles\":{\"type\":\"object\",\"properties\":{\"chargingProfileId\":{\"type\":\"integer\"},\"transactionId\":{\"type\":\"integer\"},\"stackLevel\":{\"type\":\"integer\"},\"chargingProfilePurpose\":{\"type\":\"string\",\"enum\":[\"ChargePointMaxProfile\",\"TxDefaultProfile\",\"TxProfile\"]},\"chargingProfileKind\":{\"type\":\"string\",\"enum\":[\"Absolute\",\"Recurring\",\"Relative\"]},\"recurrencyKind\":{\"type\":\"string\",\"enum\":[\"Daily\",\"Weekly\"]},\"validFrom\":{\"type\":\"string\",\"format\":\"date-time\"},\"validTo\":{\"type\":\"string\",\"format\":\"date-time\"},\"chargingSchedule\":{\"type\":\"object\",\"properties\":{\"duration\":{\"type\":\"integer\"},\"startSchedule\":{\"type\":\"string\",\"format\":\"date-time\"},\"chargingRateUnit\":{\"type\":\"string\",\"enum\":[\"A\",\"W\"]},\"chargingSchedulePeriod\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"startPeriod\":{\"type\":\"integer\"},\"limit\":{\"type\":\"number\",\"multipleOf\":0.1},\"numberPhases\":{\"type\":\"integer\"}},\"required\":[\"startPeriod\",\"limit\"]}},\"minChargingRate\":{\"type\":\"number\",\"multipleOf\":0.1}},\"required\":[\"chargingRateUnit\",\"chargingSchedulePeriod\"]}},\"required\":[\"chargingProfileId\",\"stackLevel\",\"chargingProfilePurpose\",\"chargingProfileKind\",\"chargingSchedule\"]}},\"additionalProperties\":false,\"required\":[\"connectorId\",\"csChargingProfiles\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(setChargingProfileRequest);
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
            String triggerMessageRequest = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"TriggerMessageRequest\",\"type\":\"object\",\"properties\":{\"requestedMessage\":{\"type\":\"string\",\"enum\":[\"BootNotification\",\"DiagnosticsStatusNotification\",\"FirmwareStatusNotification\",\"Heartbeat\",\"MeterValues\",\"StatusNotification\"]},\"connectorId\":{\"type\":\"integer\"}},\"additionalProperties\":false,\"required\":[\"requestedMessage\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(triggerMessageRequest);
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
            String unlockConnectorRequest = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"UnlockConnectorRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"}},\"additionalProperties\":false,\"required\":[\"connectorId\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(unlockConnectorRequest);
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
            String updateFirmwareRequest = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"UpdateFirmwareRequest\",\"type\":\"object\",\"properties\":{\"location\":{\"type\":\"string\",\"format\":\"uri\"},\"retries\":{\"type\":\"number\"},\"retrieveDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"retryInterval\":{\"type\":\"number\"}},\"additionalProperties\":false,\"required\":[\"location\",\"retrieveDate\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(updateFirmwareRequest);
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

    public class LocalBinder extends Binder {
        ServiceSendJSONCMSRequestToServer getServerInstance() {
            return ServiceSendJSONCMSRequestToServer.this;
        }
    }
}
