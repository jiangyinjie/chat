package com.george.chat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChatAdapter extends BaseAdapter
{

    private List<ChatMessage> list = new ArrayList<ChatMessage>();
    private LayoutInflater inflater;
    
    public ChatAdapter(List<ChatMessage> list, Context context)
    {
        this.list = list;
        inflater = LayoutInflater.from(context);
    }
    
    
    @Override
    public int getCount()
    {
        return list.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        if (list.get(position).getType() == Type.INCOMING)
        {
            return 0;
        }
        return 1;
    }


    @Override
    public int getViewTypeCount()
    {
        return 2;
    }


    @Override
    public Object getItem(int position)
    {
        return list.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder = null;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            if (getItemViewType(position) == 0)
            {
                convertView = inflater.inflate(R.layout.chat_left_item, null);
                viewHolder.date = (TextView)convertView.findViewById(R.id.chat_left_date);
                viewHolder.image = (ImageView)convertView.findViewById(R.id.chat_left_image);
                viewHolder.user = (TextView)convertView.findViewById(R.id.chat_left_user);
                viewHolder.message = (TextView)convertView.findViewById(R.id.chat_left_message);
            }
            else 
            {
                convertView = inflater.inflate(R.layout.chat_right_item, null);
                viewHolder.date = (TextView)convertView.findViewById(R.id.chat_right_date);
                viewHolder.image = (ImageView)convertView.findViewById(R.id.chat_right_image);
                viewHolder.user = (TextView)convertView.findViewById(R.id.chat_right_user);
                viewHolder.message = (TextView)convertView.findViewById(R.id.chat_right_message);
            }
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        
        ChatMessage chatMessage = (ChatMessage)getItem(position);
        
        viewHolder.date.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA).format(chatMessage.getDate()));
        viewHolder.message.setText(chatMessage.getMessage());
        
        return convertView;
    }
    
    class ViewHolder
    {
        TextView date;
        ImageView image;
        TextView user;
        TextView message;
    }

}
