package ir.markazandroid.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ir.markazandroid.secretsms.R;

import java.util.ArrayList;

/**
 * Created by Ali on 17/03/2016.
 */
public class ListAdaptor extends ArrayAdapter<Messages> {

    /**
     * Constructor
     *
     * @param context  The current context.
     *
     */
    private ArrayList<Messages> message;
    Context context;
    public static String DOT = "...";

    public ListAdaptor(Context context,ArrayList<Messages> messages) {
        super(context, R.layout.messages,messages);
        this.context=context;
        this.message=messages;
    }
    private static class ViewHolder {
        ImageView iconImageView;
        TextView nameLabel;
        TextView timeLabel;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.messages, null);
            holder = new ViewHolder();
            holder.iconImageView = (ImageView)convertView.findViewById(R.id.messageIcon);
            holder.nameLabel = (TextView)convertView.findViewById(R.id.senderLabel);
            holder.timeLabel = (TextView)convertView.findViewById(R.id.timeLabel);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        try {

            Messages msg = message.get(position);
            String s = msg.getMassages(0);
            int i = s.length();
            if(i>20){
                i =20;
                holder.timeLabel.setText(s.substring(0,i)+DOT);
            }
            else holder.timeLabel.setText(s.substring(0,i));



            holder.nameLabel.setText(msg.getNumber());
        }
        catch (Exception e){}

        return convertView;
    }



}
