package com.fei.damovie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.fei.damovie.R;
import com.fei.damovie.helper.Const;
import com.fei.damovie.model.Movies;
import com.fei.damovie.model.NowPlaying;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.CompanyViewHolder> {

    private Context context;
    private List<Movies.ProductionCompanies> companyList;

    public CompanyAdapter(Context context) {
        this.context = context;
    }

    public List<Movies.ProductionCompanies> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Movies.ProductionCompanies> companyList) {
        this.companyList = companyList;
    }

    @NonNull
    @Override
    public CompanyAdapter.CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_company, parent, false);
        return new CompanyAdapter.CompanyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompanyAdapter.CompanyViewHolder holder, int position) {

        final Movies.ProductionCompanies company = getCompanyList().get(position);

        if(company.getLogo_path()==null){
            Glide.with(context)
                    .load(R.drawable.damovie_icon)
                    .into(holder.company_logo_imageView);
        } else {
            Glide.with(context)
                    .load(Const.IMG_URL+company.getLogo_path())
                    .into(holder.company_logo_imageView);
        }

    }

    @Override
    public int getItemCount() {
        return getCompanyList().size();
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder {

        CardView company_cardView;
        ImageView company_logo_imageView;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            company_logo_imageView = itemView.findViewById(R.id.company_logo_imageView);

        }
    }
}
