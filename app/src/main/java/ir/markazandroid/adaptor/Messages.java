package ir.markazandroid.adaptor;


import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ali on 17/03/2016.
 */
public class Messages  {

    private String number;
    private ArrayList<String> massages;
    private ArrayList<Date> date;

    public Messages(String number,ArrayList<String> messages) {
        this.number = number;
        massages = messages;
        date = new ArrayList<Date>();

    }
    public Messages(String number,String message){
        this.number= number;
        massages = new ArrayList<String>();
        massages.add(message);
    }

    public Date getDate(int i) {
        return date.get(i);
    }

    public void setDate(ArrayList<Date> date) {
        this.date = date;
    }

    public String getMassages(int i) {
        return massages.get(i);
    }

    public void setMassages(ArrayList<String> massages) {
        this.massages = massages;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


}