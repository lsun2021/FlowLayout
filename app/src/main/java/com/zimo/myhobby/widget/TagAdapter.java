package com.zimo.myhobby.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.zimo.myhobby.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HanHailong on 15/10/19.
 */
public class TagAdapter<T> extends BaseAdapter implements OnInitSelectedPosition {

    private final Context mContext;
    private  List<String> dataArray;

    private List<HobbyTagBean> selectList;

    public TagAdapter(Context context) {
        this.mContext = context;
       dataArray=new ArrayList<>();
        selectList=new ArrayList<>();
    }

    @Override
    public int getCount() {
        return dataArray.size();
    }

    @Override
    public Object getItem(int position) {
        return dataArray.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_tag, null);
        TextView textView = (TextView) view.findViewById(R.id.tv_tag);
           String t = dataArray.get(position);
        HobbyTagBean bean=new HobbyTagBean();
        bean.setTag(t);
         int index=selectList.indexOf(bean);
        if (index>=0){
            textView.setSelected(true);
        }else {
            textView.setSelected(false);
        }
        textView.setText( t);
        return view;
    }

    public void putSelectItems(List<HobbyTagBean> selectList){
        this.selectList=selectList;
        notifyDataSetChanged();
    }
//
//    public   void  AddEnd(List<T> datas,String  mDatas){
//        mDataList.add(datas.size(), (T) datas);
//        notifyDataSetChanged();
//    }

    public void onlyAddAll(List<String> datas) {
        this.dataArray.addAll(datas);
        notifyDataSetChanged();
    }

//    public void clearAndAddAll(List<T> datas) {
//        mDataList.clear();
//        onlyAddAll(datas);
//    }
//
//    public   void   clearData(List<T> datas){
//         if(datas.size()>0){
//             mDataList.remove(datas.size()-1);
//             notifyDataSetChanged();
//         }
//    }

    public int position;
    public void setSelected(int p) {
        position=p;
    }

    @Override
    public boolean isSelectedPosition(int poi) {
        if (poi == position) {
            return true;
        }
        return false;
    }


}
