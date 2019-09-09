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

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static int TYPE_WHITE = 1;
    private static int TYPE_BLUE = 2;

    private List<Color> data;
    private RecyclerViewOnItemClickListener recyclerViewOnItemClickListener;

    public ListAdapter(@NonNull List<Color> data, @NonNull RecyclerViewOnItemClickListener recyclerViewOnItemClickListener) {
        this.data = data;
        this.recyclerViewOnItemClickListener = recyclerViewOnItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == TYPE_WHITE) {
            View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
            return new PalleteWhiteViewHolder(row);
        } else {
            View row = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_2, viewGroup, false);
            return new PalleteBlueViewHolder(row);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Color color = data.get(position);
        if (getItemViewType(position) == TYPE_WHITE) {
            ((PalleteWhiteViewHolder) viewHolder).getTitleTextView().setText(color.getName());
            ((PalleteWhiteViewHolder) viewHolder).getSubtitleTextView().setText(color.getHex());

            GradientDrawable gradientDrawable = (GradientDrawable) ((PalleteWhiteViewHolder) viewHolder).getCircleView().getBackground();
            int colorId = android.graphics.Color.parseColor(color.getHex());
            gradientDrawable.setColor(colorId);
        } else {
            ((PalleteBlueViewHolder) viewHolder).getTitleTextView().setText(color.getName());
            ((PalleteBlueViewHolder) viewHolder).getSubtitleTextView().setText(color.getHex());

            GradientDrawable gradientDrawable = (GradientDrawable) ((PalleteBlueViewHolder) viewHolder).getCircleView().getBackground();
            int colorId = android.graphics.Color.parseColor(color.getHex());
            gradientDrawable.setColor(colorId);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position% 2 == 0) {
            return TYPE_WHITE;
        } else {
            return TYPE_BLUE;
        }
    }

    /**
     * For White background.
     */
    class PalleteWhiteViewHolder extends RecyclerView.ViewHolder {

        private View circleView;
        private TextView titleTextView;
        private TextView subtitleTextView;

        public PalleteWhiteViewHolder(View itemView) {
            super(itemView);
            circleView = itemView.findViewById(R.id.circleView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            subtitleTextView = (TextView) itemView.findViewById(R.id.subtitleTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });
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

    /**
     * For Blue background.
     */
    class PalleteBlueViewHolder extends RecyclerView.ViewHolder {

        private View circleView;
        private TextView titleTextView;
        private TextView subtitleTextView;

        public PalleteBlueViewHolder(View itemView) {
            super(itemView);
            circleView = itemView.findViewById(R.id.circleView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            subtitleTextView = (TextView) itemView.findViewById(R.id.subtitleTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });
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


    public interface RecyclerViewOnItemClickListener {
        void onItemClick(int position);
    }

}
