package com.heyi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.heyi.R;
import com.heyi.bean.Mei_T;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公司：HeYi
 */
public class MeiTAdapter extends RecyclerView.Adapter<MeiTAdapter.MeiTViewHolder> {

    private List<Mei_T> mLists;
    private LayoutInflater mInflater;
    private onItemClickListener onItemClickListener;

    public MeiTAdapter(List<Mei_T> mLists, Context mContext) {
        this.mLists = mLists;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MeiTViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.fragment_meit_item, parent, false);
        return new MeiTViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MeiTViewHolder holder, int position) {
        holder.mItemImg.setImageResource(mLists.get(position).getImgId());
        holder.mItemText.setText(mLists.get(position).getTitle());
        if (onItemClickListener != null) {
            holder.mParentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(holder.mParentLayout, holder.getLayoutPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mLists.size();
    }

    public void setOnItemClickListener(MeiTAdapter.onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    class MeiTViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.mei_t_item_img)
        ImageView mItemImg;
        @BindView(R.id.mei_t_item_text)
        TextView mItemText;
        @BindView(R.id.mei_t_item_parent)
        LinearLayout mParentLayout;

        public MeiTViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * item点击事件接口
     */
    public interface onItemClickListener {
        void onClick(View view, int position);
    }
}
