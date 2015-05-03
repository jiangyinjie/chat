package com.george.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity
{

    private ListView listView = null;
    private ChatAdapter chatAdapter = null;
    private EditText editText = null;
    public static final int HTTP_MESSAGE = 1;
    private List<ChatMessage> list = new ArrayList<ChatMessage>();
    private Button button = null;
    private Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg)
        {
            switch (msg.what) {
            case HTTP_MESSAGE:
                Response response = (Response) msg.obj;
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMessage(response.getText());
                chatMessage.setType(Type.INCOMING);
                chatMessage.setDate(new Date());
                list.add(chatMessage);
                chatAdapter.notifyDataSetChanged();
                break;

            default:
                break;
            }
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);
        editText = (EditText)findViewById(R.id.text);
        button = (Button)findViewById(R.id.send);
        
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setDate(new Date());
        chatMessage.setMessage("你好， 我是蒋蒋");
        chatMessage.setType(Type.INCOMING);
        list.add(chatMessage);
        
        chatAdapter = new ChatAdapter(list, this);
        listView.setAdapter(chatAdapter);
        
        button.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                String text = editText.getText().toString();
                if (text.equals(""))
                {
                    Toast.makeText(MainActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                ChatMessage chatMessageSend = new ChatMessage();
                chatMessageSend.setDate(new Date());
                chatMessageSend.setMessage(text);
                chatMessageSend.setType(Type.OUTCOMING);
                list.add(chatMessageSend);
                chatAdapter.notifyDataSetChanged();
                new HttpThread(handler, text).start();
                editText.setText("");
            }
        });
        
        
    }
}
