package ming.abner.com.abnerlibrary;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * author:AbnerMing
 * date:2018/10/30
 */
public class AbnerImageView extends ImageView{
    private AbnerMemoryCache abnerMemoryCache;
    private AbnerDiskCache mAbnerDiskCache;

    public AbnerImageView(Context context) {
        super(context);
        init(context);
    }

    public AbnerImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AbnerImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    //初始化
    private void init(Context context) {
         abnerMemoryCache=new AbnerMemoryCache();
         mAbnerDiskCache=new AbnerDiskCache();
    }

    //加载网上资源
    public void loadImage(String url){
        //三级缓存，先从内存中取，然后再从磁盘中，然后再从网上

        setTag(url);

        //从内存当中取
        Bitmap bitmap=abnerMemoryCache.get(url);
        if(bitmap!=null){
            setImageBitmap(bitmap);
            return;
        }
        //从磁盘中取
        Bitmap bitmap1=mAbnerDiskCache.get(url);
        if(bitmap1==null){
            //从网络当中取
            doHttp(url);
            return;
        }

        abnerMemoryCache.put(url,bitmap1);

        String urlImage= (String) getTag();
        if(TextUtils.equals(url,urlImage)){
            setImageBitmap(bitmap1);
        }else{
            //错误
        }
    }

    //从网络获取
    private void doHttp(final String url) {
        new AbnerDownLoad().get(url).result(new HttpListener() {
            @Override
            public void success(Bitmap bitmap) {
              setImageBitmap(bitmap);
              //添加到内存和磁盘当中
                abnerMemoryCache.put(url,bitmap);
                mAbnerDiskCache.put(url,bitmap);
            }

            @Override
            public void fail() {

            }
        });
    }

    //加载本地
    public void loadImage(Bitmap bitmap){
        setImageBitmap(bitmap);
    }

    public void fit(){
        setScaleType(ScaleType.FIT_XY);
    }
}
