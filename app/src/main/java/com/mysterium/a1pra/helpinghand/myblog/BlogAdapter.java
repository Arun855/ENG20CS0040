package com.mysterium.a1pra.helpinghand.myblog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mysterium.a1pra.helpinghand.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BlogAdapter extends RecyclerView.Adapter<BlogViewHolder> implements ValueEventListener {

	List<BlogModel> blogList = new ArrayList<>();
	private DatabaseReference reference;

	public BlogAdapter() {
		blogList = new ArrayList<>();
		reference = FirebaseDatabase.getInstance().getReference();
		reference.addValueEventListener(this);
	}

	@NonNull
	@Override
	public BlogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());

		View view = inflater.inflate(R.layout.view_blog, parent, false);
		BlogViewHolder holder = new BlogViewHolder(view);
		return holder;
	}

	@Override
	public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
		BlogModel blogItem = blogList.get(position);
		holder.populate(blogItem, position);
	}

	@Override
	public int getItemCount() {
		return blogList.size();
	}

	@Override
	public void onDataChange(DataSnapshot dataSnapshot) {
		blogList = new ArrayList<>();
		for (DataSnapshot c : dataSnapshot.child("blogs").getChildren()) {
			blogList.add(new BlogModel(c.getKey().toString(), c.child("imageTitle").getValue(String.class), c.child("imageURL").getValue(String.class)));
		}
		notifyDataSetChanged();


	}

	@Override
	public void onCancelled(DatabaseError databaseError) {

	}

}
