package com.newboston.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Chris on 16.09.13.
 */
public class Camera extends Activity implements View.OnClickListener{
    ImageButton ib;
    Button b;
    ImageView iv;
    Intent i;
    final int cameraData = 0;
    Bitmap bmp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);
        init();
        InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
        bmp = BitmapFactory.decodeStream(is);
    }

    private void init(){
        iv = (ImageView) findViewById(R.id.ivReturnedPicture);
        ib = (ImageButton) findViewById(R.id.ibTakePic);
        b = (Button) findViewById(R.id.bSetWall);
        b.setOnClickListener(this);
        ib.setOnClickListener(this);
    }

    private void takePic() {
        i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, cameraData);
    }

    private void setWall() {
        try {
            getApplicationContext().setWallpaper(bmp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            bmp = (Bitmap) extras.get("data");
            iv.setImageBitmap(bmp);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bSetWall:
                setWall();
                break;
            case R.id.ibTakePic:
                takePic();
                break;
        }
    }
}
