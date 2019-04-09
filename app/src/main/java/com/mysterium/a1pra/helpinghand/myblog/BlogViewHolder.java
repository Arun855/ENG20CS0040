package com.mysterium.a1pra.helpinghand.myblog;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mysterium.a1pra.helpinghand.R;

import androidx.recyclerview.widget.RecyclerView;

public class BlogViewHolder extends RecyclerView.ViewHolder {
	Context applicationContext = MyBlogActivity.getContextOfApplication();
	StorageReference storageReference;
	DatabaseReference mDatabase;
	private TextView blogTitleTV;
	private ImageView blogImage;
	private Button editBlogB, saveBlogB, deleteBlogB, selectNewImageB;
	private String title, imageURL, key;

	public BlogViewHolder(View itemView) {
		super(itemView);
		blogTitleTV = itemView.findViewById(R.id.blog_tv_title);
		editBlogB = itemView.findViewById(R.id.btn_editBlog);
		saveBlogB = itemView.findViewById(R.id.btn_saveBlog);
		deleteBlogB = itemView.findViewById(R.id.btn_deleteBlog);
		selectNewImageB = itemView.findViewById(R.id.btn_addImage);
		blogImage = itemView.findViewById(R.id.blog_image);
	}

	public void populate(BlogModel blogItem, final int position) {
		title = blogItem.getBlogTitle();
		imageURL = blogItem.getBlogImageURL();
		key = blogItem.getKey();
		storageReference = FirebaseStorage.getInstance().getReference();
		mDatabase = FirebaseDatabase.getInstance().getReference();

		blogTitleTV.setText(title);
		Glide.with(applicationContext).load(imageURL).into(blogImage);

		editBlogB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(applicationContext, NewBlogActivity.class);
				intent.putExtra("blogtitle", title);
				intent.putExtra("blogkey", key);
				intent.putExtra("blogurl", imageURL);
				applicationContext.startActivity(intent);
				/*InputMethodManager imm = (InputMethodManager)applicationContext.getSystemService(applicationContext.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
				blogTitleTV.setVisibility(View.GONE);
				blogTitleET.setVisibility(View.VISIBLE);

				editBlogB.setVisibility(View.GONE);
				selectNewImageB.setVisibility(View.VISIBLE);
				saveBlogB.setVisibility(View.VISIBLE);

				blogTitleET.setFocusableInTouchMode(true);
				blogTitleET.requestFocus();

				selectNewImageB.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						chooseImage();
					}
				});

				saveBlogB.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {

						uploadImage();
						MyBlogActivity.activity.recreate();

						InputMethodManager imm = (InputMethodManager)applicationContext.getSystemService(applicationContext.INPUT_METHOD_SERVICE);

						imm.hideSoftInputFromWindow(viewLinearLayout.getWindowToken(), 0);

						blogTitleTV.setVisibility(View.VISIBLE);
						blogTitleET.setVisibility(View.GONE);

						editBlogB.setVisibility(View.VISIBLE);
						selectNewImageB.setVisibility(View.GONE);
						saveBlogB.setVisibility(View.GONE);



					}
				});*/
			}
		});


		deleteBlogB.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				mDatabase.child("blogs").child(key).child("imageTitle").removeValue();
				mDatabase.child("blogs").child(key).child("imageURL").removeValue();
				MyBlogActivity.activity.recreate();
			}
		});


	}

	/*private void uploadImage() {

		title=blogTitleET.getText().toString();
		if(newBlogURL != null && title!=null)
		{
			final ProgressDialog progressDialog = new ProgressDialog(applicationContext);
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
									mDatabase.child("blogs").child(key).child("imageURL").setValue(downloaduri.toString());
									mDatabase.child("blogs").child(key).child("imageTitle").setValue(title);

								}
							});
							Toast.makeText(applicationContext, "Uploaded", Toast.LENGTH_SHORT).show();
						}
					})
					.addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							progressDialog.dismiss();
							Toast.makeText(applicationContext, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
						}
					})
					.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
						@Override
						public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
							double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();

							progressDialog.setMessage("Uploaded "+(int)progress+"%");
						}
					});

		}

	}

	private void chooseImage()
	{
		title=blogTitleET.getText().toString();
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);
		((Activity) applicationContext).startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
				&& data != null && data.getData() != null )
		{
			newBlogURL = data.getData();
			try {
				Bitmap bitmap = MediaStore.Images.Media.getBitmap(applicationContext.getContentResolver(), newBlogURL);
				blogImage.setVisibility(View.VISIBLE);
				blogImage.setImageBitmap(bitmap);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}*/
}
