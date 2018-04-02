package com.taindb.twitsplit.ui.message;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taindb.twitsplit.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2018, Taindb.
 *
 * @author taindb
 * @since 4/2/18
 */

public class MessageAdapter extends RecyclerView.Adapter {

    private final List<String> mItems = new ArrayList<>();

    private final Object mLock = new Object();

    public MessageAdapter() {
    }

    public void setItems(List<String> items) {
        synchronized (mLock) {
            mItems.clear();
            if (items != null && !items.isEmpty()) {
                mItems.addAll(items);
            }

            notifyDataSetChanged();
        }
    }

    public void appendItems(List<String> items) {
        synchronized (mLock) {
            if (items != null && !items.isEmpty()) {
                mItems.addAll(items);
                notifyItemRangeChanged(getItemCount(), getItemCount() + items.size() - 1);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageItemHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_sent, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        String message = mItems.get(position);
        ((MessageItemHolder) holder).setData(message);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    private static final class MessageItemHolder extends RecyclerView.ViewHolder {

        private TextView mMessageTv;

        private TextView mTimeTv;

        public MessageItemHolder(View itemView) {
            super(itemView);

            mMessageTv = itemView.findViewById(R.id.message_body_tv);
            mTimeTv = itemView.findViewById(R.id.message_time_tv);
        }

        public void setData(String message) {
            mMessageTv.setText(message);
         }
    }
}
