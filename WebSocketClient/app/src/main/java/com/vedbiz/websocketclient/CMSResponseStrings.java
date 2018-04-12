package com.vedbiz.websocketclient;

/**
 * Created by User on 30-01-2018.
 **/

public class CMSResponseStrings {

   final public static String authorizeResponse="{\"title\":\"AuthorizeResponse\",\"status\":\"Accepted\",\"expiryDate\":\"2018-02-01 18:06:23\",\"parentIdTag\":\"xyzabc\"}";
   final public static String bootNotification="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"BootNotificationResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Pending\",\"Rejected\"]},\"currentTime\":{\"type\":\"string\",\"format\":\"date-time\"},\"interval\":{\"type\":\"number\"}},\"additionalProperties\":false,\"required\":[\"status\",\"currentTime\",\"interval\"]}";
   final public static String dataTransfer="{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"DataTransferResponse\",\"type\":\"object\",\"properties\":{\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Rejected\",\"UnknownMessageId\",\"UnknownVendorId\"]},\"data\":{\"type\":\"string\"}},\"additionalProperties\":false,\"required\":[\"status\"]}";
   final public static String diagnosticsStatusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"DiagnosticsStatusNotificationResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
   final public static String firmwareStatusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"FirmwareStatusNotificationResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
   final public static String heartbeatResponse = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"HeartbeatResponse\",\"type\":\"object\",\"properties\":{\"currentTime\":{\"type\":\"string\",\"format\":\"date-time\"}},\"additionalProperties\":false,\"required\":[\"currentTime\"]}";
   final public static String  meterValues = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"MeterValuesResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
   final public static String startTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StartTransactionResponse\",\"type\":\"object\",\"properties\":{\"idTagInfo\":{\"type\":\"object\",\"properties\":{\"expiryDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"parentIdTag\":{\"type\":\"string\",\"maxLength\":20},\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Blocked\",\"Expired\",\"Invalid\",\"ConcurrentTx\"]}},\"required\":[\"status\"]},\"transactionId\":{\"type\":\"integer\"}},\"additionalProperties\":false,\"required\":[\"idTagInfo\",\"transactionId\"]}";
   final public static String statusNotification = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StatusNotificationResponse\",\"type\":\"object\",\"properties\":{},\"additionalProperties\":false}";
   final public static String stopTransaction = "{\"$schema\":\"http://json-schema.org/draft-04/schema#\",\"title\":\"StopTransactionResponse\",\"type\":\"object\",\"properties\":{\"idTagInfo\":{\"type\":\"object\",\"properties\":{\"expiryDate\":{\"type\":\"string\",\"format\":\"date-time\"},\"parentIdTag\":{\"type\":\"string\",\"maxLength\":20},\"status\":{\"type\":\"string\",\"enum\":[\"Accepted\",\"Blocked\",\"Expired\",\"Invalid\",\"ConcurrentTx\"]}},\"required\":[\"status\"]}},\"additionalProperties\":false}";

}
