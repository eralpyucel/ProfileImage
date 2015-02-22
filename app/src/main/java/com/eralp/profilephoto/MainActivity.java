package com.eralp.profilephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    Button button;
    ImageView imageView;

    private final static int ACTIVITY_PICK_IMAGE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        imageView= (ImageView) findViewById(R.id.imageView);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                selectFromGallery();
                break;
            default:
                break;
        }
    }

    private void selectFromGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, ACTIVITY_PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ACTIVITY_PICK_IMAGE:
                    set(data.toUri(0));
                    break;
                default:
                    break;
            }
        }
    }

    private void set(String url) {
        Bitmap b;
        try {
            b = PhotoFunctions.bitmapFromUri(this, Uri.parse(url));

             imageView.setImageBitmap(b);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


