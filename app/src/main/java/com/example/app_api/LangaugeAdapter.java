package com.example.app_api;

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

public class LangaugeAdapter extends RecyclerView.Adapter<LangaugeAdapter.LanguageHolder> {

    private Context context;
    private List<ProgramLanguages> programLanguagesList;

public LangaugeAdapter(Context context, List<ProgramLanguages> programLanguages) {
    this.context = context;
    programLanguagesList = programLanguages;
}

    @NonNull
    @Override
    public LanguageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new LanguageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageHolder holder, int position) {

    ProgramLanguages programLanguages = programLanguagesList.get(position);
    holder.title.setText(programLanguages.getTitle());
    holder.desc.setText(programLanguages.getDesc());
    holder.example.setText(programLanguages.getExample());
    holder.url.setText(programLanguages.getUrl());
        Glide.with(context).load(programLanguages.getLogo()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return programLanguagesList.size();
    }

    public class LanguageHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView title, desc, example, url;
        public LanguageHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            example = itemView.findViewById(R.id.example);
            url = itemView.findViewById(R.id.link);
        }
    }
}
