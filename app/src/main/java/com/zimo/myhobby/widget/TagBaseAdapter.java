package com.zimo.myhobby.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zimo.myhobby.R;

import java.util.List;

/**
 * @author fyales
 * @since 8/26/15.
 */
public class TagBaseAdapter extends BaseAdapter {

    private Context mContext;
    private List<HobbyTagBean> mList;
   // private int selectedPosition = -1;// 选中的位置

    public TagBaseAdapter(Context context, List<HobbyTagBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        int size=0;
        for (HobbyTagBean bean:mList){
            if (!bean.isOnFirst()){
                size++;
            }
        }
        return size;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    public void addAllItems(HobbyTagBean bean){
        mList.add(bean);
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }






    public void deletePosition(int position) {
        mList.remove(position);
        notifyDataSetInvalidated();

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tag_add, null);
            holder = new ViewHolder();
            holder.tagBtn = (TextView) convertView.findViewById(R.id.tv_tag);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final HobbyTagBean bean = mList.get(position);
        holder.tagBtn.setText(bean.getTag());
        if (position == mList.size() - 1) {
           // holder.tagBtn.setBackgroundResource(R.drawable.shape_rect_round);
            holder.tagBtn.setBackgroundResource(R.drawable.shape_rect_round);
            holder.tagBtn.setSelected(true);
            holder.tagBtn.setPressed(true);
//            if (0 == position) {
//                holder.tagBtn.setBackgroundResource(R.drawable.shape_rect_round);
//                holder.tagBtn.setSelected(true);
//                holder.tagBtn.setPressed(true);
//            } else {
//                holder.tagBtn.setSelected(false);
//                holder.tagBtn.setPressed(false);
//                holder.tagBtn.setBackgroundColor(Color.TRANSPARENT);
//                holder.tagBtn.setTextColor(convertView.getResources().getColor(R.color.base_main_color));
//            }
        } else {
            holder.tagBtn.setBackgroundResource(R.drawable.blue_rect_round_bg);
            holder.tagBtn.setSelected(true);
            holder.tagBtn.setTextColor(convertView.getResources().getColor(R.color.bg_writer));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tagBtn;
    }
}
