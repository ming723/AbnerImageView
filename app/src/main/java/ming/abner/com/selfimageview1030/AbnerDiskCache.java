package ming.abner.com.selfimageview1030;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * author:AbnerMing
 * date:2018/10/30
 * 磁盘管理类
 */
public class AbnerDiskCache implements ImageCache {


    @Override
    public Bitmap get(String key) {
        String url=getPath(key);
        return BitmapFactory.decodeFile(url);
    }

    @Override
    public void put(String key, Bitmap bitmap) {

    }


    //添加到SD卡当中
    public void put(String key, InputStream inputStream) {
        FileOutputStream fileOutputStream=null;
        try {
            String path = getPath(key);
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }

            int len=-1;
            byte[] bytes=new byte[1024];
             fileOutputStream=new FileOutputStream(file);
            while ((len=inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
                fileOutputStream.flush();
            }
        } catch (Exception e) {
        }finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remove(String key) {
        File file=new File(getPath(key));
        if(file.exists()){
            file.delete();
        }
    }

    //获取SD卡路径
    private String getPath(String url) {
        url = MD5Util.hashKeyFromUrl(url);
        return Environment.getExternalStorageDirectory().getAbsolutePath() + url + ".png";
    }
}
