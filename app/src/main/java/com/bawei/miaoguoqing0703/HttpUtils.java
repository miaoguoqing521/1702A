package com.bawei.miaoguoqing0703;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class HttpUtils {
    //懒汉式单例模式
    private static HttpUtils httpUtils=new HttpUtils();
    private HttpURLConnection connection;
    private Bitmap bitmap;

    private HttpUtils() {
    }
    public static HttpUtils getInstance(){
        return httpUtils;
    }

    public String getString(String strUrl){//请求数据
        try {
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code==200){
                InputStream stream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String str="";
                while ((str=bufferedReader.readLine())!=null){
                    buffer.append(str);
                }
                bufferedReader.close();

                return buffer.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.disconnect();
        return "";
    }
    public void getAsyncTask(String strUrl, final GetCallBack back){
        new AsyncTask<String, Integer, String>() {
            @Override
            protected String doInBackground(String... strings) {
                return getString(strings[0]);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                back.getData(s);
            }
        }.execute(strUrl);
    }
    public interface GetCallBack{
        void getData(String s);
    }
    //请求图片
    public Bitmap getBitmap(String strUrl){
        try {
            URL url = new URL(strUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int code = connection.getResponseCode();
            if (code==200){
                InputStream stream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(stream);
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public void getAsyncTaskA(String strUrl, final GetCallBackA backa){
        new AsyncTask<String, Void, Bitmap>() {

            @Override
            protected Bitmap doInBackground(String... strings) {
                return getBitmap(strings[0]);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                backa.getData(bitmap);
            }
        }.execute(strUrl);
    }
    public interface GetCallBackA{
        void getData(Bitmap bitmap);
    }
    //判断网络
    public boolean isNewWork(Context context){
        if (context!=null){
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info!=null){
                return info.isAvailable();
            }
        }
        return false;
    }
}
