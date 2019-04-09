package com.mysterium.a1pra.helpinghand.myblog;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.mysterium.a1pra.helpinghand.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class NewBlogActivity extends AppCompatActivity {
	private final int PICK_IMAGE_REQUEST = 71;
	EditText addBlogTitle;
	TextView title;
	SharedPreferences sharedPreferences;
	Button publishBlog, pickImageBlog, clearBlog;
	String newBlogTitle, key, url;
	Uri newBlogURL;
	LinearLayout linearLayout;
	View newBlogScreen;
	ImageView image;
	StorageReference storageReference;
	DatabaseReference mDatabase;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_blog);
		Window w = getWindow(); // in Activity's onCreate() for instance
		w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		sharedPreferences = getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
		final SharedPreferences.Editor editor = sharedPreferences.edit();
		linearLayout = findViewById(R.id.new_blog);
		int darkTheme = sharedPreferences.getInt("darkTheme", 0);
		if (darkTheme == 1) {//change theme to dark.
			linearLayout.setBackgroundResource(R.drawable.gradientdark);
		} else if (darkTheme == 2) {
			Calendar c = Calendar.getInstance();
			SimpleDateFormat df = new SimpleDateFormat("HH");
			String time = df.format(c.getTime());
			int check = Integer.parseInt(time);
			if (5 <= check && check < 11) {
				linearLayout.setBackgroundResource(R.drawable.gradientmorning);
			} else if (11 <= check && check < 16) {
				linearLayout.setBackgroundResource(R.drawable.gradientnoon);
			} else if (16 <= check && check < 19) {
				linearLayout.setBackgroundResource(R.drawable.gradientevening);
			} else {
				linearLayout.setBackgroundResource(R.drawable.gradientnight);
			}


		} else {
			//set theme bright.
			linearLayout.setBackgroundResource(R.drawable.gradient);
		}


		addBlogTitle = findViewById(R.id.add_blog_title);
		publishBlog = findViewById(R.id.btn_publish_blog);
		clearBlog = findViewById(R.id.btn_clear_blog);
		pickImageBlog = findViewById(R.id.btn_pick_image_blog);
		newBlogScreen = findViewById(R.id.new_blog);
		image = findViewById(R.id.imgView);
		title = findViewById(R.id.newblogtitle);

		try {
			newBlogTitle = getIntent().getStringExtra("blogtitle");
			key = getIntent().getStringExtra("blogkey");
			url = getIntent().getStringExtra("blogurl");
			addBlogTitle.setText(newBlogTitle);
			if (!(url.equals(null))) {
				title.setText("Edit Blog");
				image.setVisibility(View.VISIBLE);
				Glide.with(NewBlogActivity.this).load(url).into(image);
			}
		} catch (Exception e) {
			newBlogTitle = "";
			url = "";
			key = "";
		}


		storageReference = FirebaseStorage.getInstance().getReference();
		mDatabase = FirebaseDatabase.getInstance().getReference();

		pickImageBlog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				chooseImage();
			}
		});

		publishBlog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				uploadImage();
			}
		});

		clearBlog.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				addBlogTitle.setText("");
				image.setVisibility(View.GONE);
				publishBlog.setVisibility(View.GONE);
				pickImageBlog.setVisibility(View.VISIBLE);
			}
		});

	}

	private void uploadImage() {

		newBlogTitle = addBlogTitle.getText().toString();
		if (newBlogURL != null && newBlogTitle != null) {
			final ProgressDialog progressDialog = new ProgressDialog(this);
			progressDialog.setTitle("Uploading...");
			progressDialog.setCancelable(false);
			progressDialog.show();

			final StorageReference ref = storageReference.child(UUID.randomUUID().toString());
			ref.putFile(newBlogURL)
					.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
						@Override
						public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
							progressDialog.dismiss();
							ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
								@Override
								public void onSuccess(Uri uri) {
									Uri downloaduri = uri;
									if (key.equals(null) || key.equals("")) {
										key = mDatabase.child("blogs").push().getKey();
									}
									mDatabase.child("blogs").child(key).child("imageURL").setValue(downloaduri.toString());
									mDatabase.child("blogs").child(key).child("imageTitle").setValue(newBlogTitle);

								}
							});
							Toast.makeText(NewBlogActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
							Intent intent = new Intent(NewBlogActivity.this, MyBlogActivity.class);
							startActivity(intent);
							finish();
						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							progressDialog.dismiss();
							Toast.makeText(NewBlogActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
						}
					})
					.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
						@Override
						public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
							double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

							progressDialog.setMessage("Uploaded " + (int) progress + "%");
						}
					});

		}

	}

	private void chooseImage() {
		newBlogTitle = addBlogTitle.getText().toString();
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
				&& data != null && data.getData() != null) {
			publishBlog.setVisibility(View.VISIBLE);
			pickImageBlog.setVisibility(View.GONE);
			newBlogURL = data.getData();
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), newBlogURL);
				image.setVisibility(View.VISIBLE);
				image.setImageBitmap(bitmap);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent msg) {

		switch (keyCode) {
			case (KeyEvent.KEYCODE_BACK):
				Intent a1_intent = new Intent(this, MyBlogActivity.class);
				startActivity(a1_intent);
				finish();
				return true;


		}
		return false;
	}
}
