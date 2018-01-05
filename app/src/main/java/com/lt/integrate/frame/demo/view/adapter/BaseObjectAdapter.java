package com.lt.integrate.frame.demo.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lt.integrate.frame.demo.R;
import com.lt.integrate.frame.demo.listener.OnItemClickListener;
import com.lt.integrate.frame.demo.model.ItemObject;
import com.lt.integrate.frame.img.ImageLoader;

import java.util.List;

/**
 * Created by iclick on 2017/9/27.
 */

public class BaseObjectAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Context mContext;
    private OnItemClickListener clickListener;
    private List<Object> objectList;
    private LayoutInflater inflater;
    public BaseObjectAdapter(Context context){
        this.mContext =context;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setObjectData(List<Object> object){
        if(this.objectList == null)
            objectList = object;
    }

    public void setOnItemClickListener( OnItemClickListener listener){
        if(clickListener == null)
            clickListener = listener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ObjectViewHolder(inflater.inflate(R.layout.list_item_news,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ObjectViewHolder itemHolder = (ObjectViewHolder) holder;
        ItemObject objectItem = (ItemObject) objectList.get(position);
        if(objectItem !=null){
            itemHolder.textView.setText(objectItem.getTitle());
            ImageLoader.display(mContext,itemHolder.imageView,objectItem.getImgUrl());
        }
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    class ObjectViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;

        public ObjectViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickListener != null)
                        clickListener.onItemClickListener(itemView, getPosition());
                }
            });
        }
    }

}
