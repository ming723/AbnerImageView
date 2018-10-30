package ming.abner.com.abnerlibrary;

import android.graphics.Bitmap;
import android.util.LruCache;

/**
 * author:AbnerMing
 * date:2018/10/30
 * 内存管理类
 */
public class AbnerMemoryCache implements ImageCache{
    private LruCache<String,Bitmap> mMemoryCache;

    public AbnerMemoryCache(){
        int size= (int) Runtime.getRuntime().maxMemory();
        int memoryZize=size/4;
        mMemoryCache=new LruCache<String,Bitmap>(memoryZize){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }

    @Override
    public Bitmap get(String key) {
        return mMemoryCache.get(getKey(key));
    }

    @Override
    public void put(String key, Bitmap bitmap) {
        mMemoryCache.put(getKey(key),bitmap);
    }

    @Override
    public void remove(String key) {
        mMemoryCache.remove(getKey(key));
    }

    private String getKey(String key){
      return   MD5Util.hashKeyFromUrl(key);
    }
}
