package com.nicolasfanin.IUASampleApp.utils;

import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.nicolasfanin.IUASampleApp.R;
import com.nicolasfanin.IUASampleApp.data.Color;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.PalleteViewHolder> {

    private List<Color> data;

    public ListAdapter(@NonNull List<Color> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public PalleteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        return new PalleteViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull PalleteViewHolder palleteViewHolder, int i) {
        Color color = data.get(i);
        palleteViewHolder.getTitleTextView().setText(color.getName());
        palleteViewHolder.getSubtitleTextView().setText(color.getHex());

        GradientDrawable gradientDrawable = (GradientDrawable) palleteViewHolder.getCircleView().getBackground();
        int colorId = android.graphics.Color.parseColor(color.getHex());
        gradientDrawable.setColor(colorId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class PalleteViewHolder extends RecyclerView.ViewHolder {

        private View circleView;
        private TextView titleTextView;
        private TextView subtitleTextView;

        public PalleteViewHolder(View itemView) {
            super(itemView);
            circleView = itemView.findViewById(R.id.circleView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            subtitleTextView = (TextView) itemView.findViewById(R.id.subtitleTextView);
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getSubtitleTextView() {
            return subtitleTextView;
        }

        public View getCircleView() {
            return circleView;
        }
    }
}
