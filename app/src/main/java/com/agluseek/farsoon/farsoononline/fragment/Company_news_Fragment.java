package com.agluseek.farsoon.farsoononline.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.agluseek.farsoon.farsoononline.Interface.RecyclerViewOnItemClickListener;
import com.agluseek.farsoon.farsoononline.R;
import com.agluseek.farsoon.farsoononline.activity.News_detailsActivity;
import com.agluseek.farsoon.farsoononline.model.News;
import com.agluseek.farsoon.farsoononline.model.NewsList;
import com.agluseek.farsoon.farsoononline.utils.Functions;
import com.agluseek.farsoon.farsoononline.utils.GlideApp;
import com.agluseek.farsoon.farsoononline.utils.HttpUtils;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Wu Ang on 2017/4/17.
 */

public class Company_news_Fragment extends Fragment {
    private WebView webView;
    private static final String ARG_NEWS_INFO = "news-info";
    public static final int UPDATE_NEWS = 1;
    private String mNewsInfo;
    private String Receive;
    private String Json_news;
    private static ProgressBar loadingBar;
    private static TextView loadingText;
    private static View rootView;
    private RecyclerView news_recyclerView;
    private static List<News> mNewslist = new ArrayList<>();
    private static String NewsUrl = "http://192.168.4.49/WebLearn/GetNewsList.aspx";
    private SwipeRefreshLayout mSwipeRefresh;

    private static LinearLayout loading;

    public static DevicesItem_Fragment newInstance(String ReceiveInfo) {
        DevicesItem_Fragment fragment = new DevicesItem_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_NEWS_INFO, ReceiveInfo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNewsInfo = getArguments().getString(ARG_NEWS_INFO);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    //company_news_fragment  主页面新闻布局Layout

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            long currentTime = System.currentTimeMillis();
            getNewsJSON();

            rootView = inflater.inflate(R.layout.company_news_fragment, container, false);
            loadingBar = (ProgressBar) rootView.findViewById(R.id.loading_bar);
            loadingText = (TextView) rootView.findViewById(R.id.loading_text);
    //        webView = (WebView) rootView.findViewById(R.id.company_news_webview);
    //        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.metal_detail_toolbar);

            loading = (LinearLayout) rootView.findViewById(R.id.loading);

            news_recyclerView = (RecyclerView) rootView.findViewById(R.id.news_small_recyclerView);
            // 下拉刷新（更新新闻）
            mSwipeRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.swipeRefresh);

            mSwipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
            mSwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    Snackbar.make(getView(), "欢迎来到华曙高科！", Snackbar.LENGTH_LONG).setAction("好的", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
                    //下拉箭头MISS

                    mSwipeRefresh.setRefreshing(false);
                }
            });

            news_recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            news_recyclerView.setItemAnimator(new DefaultItemAnimator());
            news_recyclerView.setHasFixedSize(true);
            //  news_recyclerView.addItemDecoration(new DividerGridItemDecoration(getActivity()));

    //        WebSettings ws = webView.getSettings();
    //
    //        ws.setJavaScriptEnabled(true);
    //        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    //        ws.setUseWideViewPort(true);
    //        ws.setLoadWithOverviewMode(true);
    //
    //        webView.setWebViewClient(new WebViewClient() {
    //            @Override
    //            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
    //                String url = null;
    //                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
    //                    url = request.getUrl().toString();
    //                }
    //                view.loadUrl(url);
    //                return true;
    //            }
    //            @Override
    //            public void onPageStarted(WebView view, String url, Bitmap favicon) {
    //                super.onPageStarted(view, url, favicon);
    //            }
    //            @Override
    //            public void onPageFinished(WebView view, String url) {
    //                super.onPageFinished(view, url);
    ////                showProgress(false);
    //            }
    //        });
    //        webView.loadUrl("http://www.farsoon.net/news/");
            return rootView;
    }

    private void getNewsJSON() {
        String api = "http://192.168.4.49/WebLearn/GetNewsList.aspx";
        HttpUtils.doGet(api, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Message s = Message.obtain();
                s.obj = result;
                s.what = UPDATE_NEWS;
                handler.sendMessage(s);

            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
//        if (mRollPagerView.isPlaying()) {
//            mRollPagerView.pause();
//        }
//        webView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (!mRollPagerView.isPlaying()) {
//            mRollPagerView.resume();
//        }
//        webView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        webView.clearCache(true);
//        webView.clearHistory();
//        webView.destroy();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void showProgress(final boolean show) {
        loading.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        private final List<News> mValues;
        private RecyclerViewOnItemClickListener mRecyclerViewOnItemClickListener;

        public HomeAdapter(List<News> mValues, RecyclerViewOnItemClickListener mRecyclerViewOnItemClickListener) {
            this.mValues = mValues;
            this.mRecyclerViewOnItemClickListener = mRecyclerViewOnItemClickListener;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_fragment_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            final News news_large = mValues.get(position);
            final int id = news_large.getnID();
            holder.news_tv_summary.setText(news_large.getSummary());

            String url = news_large.getImagePath();

            System.out.println("解析之前的地址--------->>" + url);
            String replace = url.replace('\\', '/');
            System.out.println("解析出来后的图片地址------------>>" + replace);
            String[] split = replace.split(":");

            System.out.println(split[0]);
            System.out.println(split[1]);

            String api = split[0] + ":/" + split[1];
            System.out.println("截取出来的API----------->>" + api);
            // https:ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2956560198,255477481&fm=11&gp=0.jpg
            //  https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504157141268&di=d79c6dd99a7cecb880e441cb15438ba6&imgtype=0&src=http%3A%2F%2Fpic68.nipic.com%2Ffile%2F20150603%2F21065026_153314635000_2.jpg
            GlideApp.with(getActivity())
                    .asBitmap()
                    .load(api)
                    .centerCrop()
                    .placeholder(R.mipmap.f2)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.news_item_iv);

            holder.news_tv_title.setText(news_large.getTitle());
            holder.news_tv_date.setText(news_large.getStrDate());

            if (mRecyclerViewOnItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mRecyclerViewOnItemClickListener.setOnItemClick(holder.itemView, pos, id);
                    }
                });

                holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        int pos = holder.getLayoutPosition();
                        mRecyclerViewOnItemClickListener.setOnItemLongClick(holder.itemView, pos);

                        return true;

                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView news_tv_title, news_tv_date, news_tv_summary;
            ImageView news_item_iv;

            public MyViewHolder(View view) {
                super(view);
                news_tv_title = (TextView) view.findViewById(R.id.news_tv_title);
                news_item_iv = (ImageView) view.findViewById(R.id.news_item_iv);
                news_tv_date = (TextView) view.findViewById(R.id.news_tv_date);
                news_tv_summary = (TextView) view.findViewById(R.id.news_tv_summary);
            }
        }

        /**
         * 加载图片
         *
         * @param url
         * @param imageView
         */

        public void loadImage(String url, ImageView imageView) {
            RequestBuilder<Bitmap> bitmapRequestBuilder = GlideApp.with(getActivity())
                    .asBitmap()
                    .load(url)
                    .error(R.drawable.scrubber_control_disabled_holo)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .fallback(R.mipmap.ic_launcher_round);
            bitmapRequestBuilder.into(imageView);

        }
    }

    private static void parseInfoNews(List<Map<String, String>> News) {
        mNewslist = new ArrayList<News>();
        for (int i = 0; i < News.size(); i++) {

            Map<String, String> newsMap = News.get(i);
            if (newsMap == null) {
                break;
            }

            News news_large = new News();
            news_large.setImagePath(newsMap.get("ImagePath"));
            news_large.setnID(Integer.parseInt(newsMap.get("nID")));
            news_large.setStrDate(newsMap.get("strDate"));
            news_large.setSummary(newsMap.get("Summary"));
            news_large.setTitle(newsMap.get("Title"));
            news_large.setUrlPath(newsMap.get("UrlPath"));

            mNewslist.add(news_large);
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == UPDATE_NEWS) {
                Receive = (String) msg.obj;
                NewsList news = JSON.parseObject(Receive, NewsList.class);
                String newsListJSON = news.getNewsList();
                try {
                    parseInfoNews(Functions.readJson(newsListJSON));
                    System.out.println("mNewslist------>>>>>" + mNewslist.size());

                } catch (Exception e) {
                    e.printStackTrace();
                }

                System.out.println("解析后得到的新闻列表JSON--------------->>" + newsListJSON);
                HomeAdapter mAdapter = new HomeAdapter(mNewslist, new RecyclerViewOnItemClickListener() {
                    @Override
                    public void setOnItemClick(View view, int position, int id) {
                        News news1 = mNewslist.get(position);
                        int i = news1.getnID();

                        System.out.println(" news1.getnID()---------->>" + i);
                        if (i == id) {
                            News_detailsActivity.actionStart(getActivity(), news1.getUrlPath());

                        }
                    }

                    @Override
                    public void setOnItemLongClick(View view, int position) {
                        Snackbar.make(view, position + " RecyclerView-DridLayoutManager的长点击事件！", Snackbar.LENGTH_SHORT).show();
                    }

                });
                news_recyclerView.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
            }
        }
    };
}
