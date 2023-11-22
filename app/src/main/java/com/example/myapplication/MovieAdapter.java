package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    private Context mContext;

    private MovieOnClickListener movieOnClickListener;


    private List<MovieModelClass> movieModelClassList;

    public MovieAdapter(Context mContext, List<MovieModelClass> movieModelClassList) {
        this.mContext = mContext;
        this.movieModelClassList = movieModelClassList;
        //notifyDataSetChanged();

    }

    public void setFilterListItems(List<MovieModelClass>movieModelClassListFilter){
        this.movieModelClassList = movieModelClassListFilter;
        notifyDataSetChanged();
    }




    public MovieAdapter setMovieOnClickListener(MovieOnClickListener movieOnClickListener) {
        this.movieOnClickListener = movieOnClickListener;
        return this;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.date.setText(movieModelClassList.get(position).getReleaseDate());


        holder.txt_id.setText(movieModelClassList.get(position).getId());

        holder.txt_name.setText(movieModelClassList.get(position).getName());

        //Using Glide to display the image
        //we need to add a link before the image name(that display in a details
        //https://image.tmdb.org/t/p/w500/1E5baAaEse26fej7uHcjOgEE2t2.jpg

        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500"+movieModelClassList.get(position).getImg()).into(holder.imageView_movie_photo);

    }


    @Override
    public int getItemCount() {
        return movieModelClassList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_name;
        TextView txt_id;

         TextView date;
        ImageView imageView_movie_photo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_id = itemView.findViewById(R.id.txt_id);
            txt_name = itemView.findViewById(R.id.txt_name);
            imageView_movie_photo = itemView.findViewById(R.id.imageView_movie_photo);
            date = itemView.findViewById(R.id.date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieOnClickListener.movieOnClicked(getAdapterPosition(),movieModelClassList.get(getAdapterPosition()));
                }
            });


        }
    }


}
