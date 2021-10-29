package com.fei.damovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fei.damovie.R;
import com.fei.damovie.helper.Const;
import com.fei.damovie.model.Credits;
import com.fei.damovie.model.Person;

import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {

    private Context context;
    private List<Person> personList;

    public PersonAdapter(Context context) {
        this.context = context;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_person, parent, false);
        return new PersonAdapter.PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        final Person results = getPersonList().get(position);

        if(results.getProfile_path()==null){
            Glide.with(context)
                    .load(R.drawable.damovie_icon)
                    .into(holder.personCard_imageView);
        } else {
            Glide.with(context)
                    .load(Const.IMG_URL+results.getProfile_path())
                    .into(holder.personCard_imageView);
        }

        holder.personCard_textView.setText(results.getName());

    }

    @Override
    public int getItemCount() {
        return getPersonList().size();
    }

    public class PersonViewHolder extends RecyclerView.ViewHolder {

        CardView personCard_cardView;
        ImageView personCard_imageView;
        TextView personCard_textView;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);

            personCard_cardView = itemView.findViewById(R.id.personCard_cardView);
            personCard_imageView = itemView.findViewById(R.id.personCard_imageView);
            personCard_textView = itemView.findViewById(R.id.personCard_textView);
        }
    }
}
