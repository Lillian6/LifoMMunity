package com.markzhengma.android.lifommunity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class FullPostActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    private TextView posterDisplay;
    private TextView titleDisplay;
    private TextView contentDisplay;
    private ImageView imageView;
    private float textSize = 25;
    StorageReference mStorage;
    private PostData postData;

   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_post);

        posterDisplay = (TextView)findViewById(R.id.full_post_poster);
        titleDisplay = (TextView)findViewById(R.id.full_post_title);
        contentDisplay = (TextView)findViewById(R.id.full_post_content);
        imageView = findViewById(R.id.post_image_view);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("post");
//        mStorage = FirebaseStorage.getInstance().getReference();


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Picasso.get()
                        .load(dataSnapshot.getValue(PostData.class).imageId)
                        .into(imageView);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



//        mStorage.child("Post Image").child(uri.getLastPathSegment()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.get()
//                        .load("https://firebasestorage.googleapis.com/v0/b/lifommunity-d553e.appspot.com/o/Post%20Image%2Fimage%3A50%2FPost%20Image%2Fimage%3A50?alt=media&token=7d20acb9-201d-4c0b-8bf7-f56f2b6a6799")
//                        .into(postImageView);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(FullPostActivity.this, "display failed", Toast.LENGTH_SHORT).show();
//            }
//        });

        Intent intent = getIntent();
        PostData post = (PostData) intent.getSerializableExtra(Keys.POST_KEY);
        titleDisplay.setText(post.getTitleText());
        contentDisplay.setText(post.getContentText());

    }


    //inflates the xml file
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
//      return true;
        return super.onCreateOptionsMenu(menu);

    }

    //onOptionsItemSelected() handles menu item actions. Uses switch statement
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                Toast.makeText(this, "Shared", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.email:
                String title = ((TextView) findViewById(R.id.full_post_title)).getText().toString();
                String content = ((TextView) findViewById(R.id.full_post_content)).getText().toString();
                Toast.makeText(this,"Send email", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_SUBJECT, title);
                intent.putExtra(Intent.EXTRA_TEXT, content);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                return true;
            case R.id.Home_action:
                Intent homeIntent = new Intent(this, TabActivity.class);
                startActivity(homeIntent);
                return true;
            case R.id.Text_Size_Bigger:
                textSize ++;
                contentDisplay.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}


