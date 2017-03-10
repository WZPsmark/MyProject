package app.appservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import app.appservice.R;
import app.appservice.SimpleHttpServer;
import app.appservice.WebConfiguration;

public class MainActivity extends AppCompatActivity {

    private SimpleHttpServer shs;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = (ImageView) findViewById(R.id.image);

        WebConfiguration webConfiguration = new WebConfiguration();
        webConfiguration.setPort(8088);
        webConfiguration.setMaxParallels(50);
        shs = new SimpleHttpServer(webConfiguration);
        shs.registerResourceHandler(new ResourceInAssetsHandler(this));
        shs.registerResourceHandler(new UpLoadImageHandler(){
            @Override
            protected void onImageLoaded(String filePath) {
                showImage(filePath);
            }
        });
        shs.startAsync();
    }


    /**
     * 显示文件
     * @param filePath
     */
    private void showImage(final String filePath) {

       runOnUiThread(new Runnable() {
           @Override
           public void run() {
               Bitmap b = BitmapFactory.decodeFile(filePath);
               image.setImageBitmap(b);
               Toast.makeText(MainActivity.this, "Image show", Toast.LENGTH_SHORT).show();
           }
       });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shs.stopAsync();
    }
}
