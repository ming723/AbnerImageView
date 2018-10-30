package ming.abner.com.selfimageview1030;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * author:AbnerMing
 * date:2018/10/30
 * 网络请求
 */
public class AbnerDownLoad {
    private HttpListener listener;

    public AbnerDownLoad get(final String url) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setReadTimeout(5000);
                    int code = connection.getResponseCode();
                    Message message=Message.obtain();
                    if(code==HttpURLConnection.HTTP_OK){
                       InputStream inputStream= connection.getInputStream();
                        Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                       message.obj=bitmap;
                       message.what=100;
                    }else{
                        message.what=101;
                    }
                    handler.sendMessage(message);
                } catch (Exception e) {

                }
            }
        }.start();

//       final Message message=Message.obtain();
//        OkHttpClient client=new OkHttpClient();
//        Request request=new Request.Builder()
//                .url(url)
//                .build();
//        client.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                message.what=101;
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//               InputStream inputStream= response.body().byteStream();
//               message.obj=inputStream;
//                message.what=100;
//               handler.sendMessage(message);
//            }
//        });

        return this;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 100:
                    //设置给imageView
                    //添加到内存和磁盘当中
                    Bitmap bitmap = (Bitmap) msg.obj;
                    listener.success(bitmap);
                    break;
                case 101:
                    listener.fail();
                    break;
            }


        }
    };

    //传递接口
    public void result(HttpListener listener) {
        this.listener = listener;
    }
}
