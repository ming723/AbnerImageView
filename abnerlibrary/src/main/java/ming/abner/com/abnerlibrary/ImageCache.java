package ming.abner.com.abnerlibrary;

import android.graphics.Bitmap;

/**
 * author:AbnerMing
 * date:2018/10/30
 */
public interface ImageCache {

    //获取
    Bitmap get(String key);

    //添加
    void put(String key, Bitmap bitmap);

    //删除
    void remove(String key);


}
