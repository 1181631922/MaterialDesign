package com.fanyafeng.materialdesign.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fanyafeng.materialdesign.R;
import com.fanyafeng.materialdesign.util.ControllerListenerUtil;
import com.fanyafeng.materialdesign.util.MyUtils;

import java.util.List;

/**
 * Created by 365rili on 16/6/14.
 */
public class RVAdapter extends BaseRecyclerAdapter<RVAdapter.ViewHolder> {
    private Context context;
    private List<String> stringList;

    public RVAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position, boolean isItem) {
//        holder.sdvRvItem.setImageURI(Uri.parse(stringList.get(position)));
//        holder.sdvRvItem.set
        ControllerListenerUtil.setControllerListener(holder.sdvRvItem, stringList.get(position), MyUtils.getScreenWidth(context)>>1, context);
    }

    @Override
    public int getAdapterItemCount() {
        return stringList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView sdvRvItem;

        public ViewHolder(View itemView) {
            super(itemView);
            sdvRvItem = (SimpleDraweeView) itemView.findViewById(R.id.sdvRvItem);
        }
    }
}
