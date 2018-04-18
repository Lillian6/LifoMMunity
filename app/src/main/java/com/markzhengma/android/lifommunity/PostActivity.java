package com.markzhengma.android.lifommunity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;

public class PostActivity extends AppCompatActivity {
    private Button homeBtn;
    private Button profileBtn;
    private Button postBtn;
    private Button submitPostBtn;
    private Button addImageBtn;
    private Button cameraBtn;
    private EditText titleTextView;
    private EditText contentTextView;

    private String titleText;
    private String contentText;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference postRef = database.getReference("post");
    private DatabaseReference picRef = database.getReference("picture");
    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener authStateListener;

    private static final int RC_PHOTO_PICKER = 1;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        homeBtn = findViewById(R.id.home_btn);
        postBtn = findViewById(R.id.post_btn);
        profileBtn = findViewById(R.id.profile_btn);

        titleTextView = findViewById(R.id.post_title_edit_text);
        contentTextView = findViewById(R.id.post_content_edit_text);
        submitPostBtn = findViewById(R.id.submit_post_btn);
        addImageBtn = findViewById(R.id.add_image_button);
        cameraBtn = findViewById(R.id.camera_button);
        titleText = titleTextView.getText().toString();
        contentText = contentTextView.getText().toString();
        imageView = findViewById(R.id.post_image_view);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null)
                    startActivity(new Intent(PostActivity.this, LoginActivity.class));
            }
        };

        setHomeBtnListener();
        setProfileBtnListener();
        setSubmitPostListener();
        setCameraBtnListener();
        setAddImageBtnListener();
    }


    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override

    public void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }


    public void setHomeBtnListener() {
        homeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadMainActivity();
            }
        });
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    public void setProfileBtnListener(){
        profileBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                loadProfileActivity();
            }
        });
    }

    private void loadProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void getPostData(){
        titleText = titleTextView.getText().toString();
        contentText = contentTextView.getText().toString();
    }

    private void setPostData(){
        PostData newPost = new PostData(titleText, contentText);
        Intent intent = new Intent();
        intent.putExtra("NEW_POST", newPost);
        setResult(RESULT_OK, intent);
        finish();
    }



    private void setSubmitPostListener(){

        submitPostBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                getPostData();
                DatabaseReference userPostRef = postRef.child(titleText);
                userPostRef.setValue(contentText);
                setPostData();
            }
        });
    }

    //I want to allow the user to upload his or her local image and save the image to the firebase as well as the main page
    private void setAddImageBtnListener(){
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });
    }

    private void selectImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), RC_PHOTO_PICKER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) return;

        if (requestCode == RC_PHOTO_PICKER) {
            Uri photoUrl = data.getData();
            try {
                decodeUri(photoUrl);
                picRef.setValue(ImageUtil.bitmapToByteString(((BitmapDrawable) imageView.getDrawable()).getBitmap())); // Save image to Firebase
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "Error decoding photo", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Compress and then display the image
    private void decodeUri(Uri uri) throws FileNotFoundException {

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();

        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image so it's not bigger than 500 x 500 pixels
        int scaleFactor = (int) Math.ceil(Math.min(photoW / 500, photoH / 500));

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;

        // Create the compressed bitmap and load it to the imageView
        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, bmOptions);
        imageView.setImageBitmap(bitmap);
    }





    //I want to make the picture that the camera took to be saved into firebase as well as the main page
    private void setCameraBtnListener(){
        cameraBtn.setOnClickListener(new View.OnClickListener() {
            static final int REQUEST_IMAGE_CAPTURE = 1;
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

}
