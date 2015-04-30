package com.george.chat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.http.util.ByteArrayBuffer;

import android.os.Handler;
import android.util.Log;

import com.google.gson.Gson;

public class HttpThread extends Thread
{

    private final static String KEY = "51ff3ff72a3976abe10e1709f99742e7";
    private final static String HTTP_URL = "http://www.tuling123.com/openapi/api?key=";
    
    private Handler handler = null;
    private String text = null;
    
    
    public HttpThread(Handler handler, String text)
    {
        this.handler = handler;
        this.text = text;
    }
    
    @Override
    public void run()
    {
        super.run();
        
    }
    
    public static Response httpRequest(String text)
    {
        Response response = null;
        try
        {
            String url = HTTP_URL + KEY + "&info=" + URLEncoder.encode(text, "UTF-8");
            URL httpUrl = new URL(url);
            URLConnection conn = httpUrl.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            
            byte[] bytes = new byte[1024];
            ByteArrayBuffer buffer = new ByteArrayBuffer(0);
            int len = 0;
            while ((len = conn.getInputStream().read(bytes, 0, 1024)) != -1)
            {   
                buffer.append(bytes, 0, len);
            }
            Gson gson = new Gson();
            String string = new String(bytes, "UTF-8");
            Log.e("ddd", string);
            response = gson.fromJson(string, Response.class);
            Log.e("ddd", response.getText());
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return response;
    }

    
    
}
