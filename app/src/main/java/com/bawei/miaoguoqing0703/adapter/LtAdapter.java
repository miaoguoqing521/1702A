package com.bawei.miaoguoqing0703.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.miaoguoqing0703.Bean;
import com.bawei.miaoguoqing0703.HttpUtils;
import com.bawei.miaoguoqing0703.R;

import java.util.List;

public class LtAdapter extends BaseAdapter {
    private List<Bean> list;
    private Context context;

    public LtAdapter(List<Bean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=View.inflate(context, R.layout.one,null);
            holder=new ViewHolder();
            holder.imm=convertView.findViewById(R.id.imm);
            holder.tv=convertView.findViewById(R.id.tv);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bean bean = list.get(position);
        holder.tv.setText(bean.getTitle());
        final ViewHolder viewHolder=holder;
        HttpUtils.getInstance().getAsyncTaskA(bean.getImageUrl(), new HttpUtils.GetCallBackA() {
            @Override
            public void getData(Bitmap bitmap) {
                viewHolder.imm.setImageBitmap(bitmap);
            }
        });
        return convertView;
    }
    class ViewHolder{
        ImageView imm;
        TextView tv;
    }
}
