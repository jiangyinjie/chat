package com.george.chat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity
{

    private ListView listView = null;
    private ChatAdapter chatAdapter = null;
    private EditText editText = null;
    private Button button = null;
    private Handler handler = new Handler();
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView)findViewById(R.id.list_view);
        editText = (EditText)findViewById(R.id.text);
        button = (Button)findViewById(R.id.send);
        
        List<ChatMessage> list = new ArrayList<ChatMessage>();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setDate(new Date());
        chatMessage.setMessage("你好， 我是蒋蒋");
        chatMessage.setType(Type.INCOMING);
        
        chatAdapter = new ChatAdapter(list, this);
        listView.setAdapter(chatAdapter);
        
        button.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View v)
            {
                String text = editText.getText().toString();
                if (text.equals(""))
                {
                    Toast.makeText(MainActivity.this, "不能为空", 0);
                    return;
                }
                new HttpThread(handler, text).start();
            }
        });
        
        
    }
}
