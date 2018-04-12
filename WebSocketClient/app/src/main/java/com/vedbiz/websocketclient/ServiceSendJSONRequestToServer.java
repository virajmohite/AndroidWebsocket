package com.vedbiz.websocketclient;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceSendJSONRequestToServer extends Service {

    public IBinder mBinder = new LocalBinder();
    Utils utils;

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }


    public class LocalBinder extends Binder {
        public ServiceSendJSONRequestToServer getServerInstance() {
            return ServiceSendJSONRequestToServer.this;
        }
    }

    public String getJSONRequest(int flag) {

        utils = new Utils(getApplicationContext());

        Log.e("Get JSONString =>  ","Called");
        if (flag == 1 ){
            String authorizeRequest="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"AuthorizeRequest\",\"type\":\"object\",\"properties\":{\"idTag\":{\"type\":\"string\",\"maxLength\":20}},\"additionalProperties\":false,\"required\":[\"idTag\"]}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(authorizeRequest);
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
            String bootNotification="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"BootNotificationRequest\",\"type\":\"object\",\"properties\":{\"chargePointVendor\":{\"type\":\"string\",\"maxLength\":20},\"chargePointModel\":{\"type\":\"string\",\"maxLength\":20},\"chargePointSerialNumber\":{\"type\":\"string\",\"maxLength\":25},\"chargeBoxSerialNumber\":{\"type\":\"string\",\"maxLength\":25},\"firmwareVersion\":{\"type\":\"string\",\"maxLength\":50},\"iccid\":{\"type\":\"string\",\"maxLength\":20},\"imsi\":{\"type\":\"string\",\"maxLength\":20},\"meterType\":{\"type\":\"string\",\"maxLength\":25},\"meterSerialNumber\":{\"type\":\"string\",\"maxLength\":25}},\"additionalProperties\":false,\"required\":[\"chargePointVendor\",\"chargePointModel\"]}";

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
            String dataTransfer="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"DataTransferRequest\",\"type\":\"object\",\"properties\":{\"vendorId\":{\"type\":\"string\",\"maxLength\":255},\"messageId\":{\"type\":\"string\",\"maxLength\":50},\"data\":{\"type\":\"string\"}},\"additionalProperties\":false,\"required\":[\"vendorId\"]}";
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
            String diagnosticsStatusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"DiagnosticsStatusNotificationRequest\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Idle\",\"Uploaded\",\"UploadFailed\",\"Uploading\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
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
            String firmwareStatusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"FirmwareStatusNotificationRequest\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Downloaded\",\"DownloadFailed\",\"Downloading\",\"Idle\",\"InstallationFailed\",\"Installing\",\"Installed\"]}},\"additionalProperties\":false,\"required\":[\"status\"]}";
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
            String heartbeatRequest = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"HeartbeatRequest\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(heartbeatRequest);
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
            String  meterValues = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"MeterValuesRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"},\"transactionId\":{\"type\":\"integer\"},\"meterValue\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"timestamp\":{\"type\":\"string\",\"format\":\"date-time\"},\"sampledValue\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"value\":{\"type\":\"string\"},\"context\":{\"type\":\"string\",\"enum\":[\"Interruption.Begin\",\"Interruption.End\",\"Sample.Clock\",\"Sample.Periodic\",\"Transaction.Begin\",\"Transaction.End\",\"Trigger\",\"Other\"]},\"format\":{\"type\":\"string\",\"enum\":[\"Raw\",\"SignedData\"]},\"measurand\":{\"type\":\"string\",\"enum\":[\"Energy.Active.Export.Register\",\"Energy.Active.Import.Register\",\"Energy.Reactive.Export.Register\",\"Energy.Reactive.Import.Register\",\"Energy.Active.Export.Interval\",\"Energy.Active.Import.Interval\",\"Energy.Reactive.Export.Interval\",\"Energy.Reactive.Import.Interval\",\"Power.Active.Export\",\"Power.Active.Import\",\"Power.Offered\",\"Power.Reactive.Export\",\"Power.Reactive.Import\",\"Power.Factor\",\"Current.Import\",\"Current.Export\",\"Current.Offered\",\"Voltage\",\"Frequency\",\"Temperature\",\"SoC\",\"RPM\"]},\"phase\":{\"type\":\"string\",\"enum\":[\"L1\",\"L2\",\"L3\",\"N\",\"L1-N\",\"L2-N\",\"L3-N\",\"L1-L2\",\"L2-L3\",\"L3-L1\"]},\"location\":{\"type\":\"string\",\"enum\":[\"Cable\",\"EV\",\"Inlet\",\"Outlet\",\"Body\"]},\"unit\":{\"type\":\"string\",\"enum\":[\"Wh\",\"kWh\",\"varh\",\"kvarh\",\"W\",\"kW\",\"VA\",\"kVA\",\"var\",\"kvar\",\"A\",\"V\",\"K\",\"Celcius\",\"Fahrenheit\",\"Percent\"]}},\"required\":[\"value\"]}}},\"required\":[\"timestamp\",\"sampledValue\"]}}},\"additionalProperties\":false,\"required\":[\"connectorId\",\"meterValue\"]}";
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
            String startTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StartTransactionRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"},\"idTag\":{\"type\":\"string\",\"maxLength\":20},\"meterStart\":{\"type\":\"integer\"},\"reservationId\":{\"type\":\"integer\"},\"timestamp\":{\"type\":\"string\",\"format\":\"date-time\"}},\"additionalProperties\":false,\"required\":[\"connectorId\",\"idTag\",\"meterStart\",\"timestamp\"]}";
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
            String statusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StatusNotificationRequest\",\"type\":\"object\",\"properties\":{\"connectorId\":{\"type\":\"integer\"},\"errorCode\":{\"type\":\"string\",\"enum\":[\"ConnectorLockFailure\",\"EVCommunicationError\",\"GroundFailure\",\"HighTemperature\",\"InternalError\",\"LocalListConflict\",\"NoError\",\"OtherError\",\"OverCurrentFailure\",\"PowerMeterFailure\",\"PowerSwitchFailure\",\"ReaderFailure\",\"ResetFailure\",\"UnderVoltage\",\"OverVoltage\",\"WeakSignal\"]},\"info\":{\"type\":\"string\",\"maxLength\":50},\"status\":{\"type\":\"string\",\"enum\":[\"Available\",\"Preparing\",\"Charging\",\"SuspendedEVSE\",\"SuspendedEV\",\"Finishing\",\"Reserved\",\"Unavailable\",\"Faulted\"]},\"timestamp\":{\"type\":\"string\",\"format\":\"date-time\"},\"vendorId\":{\"type\":\"string\",\"maxLength\":255},\"vendorErrorCode\":{\"type\":\"string\",\"maxLength\":50}},\"additionalProperties\":false,\"required\":[\"connectorId\",\"errorCode\",\"status\"]}";
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
            String stopTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StopTransactionRequest\",\"type\":\"object\",\"properties\":{\"idTag\":{\"type\":\"string\",\"maxLength\":20},\"meterStop\":{\"type\":\"integer\"},\"timestamp\":{\"type\":\"string\",\"format\":\"date-time\"},\"transactionId\":{\"type\":\"integer\"},\"reason\":{\"type\":\"string\",\"enum\":[\"EmergencyStop\",\"EVDisconnected\",\"HardReset\",\"Local\",\"Other\",\"PowerLoss\",\"Reboot\",\"Remote\",\"SoftReset\",\"UnlockCommand\",\"DeAuthorized\"]},\"transactionData\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"timestamp\":{\"type\":\"string\",\"format\":\"date-time\"},\"sampledValue\":{\"type\":\"array\",\"items\":{\"type\":\"object\",\"properties\":{\"value\":{\"type\":\"string\"},\"context\":{\"type\":\"string\",\"enum\":[\"Interruption.Begin\",\"Interruption.End\",\"Sample.Clock\",\"Sample.Periodic\",\"Transaction.Begin\",\"Transaction.End\",\"Trigger\",\"Other\"]},\"format\":{\"type\":\"string\",\"enum\":[\"Raw\",\"SignedData\"]},\"measurand\":{\"type\":\"string\",\"enum\":[\"Energy.Active.Export.Register\",\"Energy.Active.Import.Register\",\"Energy.Reactive.Export.Register\",\"Energy.Reactive.Import.Register\",\"Energy.Active.Export.Interval\",\"Energy.Active.Import.Interval\",\"Energy.Reactive.Export.Interval\",\"Energy.Reactive.Import.Interval\",\"Power.Active.Export\",\"Power.Active.Import\",\"Power.Offered\",\"Power.Reactive.Export\",\"Power.Reactive.Import\",\"Power.Factor\",\"Current.Import\",\"Current.Export\",\"Current.Offered\",\"Voltage\",\"Frequency\",\"Temperature\",\"SoC\",\"RPM\"]},\"phase\":{\"type\":\"string\",\"enum\":[\"L1\",\"L2\",\"L3\",\"N\",\"L1-N\",\"L2-N\",\"L3-N\",\"L1-L2\",\"L2-L3\",\"L3-L1\"]},\"location\":{\"type\":\"string\",\"enum\":[\"Cable\",\"EV\",\"Inlet\",\"Outlet\",\"Body\"]},\"unit\":{\"type\":\"string\",\"enum\":[\"Wh\",\"kWh\",\"varh\",\"kvarh\",\"W\",\"kW\",\"VA\",\"kVA\",\"var\",\"kvar\",\"A\",\"V\",\"K\",\"Celcius\",\"Fahrenheit\",\"Percent\"]}},\"required\":[\"value\"]}}},\"required\":[\"timestamp\",\"sampledValue\"]}}},\"additionalProperties\":false,\"required\":[\"transactionId\",\"timestamp\",\"meterStop\"]}";
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