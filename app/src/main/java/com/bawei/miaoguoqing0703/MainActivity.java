package com.bawei.miaoguoqing0703;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.bawei.miaoguoqing0703.adapter.TabAdapter;
import com.bawei.miaoguoqing0703.tablayout.Tablayoutfive;
import com.bawei.miaoguoqing0703.tablayout.Tablayoutfour;
import com.bawei.miaoguoqing0703.tablayout.Tablayoutone;
import com.bawei.miaoguoqing0703.tablayout.Tablayoutseven;
import com.bawei.miaoguoqing0703.tablayout.Tablayoutsex;
import com.bawei.miaoguoqing0703.tablayout.Tablayoutthree;
import com.bawei.miaoguoqing0703.tablayout.Tablayouttwo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout draw;
    private TabLayout tab;
    private ViewPager view;
    private List<String> list;
    private List<Fragment> list1;
    private ImageView ima;
    private ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        draw = findViewById(R.id.draw);
        tab = findViewById(R.id.tab);
        view = findViewById(R.id.view);
        ima = findViewById(R.id.ima);
        imv = findViewById(R.id.imv);
        list = new ArrayList<>();
        list.add("风景");
        list.add("美女");
        list.add("卡通人物");
        list.add("娱乐明星");
        list.add("萌宠");
        list.add("萌宠");
        list.add("娱乐明星");
        list1 = new ArrayList<>();
        list1.add(new Tablayoutone());
        list1.add(new Tablayouttwo());
        list1.add(new Tablayoutthree());
        list1.add(new Tablayoutfour());
        list1.add(new Tablayoutfive());
        list1.add(new Tablayoutsex());
        list1.add(new Tablayoutseven());
        TabAdapter tabAdapter=new TabAdapter(getSupportFragmentManager(),list,list1);
        view.setAdapter(tabAdapter);
        tab.setupWithViewPager(view);
        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                draw.openDrawer(Gravity.LEFT);
            }
        });//点击头像侧滑
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);//点击图片打开相册
                intent.setType("image/*");
                startActivityForResult(intent,10);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==10&&resultCode==RESULT_OK){
            Uri data1 = data.getData();
            Glide.with(MainActivity.this).load(data1).apply(RequestOptions.bitmapTransform(new CircleCrop())).into(imv);
        }
    }
}
