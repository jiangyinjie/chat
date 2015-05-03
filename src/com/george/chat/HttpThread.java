package com.george.chat;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.util.ByteArrayBuffer;

import android.os.Handler;
import android.os.Message;
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
        Response response = httpRequest(text);

        Message message = Message.obtain();
        message.what = MainActivity.HTTP_MESSAGE;
        message.obj = response;
        handler.sendMessage(message);
        
    }
    
    public static Response httpRequest(String text)
    {
        Response response = null;
        try {
            String url = HTTP_URL + KEY + "&info=" + URLEncoder.encode(text, "UTF-8");
            URL httpUrl = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setReadTimeout(5 * 1000);
            conn.setRequestMethod("GET");

            byte[] bytes = new byte[1024];

            ByteArrayBuffer buffer = new ByteArrayBuffer(0);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len = -1;
            while ((len = conn.getInputStream().read(bytes)) != -1) {
                // baos.write(bytes, 0, len);
                buffer.append(bytes, 0, len);

            }
            // baos.flush();

            Gson gson = new Gson();
            String string = new String(buffer.toByteArray());
            Log.e("ddd", string);
            response = gson.fromJson(string, Response.class);
            Log.e("ddd", response.getText());
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;
    }

    
    
}
