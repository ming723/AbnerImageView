package ming.abner.com.abnerlibrary;

import android.graphics.Bitmap;

import java.io.InputStream;

/**
 * author:AbnerMing
 * date:2018/10/30
 */
public interface HttpListener {
    void success(Bitmap bitmap);

    void fail();
}
