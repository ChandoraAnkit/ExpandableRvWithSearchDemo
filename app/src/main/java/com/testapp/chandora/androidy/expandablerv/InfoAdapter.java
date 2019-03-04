package com.testapp.chandora.androidy.expandablerv;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by chandora on 04-Mar-2019
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder> implements Filterable {

    private ArrayList<Info> infoList;
    private ArrayList<Info> filteredInfoList;

    public InfoAdapter() {
        infoList = new ArrayList<>();
        filteredInfoList =  new ArrayList<>();
    }


    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, null, false);

        InfoViewHolder infoViewHolder = new InfoViewHolder(view);

        return infoViewHolder;
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {

        holder.bind(filteredInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredInfoList.size();
    }



    public void setItems(ArrayList<Info> list) {

        if (infoList != null) {
            infoList.clear();
            infoList.addAll(list);

            filteredInfoList.addAll(list);

            notifyDataSetChanged();
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String query = charSequence.toString();

                if (query.isEmpty()){
                    filteredInfoList = infoList;
                }else {

                    ArrayList<Info> filteredList = new ArrayList<>();

                    for (Info info : infoList){

                        if (info.getTitle().toLowerCase().contains(query.toLowerCase())){
                            filteredList.add(info);
                        }
                    }

                    filteredInfoList = filteredList;
                }

                FilterResults  filterResults = new FilterResults();
                filterResults.values = filteredInfoList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                filteredInfoList = (ArrayList<Info>) filterResults.values;

                notifyDataSetChanged();
            }
        };
    }

    static class InfoViewHolder extends RecyclerView.ViewHolder {

        private TextView title, caption, time;
        private ImageView imageView;

        public InfoViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.titleTxt);
            caption = itemView.findViewById(R.id.captionTxt);
            time = itemView.findViewById(R.id.timeTxt);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void bind(Info info) {

            title.setText(info.getTitle());
            caption.setText(info.getCaption());
            time.setText(info.getTime());

            Glide.with(imageView.getContext()).load(info.getImageUrl()).into(imageView);
        }
    }
}
