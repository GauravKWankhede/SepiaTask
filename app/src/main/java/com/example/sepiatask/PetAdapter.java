package com.example.sepiatask;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.ViewHolder> {

    Context context;
    ArrayList<PetInfo> list;

    public PetAdapter(Context context, ArrayList<PetInfo> list) {
        this.context=context;
        this.list=list;
    }

    @NonNull
    @Override
    public PetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PetAdapter.ViewHolder holder, int position) {
        PetInfo info = list.get(position);
        holder.petName.setText(info.getTitle());
        Glide.with(context)
                .load(info.getImage_url())
                .centerInside()
                .into(holder.petImage);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,PetDetailsActivity.class);
                intent.putExtra("title", info.getTitle());
                intent.putExtra("image_url", info.getImage_url());
                intent.putExtra("content_url", info.getContent_url());
                intent.putExtra("date_added", info.getDate_added());

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        
        ImageView petImage;
        TextView petName;
        LinearLayout layout;
        
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            petImage = itemView.findViewById(R.id.petImage);
            petName =itemView.findViewById(R.id.petName);
            layout = itemView.findViewById(R.id.rowLayout);

            itemView.setOnClickListener(view -> {
                Intent intent = new Intent(view.getContext(),PetDetailsActivity.class);
                intent.putExtra("position", getAbsoluteAdapterPosition()+"");
                view.getContext().startActivity(intent);
            });
        }
    }
}
