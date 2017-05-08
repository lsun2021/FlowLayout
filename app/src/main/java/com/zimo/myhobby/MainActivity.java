package com.zimo.myhobby;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout relHobby;
    private TextView tvHobby;
    private TextView tvSetHobby;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

    }



    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-05-08 13:45:12 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        relHobby = (RelativeLayout)findViewById( R.id.rel_hobby );
        tvHobby = (TextView)findViewById( R.id.tv_hobby );
        tvSetHobby = (TextView)findViewById( R.id.tv_setHobby );

        relHobby.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rel_hobby:
                if (TextUtils.isEmpty(tvSetHobby.getText().toString())) {
                    Intent sethobbyIntent = new Intent(this, SetHobby.class);
                    startActivityForResult(sethobbyIntent, 3);
                } else {
                    Intent sethobbyIntent = new Intent(this, SetHobby.class);
                    sethobbyIntent.putExtra("hashobby", tvSetHobby.getText().toString());
                    startActivityForResult(sethobbyIntent, 3);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        } else {
            tvSetHobby.setText(data.getStringExtra("sethobby"));
        }
    }


}
