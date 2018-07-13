package com.test.mi.testproject.defineview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.mi.testproject.R;
import com.test.mi.testproject.recycleView.model.Performer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcl on 18.3.29
 * desc:
 */

public class DefineViewRecycleAdapter extends RecyclerView.Adapter {

    public List<DefineViewModel> listData = new ArrayList<>();
    Context context;

    private OnItemClickListener listener;

    public DefineViewRecycleAdapter(List<DefineViewModel> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == DefineViewModel.VIEW_TYPE_TWO) {
            View view = LayoutInflater.from(context).inflate(R.layout.recycle_item_test, parent, false);
            MyRecyclerViewHolder viewHolder = new MyRecyclerViewHolder(view);
            return viewHolder;
        }
        return new PerformTypeViewHolder(LayoutInflater.from(context).inflate(R.layout.recycle_item_perform_type, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final DefineViewModel model = listData.get(position);

        if (holder instanceof MyRecyclerViewHolder) {
            MyRecyclerViewHolder viewHolder = (MyRecyclerViewHolder) holder;
            viewHolder.textView.setText(model.getTitle());
            //设置点击事件
            if (listener != null) {
                viewHolder.textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(position, model, listData.size());
                    }
                });
            }
        } else if (holder instanceof PerformTypeViewHolder) {
            ((PerformTypeViewHolder) holder).bindData(model);
        }


    }

    @Override
    public int getItemViewType(int position) {
        return listData.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }


    class MyRecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyRecyclerViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvTitle);
            itemView.setTag(false);
        }
    }

    class PerformTypeViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public PerformTypeViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tvContent);
            itemView.setTag(true);
        }

        public void bindData(DefineViewModel performer) {
            textView.setText(performer.getTitle());
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position, DefineViewModel model, int size);
    }
}
