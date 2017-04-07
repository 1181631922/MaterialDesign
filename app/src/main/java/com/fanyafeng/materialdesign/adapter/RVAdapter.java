package com.fanyafeng.materialdesign.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
    private final static String TAG = "RVAdapter";
    private Context context;
    private List<String> stringList;

    private RecyclerView recyclerView;

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public RVAdapter(Context context, List<String> stringList) {
        this.context = context;
        this.stringList = stringList;
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(View view, String string, int position);

        void onItemLongClickListener(View view, String string, int position);
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
    public void onBindViewHolder(final ViewHolder holder, final int position, boolean isItem) {
//        holder.sdvRvItem.setImageURI(Uri.parse(stringList.get(position)));
//        holder.sdvRvItem.set
        ControllerListenerUtil.setControllerListener(holder.sdvRvItem, stringList.get(position), MyUtils.getScreenWidth(context) >> 1, context);
        if (onItemClickListener != null) {
            holder.sdvRvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClickListener(v, stringList.get(position), position);
                }
            });
            holder.sdvRvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemClickListener(v, stringList.get(position), position);
                    return false;
                }
            });
        }

        holder.itemView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float currentX = 0;
                float currentY = 0;
                Log.d(TAG, "holder itemView onTouch");
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        currentX = event.getX();
                        currentY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (Math.abs(event.getY() - currentY) < Math.abs(event.getX() - currentX)) {

//                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            return true;
                        } else {

                            return false;
                        }
                    case MotionEvent.ACTION_UP:
                        if (Math.abs(event.getY() - currentY) < Math.abs(event.getX() - currentX)) {

//                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(true);
                            return true;
                        } else {
//                            holder.itemView.getParent().requestDisallowInterceptTouchEvent(false);
                            return false;
                        }
                }
//                recyclerView.requestDisallowInterceptTouchEvent(false);
//                holder.sdvRvItem.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


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
