package ming.abner.com.selfimageview1030;

import java.io.InputStream;

/**
 * author:AbnerMing
 * date:2018/10/30
 */
public interface HttpListener {
    void success(InputStream inputStream);

    void fail();
}
