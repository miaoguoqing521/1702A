package com.bawei.miaoguoqing0703.tablayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.bawei.miaoguoqing0703.Bean;
import com.bawei.miaoguoqing0703.HttpUtils;
import com.bawei.miaoguoqing0703.R;
import com.bawei.miaoguoqing0703.adapter.LtAdapter;
import com.bawei.miaoguoqing0703.adapter.Xlist;
import com.bumptech.glide.Glide;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Tablayoutone extends Fragment {
    private String str="http://blog.zhaoliang5156.cn/zixunnew/fengjing?page=?";
    private int page=1;
    private PullToRefreshScrollView pull;
    private Banner ban;
    private Xlist lv;
    private HttpUtils instance;
    private List<String> list=new ArrayList<>();
    private List<Bean> list1=new ArrayList<>();
    private LtAdapter ltAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.tablayoutone, container, false);
        pull = inflate.findViewById(R.id.pull);
        ban = inflate.findViewById(R.id.ban);
        lv = inflate.findViewById(R.id.lv);
        pull.setMode(PullToRefreshBase.Mode.BOTH);
        ltAdapter = new LtAdapter(list1,getActivity());
        lv.setAdapter(ltAdapter);
        getData();
        //上拉下拉刷新加载
        pull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                page=1;
                getData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> pullToRefreshBase) {
                page++;
                getData();
            }
        });
        //Banner轮播图
        HttpUtils.getInstance().getAsyncTask(str+page, new HttpUtils.GetCallBack() {
            @Override
            public void getData(String s) {
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray news = object.getJSONObject("data").getJSONArray("news");
                    JSONArray topnews = object.getJSONObject("data").getJSONArray("topnews");
                    for (int i = 0; i < news.length(); i++) {
                        JSONObject obj = (JSONObject) news.get(i);
                        String imageUrl = obj.getString("imageUrl");
                        list.add("http://blog.zhaoliang5156.cn/zixunnew/"+imageUrl);
                    }
                    for (int i = 0; i < topnews.length(); i++) {
                        JSONObject obj1 = (JSONObject) topnews.get(i);
                        String imageUrl1 = obj1.getString("imageUrl");
                        list.add("http://blog.zhaoliang5156.cn/zixunnew/"+imageUrl1);
                    }
                    ban.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(path).into(imageView);
                        }
                    });
                    ban.setImages(list);
                    ban.isAutoPlay(true);
                    ban.setDelayTime(2000);
                    ban.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return inflate;
    }

    private void getData() {
        //lisiview解析
        HttpUtils.getInstance().getAsyncTask(str + page, new HttpUtils.GetCallBack() {
            @Override
            public void getData(String s) {
                if (page==1){
                    list1.clear();
                }
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray news = object.getJSONObject("data").getJSONArray("news");
                    JSONArray topnews = object.getJSONObject("data").getJSONArray("topnews");
                    for (int i = 0; i < news.length(); i++) {
                        JSONObject obj = (JSONObject) news.get(i);
                        String imageUrl = obj.getString("imageUrl");
                        String title = obj.getString("title");
                        list1.add(new Bean("http://blog.zhaoliang5156.cn/zixunnew/"+imageUrl,title));
                    }
                    for (int i = 0; i < topnews.length(); i++) {
                        JSONObject obj1 = (JSONObject) topnews.get(i);
                        String imageUrl1 = obj1.getString("imageUrl");
                        String title = obj1.getString("title");
                        list1.add(new Bean("http://blog.zhaoliang5156.cn/zixunnew/"+imageUrl1,title));
                    }
                    ltAdapter.notifyDataSetChanged();
                    pull.onRefreshComplete();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
