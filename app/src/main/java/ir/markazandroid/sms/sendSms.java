package ir.markazandroid.sms;

import android.telephony.SmsManager;

/**
 * Created by sajad on 23/03/2016.
 */

//send sms and insert it to database
public class sendSms {
    static SmsManager smsmanager;

    public static void send(String phone,String text){
        smsmanager = SmsManager.getDefault();
        if (!phone.isEmpty() && !text.isEmpty()){
            smsmanager.sendTextMessage(phone, null, text, null, null);
            insertToDB(phone,text);
        }

    }
    private static void insertToDB(String sender,String message){
        long date=System.currentTimeMillis();
        SMSrsr.insertNew("Search name by number", sender, message,date);
    }

}
