package com.example.zhiyongjin.checkbox;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/*
check box 监听

 */

public class MainActivity extends AppCompatActivity {
    //    三个checkbox的对象
    private CheckBox game, travel, read;
    //    checkbox监听器对象
    private CheckBoxListener checkBoxListener;

    //    button的对象
    private Button all, notAll, getResult;
    private TextView showResult;
    private List<String> list;

    //    button监听器对象
    private ButtonListener buttonListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);

        initView();
        setListener();
        initDate();
    }

    private void initView() {
//        初始化控件:
        game = findViewById(R.id.dj);
        travel = findViewById(R.id.ly);
        read = findViewById(R.id.ds);
        all = findViewById(R.id.all);
        notAll = findViewById(R.id.notAll);
        getResult = findViewById(R.id.getResult);
        showResult = findViewById(R.id.show);

    }


    //    监听器设置
    private void setListener() {
        checkBoxListener = new CheckBoxListener();
        game.setOnCheckedChangeListener(checkBoxListener);
        travel.setOnCheckedChangeListener(checkBoxListener);
        read.setOnCheckedChangeListener(checkBoxListener);

        buttonListener = new ButtonListener();
        all.setOnClickListener(buttonListener);
        notAll.setOnClickListener(buttonListener);
        showResult.setOnClickListener(buttonListener);
    }

    private void initDate() {
//        初始化, 生成集合对象

        list = new ArrayList<String>();

    }


    class CheckBoxListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//            当选中状态发生变化时, 触发
//            CompoundButton -> Checkbox 的父类
            CheckBox checkBox = (CheckBox) buttonView;

            switch (checkBox.getId()) {

                case R.id.dj:
                    if (isChecked) {
                        Toast.makeText(MainActivity.this, "少玩游戏!!",
                                Toast.LENGTH_LONG).show();
                        game.setTextColor(Color.CYAN);
                    } else {
                        game.setTextColor(Color.BLACK);
                    }

                    break;

                case R.id.ly:
                    Toast.makeText(MainActivity.this, "选择了旅游!" + isChecked,
                            Toast.LENGTH_LONG).show();
                    break;
                case R.id.ds:
                    Toast.makeText(MainActivity.this, "选择了读书!" + isChecked
                            , Toast.LENGTH_LONG).show();

                    break;
            }
        }
    }

    class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {

//                全选, 把所有的checkbox设置成已经check了
                case R.id.all:
                    game.setChecked(true);
                    read.setChecked(true);
                    travel.setChecked(true);
                    break;

                case R.id.notAll:
                    game.setChecked(false);
                    read.setChecked(false);
                    travel.setChecked(false);
                    break;

                case R.id.show:

//                    创建集合对象, 盛放用户获取的内容文本
                    if (game.isChecked()) {
                        list.add(game.getText().toString());
                    }
                    if (travel.isChecked()) {
                        list.add(travel.getText().toString());
                    }
                    if (read.isChecked()) {
                        list.add(read.getText().toString());
                    }

                    showResult.setText(list.toString());
                    list.clear();
                    break;
            }
        }
    }
}
