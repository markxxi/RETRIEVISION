package com.example.retrievision;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Bitmap;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoundImageActivity extends AppCompatActivity {
    //image view variable, shows image inside the inteface
    private ImageView imageView;
    //
    private ActivityResultLauncher<Intent> thisIsCamera;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_image);
        imageView = findViewById(R.id.imageView3);

        thisIsCamera = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult o) {
                if(o.getResultCode() == RESULT_OK) {

                    Bitmap imageBitmap = BitmapFactory.decodeFile(path);

                    Bitmap resizedBitmap = resizeBitmap(imageBitmap, 4096, 4096);
                    imageView.setImageBitmap(resizedBitmap);

                    //the resizedBitmap is what will be scanned by AI MODEL
                    //will be called in the model

                }
            }
        });
        //kapag pinindot yung image tatawagin si function take picture
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                takePic();
            }
        });

    }
    //function for taking a photo and opening a camera
    private void takePic(){
        //method to open camera
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                //store photo taken to photofile
                photoFile = createImageFile();

            } catch (IOException ex) {
                ex.printStackTrace(); //error message
            }
            //if photofile has file
            if (photoFile != null) {
                //then save it
                Uri photoURI = FileProvider.getUriForFile(getApplicationContext(), getPackageName() + ".provider", photoFile);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                thisIsCamera.launch(intent);
            }
        }
    }

    private File createImageFile()  throws IOException{
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        path = image.getAbsolutePath();
        return image;
    }

    private Bitmap resizeBitmap(Bitmap bitmap, int maxWidth, int maxHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float ratioBitmap = (float) width / (float) height;
        float ratioMax = (float) maxWidth / (float) maxHeight;

        int finalWidth = maxWidth;
        int finalHeight = maxHeight;

        if (ratioMax > ratioBitmap) {
            finalWidth = (int) ((float)maxHeight * ratioBitmap);
        } else {
            finalHeight = (int) ((float)maxWidth / ratioBitmap);
        }
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, finalWidth, finalHeight, true);
        return resizedBitmap;
    }

}