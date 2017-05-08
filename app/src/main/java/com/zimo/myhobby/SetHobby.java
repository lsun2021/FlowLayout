package com.zimo.myhobby;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zimo.myhobby.utils.AppUtils;
import com.zimo.myhobby.widget.FlowTagLayout;
import com.zimo.myhobby.widget.HobbyTagBean;
import com.zimo.myhobby.widget.OnTagSelectListener;
import com.zimo.myhobby.widget.TagAdapter;
import com.zimo.myhobby.widget.TagBaseAdapter;
import com.zimo.myhobby.widget.TagCloudLayout;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ${Zimo} on 2017/5/8.
 * title:
 */

public class SetHobby  extends AppCompatActivity   implements View.OnClickListener{

    private RelativeLayout linHeadLeft;
    private ImageView ivHeadLeft;
    private TextView tvHeadMid;
    private TextView tvHeadRight;

    private FlowTagLayout multiSelectFlowLayout;
    private TagCloudLayout container;

    private LinkedList<HobbyTagBean> tagList;
    private Context mContext;
    private String hobby;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sethobby);
        tagList=new LinkedList<>();
        mContext = this;
        hobby = getIntent().getStringExtra("hashobby");
        findViews();
    }

    private void findViews() {
        linHeadLeft = (RelativeLayout)findViewById( R.id.lin_head_left );
        ivHeadLeft = (ImageView)findViewById( R.id.iv_head_left );
        tvHeadMid = (TextView)findViewById( R.id.tv_head_mid );
        tvHeadRight = (TextView)findViewById( R.id.tv_head_right );
        multiSelectFlowLayout = (FlowTagLayout)findViewById( R.id.multi_select_flow_layout );
        container = (TagCloudLayout)findViewById( R.id.container );
        linHeadLeft.setOnClickListener(this);
        tvHeadRight.setOnClickListener(this);
        initData();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lin_head_left:
                finish();
                break;
            case R.id.tv_head_right:
                StringBuilder builder=new StringBuilder();
                for (HobbyTagBean bean:tagList){
                    builder.append(bean.getTag()).append(",");
                }
                for (HobbyTagBean bean:secList){
                    String tag=bean.getTag();
                    if ("自定义".equals(tag))
                        continue;
                    builder.append(bean.getTag()).append(",");
                }
                Intent intent = new Intent();
                if (builder.length()>0){
                    String newInterest=builder.substring(0,builder.length()-1);
                    intent.putExtra("sethobby",newInterest);
                }
                SetHobby.this.setResult(3, intent);
                SetHobby.this.finish();
                break;
        }

    }

    private void initData() {
        initMobileData();
        initAddData();
        if (!TextUtils.isEmpty(hobby)){
            String newInterest[] = hobby.split(",");
            for (int i=0;i<newInterest.length;i++){
                if (TextUtils.isEmpty(newInterest[0]))
                    continue;
                HobbyTagBean bean=new HobbyTagBean();
                bean.setTag(newInterest[i]);
                int index=dataArray.indexOf(newInterest[i]);
                if (index>=0){
                    bean.setOnFirst(true);
                    tagList.add(bean);
                }else {
                    bean.setOnFirst(false);
                    secList.addFirst(bean);
                }
            }
        }

        mAdapter.notifyDataSetChanged();
        adapter.putSelectItems(tagList);
        adapter.notifyDataSetChanged();
    }
    private List<String> dataArray;
    private TagAdapter<String> adapter;
    private LinkedList<HobbyTagBean> secList=new LinkedList<>();
    private void initMobileData() {
        dataArray = new ArrayList<>();
        dataArray.add("绘画");
        dataArray.add("桌球");
        dataArray.add("美食");
        dataArray.add("电影");
        dataArray.add("音乐");
        dataArray.add("购物");
        dataArray.add("淘宝");
        dataArray.add("天猫");
        dataArray.add("哈哈哈");
        dataArray.add("啦啦啦");

        adapter = new TagAdapter<>(this);
        multiSelectFlowLayout.setAdapter(adapter);
        adapter.onlyAddAll(dataArray);
        multiSelectFlowLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        multiSelectFlowLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int positoin, List<Integer> selectedList) {
                String tag= (String) adapter.getItem(positoin);
                if(!TextUtils.isEmpty(tag)) {
                    HobbyTagBean bean = new HobbyTagBean();
                    bean.setTag(tag);
                    int index = tagList.indexOf(bean);
                    if (index >= 0) {
                        tagList.remove(index);
                    } else {
                        if ((secList.size() + tagList.size()) > 5) {
                            Toast.makeText(mContext, "最多5个标签", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        bean.setOnFirst(true);
                        tagList.add(bean);
                    }
                    adapter.putSelectItems(tagList);

                }
            }
        });

    }

    private void initAddData() {
        HobbyTagBean bean=new HobbyTagBean();
        bean.setTag("自定义");
        secList.add(bean);
        AddData(secList);
    }
    private TagBaseAdapter mAdapter;

    private void AddData(LinkedList<HobbyTagBean> mAddList) {
        mAdapter = new TagBaseAdapter(this, mAddList);
        container.setAdapter(mAdapter);
        container.setItemClickListener(new TagCloudLayout.TagItemClickListener() {
            @Override
            public void itemClick(final int position) {
                if (position==(secList.size()-1)){
                    if (secList.size()+tagList.size()-1>4){
                        Toast.makeText(mContext, "最多5个标签", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    AddListData(position+1);
                }else {
                    secList.remove(position);
                    mAdapter.notifyDataSetChanged();
                }

            }
        });
    }

    private void AddListData(final int position) {
        final EditText editText = new EditText(mContext);
        editText.setHint("最多输入6个字");
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("请输入");    //设置对话框标题
        builder.setView(editText);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editTextValue = editText.getText().toString().trim();
                if(!AppUtils.compileExChar(editTextValue)){
                    Toast.makeText(mContext, "不允许输入特殊符号！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!AppUtils.stringFilter(editTextValue)) {
                    Toast.makeText(mContext, "标签仅可为汉字、字母、数字", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!editTextValue.isEmpty()) {
                    if (editTextValue.length() < 6 || editTextValue.length() == 6) {
                        if (position == 0) {
                            compareDataList(position, editTextValue);
                        } else {
                            compareDataList(position, editTextValue);
                        }
                    } else {
                        Toast.makeText(mContext, "超出最大输入字数", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "标签不允许输入空值", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setCancelable(true);    //设置按钮是否可以按返回键取消,false则不可以取消
        AlertDialog dialog = builder.create();  //创建对话框
        dialog.setCanceledOnTouchOutside(true); //设置弹出框失去焦点是否隐藏,即点击屏蔽其它地方是否隐藏
        dialog.show();
    }


    /**
     * 新添加的标题和上面固定的标签进行比较
     *
     * @param str      添加的值
     * @param position 位置
     */
    public void compareDataList(int position, String str) {
        if (str.isEmpty()) {
            return;
        }
        HobbyTagBean bean=new HobbyTagBean();
        bean.setTag(str);
        int index=dataArray.indexOf(str);
        int secIndex=secList.indexOf(bean);
        if (index>=0||secIndex>=0){
            Toast.makeText(mContext, "该兴趣已添加", Toast.LENGTH_SHORT).show();
        }else {
            bean.setOnFirst(false);
            secList.addFirst(bean);
            mAdapter.notifyDataSetChanged();
        }
    }



}
