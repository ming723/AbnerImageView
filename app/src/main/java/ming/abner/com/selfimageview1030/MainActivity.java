package ming.abner.com.selfimageview1030;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    private AbnerImageView mAbnerImageView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAbnerImageView=(AbnerImageView)findViewById(R.id.abnerIamge);
        imageView=(ImageView) findViewById(R.id.image_laod);
        mAbnerImageView.loadImage("http://img.huxiucdn.com/article/cover/201810/29/184225341752.jpg");
        new AbnerDownLoad().get("http://img.huxiucdn.com/article/cover/201810/29/184225341752.jpg").result(new HttpListener() {
            @Override
            public void success(InputStream inputStream) {
              Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void fail() {

            }
        });
    }
}
