package com.mansourappdevelopment.androidapp.kidsafe;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.AppAdapterViewHolder> {
    private Context mContext;
    private ArrayList<App> mApps;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

    public AppAdapter(Context mContext, ArrayList<App> mApps) {
        this.mContext = mContext;
        this.mApps = mApps;
    }

    @NonNull
    @Override
    public AppAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.app_card, viewGroup, false);
        return new AppAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AppAdapterViewHolder appAdapterViewHolder, int i) {
        App app = mApps.get(i);
        appAdapterViewHolder.mTextViewAppName.setText(app.getmAppName());
        try {
            appAdapterViewHolder.mImageViewAppIcon.setImageDrawable(app.getmAppIcon());

        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mApps.size();
    }

    //to disable the recycling behavior(when enabling a button at the 1st position it automatically enables the one on the 10th position)
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class AppAdapterViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageViewAppIcon;
        private TextView mTextViewAppName;
        private Switch mSwitchAppState;

        public AppAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewAppName = (TextView) itemView.findViewById(R.id.textViewAppName);
            mImageViewAppIcon = (ImageView) itemView.findViewById(R.id.imageViewAppIcon);
            mSwitchAppState = (Switch) itemView.findViewById(R.id.switchAppState);

            mSwitchAppState.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mOnItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
