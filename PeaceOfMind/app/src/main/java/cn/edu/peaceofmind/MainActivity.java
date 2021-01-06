package cn.edu.peaceofmind;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import cn.edu.peaceofmind.Adapter.FragmentAdapter;
import cn.edu.peaceofmind.Fragment.LaughFragment;
import cn.edu.peaceofmind.Fragment.NewFragment;
import cn.edu.peaceofmind.Fragment.ReadFragment;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wavewave.mylibrary.BottomOutNavigation;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ImageView ivRead;
    private LinearLayout rlRead;
    private ImageView ivLaugh;
    private LinearLayout rlLaugh;
    private ImageView ivNews;
    private LinearLayout rlNews;
    private LinearLayout llBottom;
    private TextView tvRead;
    private TextView tvLaugh;
    private TextView tvNews;
    //标签页
    private FragmentManager fragmentManager;
    private ArrayList<Fragment> fragmentList;
    private ReadFragment readFragment;
    private LaughFragment laughFragment;
    private NewFragment newFragment;
    //实现标签栏凸起
    private BottomOutNavigation bottomOutNavigation;
    private ImageView tempImageView;
    //默认 图片 负距离
    private int marginTop = -40;
    // 默认 图片 最小负距离
    private int marginTopMin = 0;
    //默认 选中图片 大小
    private int maxIV = 40;
    // 未选中图片大小
    private int minIV = (int) (maxIV / 1.8);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setStatusBar();
        initView();
        setListeners();
        fragmentManager = getSupportFragmentManager();
        //初始化内容页对应的fragment
        readFragment = new ReadFragment();
        laughFragment = new LaughFragment();
        newFragment = new NewFragment();
        initViewPager();
    }
    private void initViewPager() {
        fragmentList = new ArrayList<>();
        fragmentList.add( 0, readFragment );
        fragmentList.add( 1, laughFragment );
        fragmentList.add( 2, newFragment );

        FragmentAdapter fragmentAdapter = new FragmentAdapter(fragmentManager, fragmentList);
        //ViewPager设置适配器
        viewPager.setAdapter(fragmentAdapter);
        //ViewPager显示第一个Fragment
        viewPager.setCurrentItem(1);
        //ViewPager页面切换监听
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());
    }
    //获取所有视图控件的引用
    private void  initView(){
        viewPager = findViewById( R.id.main_content );
        llBottom = findViewById(R.id.ll_bottom);
        rlRead = findViewById(R.id.rlRead);
        ivRead = findViewById(R.id.ivRead);
        tvRead = findViewById(R.id.tvRead);

        rlLaugh = findViewById(R.id.rlLaugh);
        ivLaugh = findViewById(R.id.ivLaugh);
        tvLaugh = findViewById(R.id.tvLaugh);

        rlNews = findViewById(R.id.rlNews);
        ivNews = findViewById(R.id.ivNews);
        tvNews = findViewById(R.id.tvNews);
        //设置初始凸起样式
        bottomOutNavigation = findViewById(R.id.myLineView);
        bottomOutNavigation.setCount(2);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ivLaugh.getLayoutParams();
        layoutParams.width = dip2px(maxIV);
        layoutParams.height = dip2px(maxIV);
        layoutParams.setMargins(0, dip2px(marginTop), 0, 0);
        ivLaugh.setImageResource(R.drawable.laugh2);
        ivLaugh.setLayoutParams(layoutParams);
        tvLaugh.setTextColor(this.getResources().getColor(R.color.selectColor));
        tempImageView = ivLaugh;


        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) ivRead.getLayoutParams();
        layoutParams1.width = dip2px(minIV);
        layoutParams1.height = dip2px(minIV);
        layoutParams1.setMargins(0, dip2px(marginTopMin), 0, 0);
        ivRead.setLayoutParams(layoutParams1);

        LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) ivNews.getLayoutParams();
        layoutParams3.width = dip2px(minIV);
        layoutParams3.height = dip2px(minIV);
        layoutParams3.setMargins(0, dip2px(marginTopMin), 0, 0);
        ivNews.setLayoutParams(layoutParams3);
    }

    public void setListeners(){
        MyClickListener myClickListener = new MyClickListener();
        rlRead.setOnClickListener(myClickListener);
        rlLaugh.setOnClickListener(myClickListener);
        rlNews.setOnClickListener(myClickListener);
    }
    //隐藏状态栏
    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.black));
        }
    }
    private class MyClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rlRead:
                    viewPager.setCurrentItem(0);
                    if (tempImageView != ivRead) {
                        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) tempImageView.getLayoutParams();
                        layoutParams1.width = dip2px(minIV);
                        layoutParams1.height = dip2px(minIV);
                        layoutParams1.setMargins(0, dip2px(marginTopMin), 0, 0);
                        tempImageView.setLayoutParams(layoutParams1);
                        //设置当前凸起页
                        bottomOutNavigation.setCount(1);
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ivRead.getLayoutParams();
                        layoutParams.width = dip2px(maxIV);
                        layoutParams.height = dip2px(maxIV);
                        layoutParams.setMargins(0, dip2px(marginTop), 0, 0);
                        ivRead.setLayoutParams(layoutParams);
                        tempImageView = ivRead;
                        //设置当前标签栏样式
                        ivRead.setImageResource(R.drawable.read2);
                        ivLaugh.setImageResource(R.drawable.laugh);
                        ivNews.setImageResource(R.drawable.new1);

                        tvRead.setTextColor(v.getContext().getResources().getColor(R.color.selectColor));
                        tvLaugh.setTextColor(v.getContext().getResources().getColor(R.color.initColor));
                        tvNews.setTextColor(v.getContext().getResources().getColor(R.color.initColor));
                    }
                    break;
                case R.id.rlLaugh:
                    viewPager.setCurrentItem(1);
                    if (tempImageView != ivLaugh) {
                        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) tempImageView.getLayoutParams();
                        layoutParams1.width = dip2px(minIV);
                        layoutParams1.height = dip2px(minIV);
                        layoutParams1.setMargins(0, dip2px(marginTopMin), 0, 0);
                        tempImageView.setLayoutParams(layoutParams1);
                        //设置当前凸起页
                        bottomOutNavigation.setCount(2);
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ivLaugh.getLayoutParams();
                        layoutParams.width = dip2px(maxIV);
                        layoutParams.height = dip2px(maxIV);
                        layoutParams.setMargins(0, dip2px(marginTop), 0, 0);
                        ivLaugh.setLayoutParams(layoutParams);
                        tempImageView = ivLaugh;
                        //设置当前标签栏样式
                        ivRead.setImageResource(R.drawable.read);
                        ivLaugh.setImageResource(R.drawable.laugh2);
                        ivNews.setImageResource(R.drawable.new1);

                        tvRead.setTextColor(v.getContext().getResources().getColor(R.color.initColor));
                        tvLaugh.setTextColor(v.getContext().getResources().getColor(R.color.selectColor));
                        tvNews.setTextColor(v.getContext().getResources().getColor(R.color.initColor));
                    }
                    break;
                case R.id.rlNews:
                    viewPager.setCurrentItem(2);
                    if (tempImageView != ivNews) {
                        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) tempImageView.getLayoutParams();
                        layoutParams1.width = dip2px(minIV);
                        layoutParams1.height = dip2px(minIV);
                        layoutParams1.setMargins(0, dip2px(marginTopMin), 0, 0);
                        tempImageView.setLayoutParams(layoutParams1);
                        //设置当前凸起页
                        bottomOutNavigation.setCount(3);
                        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) ivNews.getLayoutParams();
                        layoutParams.width = dip2px(maxIV);
                        layoutParams.height = dip2px(maxIV);
                        layoutParams.setMargins(0, dip2px(marginTop), 0, 0);
                        ivNews.setLayoutParams(layoutParams);
                        tempImageView = ivNews;
                        //设置当前标签栏样式
                        ivRead.setImageResource(R.drawable.read);
                        ivLaugh.setImageResource(R.drawable.laugh);
                        ivNews.setImageResource(R.drawable.new2);
                        tvRead.setTextColor(v.getContext().getResources().getColor(R.color.initColor));
                        tvLaugh.setTextColor(v.getContext().getResources().getColor(R.color.initColor));
                        tvNews.setTextColor(v.getContext().getResources().getColor(R.color.selectColor));
                    }
                    break;
            }
        }
    }
    /**
     * 根据屏幕的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (position){
                case 0:
                    ivRead.setImageResource(R.drawable.read2);
                    ivLaugh.setImageResource(R.drawable.laugh);
                    ivNews.setImageResource(R.drawable.new1);

                    tvRead.setTextColor(getResources().getColor(R.color.selectColor));
                    tvLaugh.setTextColor(getResources().getColor(R.color.initColor));
                    tvNews.setTextColor(getResources().getColor(R.color.initColor));
                    break;
                case 1:
                    ivRead.setImageResource(R.drawable.read);
                    ivLaugh.setImageResource(R.drawable.laugh2);
                    ivNews.setImageResource(R.drawable.new1);

                    tvRead.setTextColor(getResources().getColor(R.color.initColor));
                    tvLaugh.setTextColor(getResources().getColor(R.color.selectColor));
                    tvNews.setTextColor(getResources().getColor(R.color.initColor));
                    break;
                case 2:
                    ivRead.setImageResource(R.drawable.read);
                    ivLaugh.setImageResource(R.drawable.laugh);
                    ivNews.setImageResource(R.drawable.new2);

                    tvRead.setTextColor(getResources().getColor(R.color.initColor));
                    tvLaugh.setTextColor(getResources().getColor(R.color.initColor));
                    tvNews.setTextColor(getResources().getColor(R.color.selectColor));
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}
