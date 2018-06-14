package com.heung.household.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import com.base.library.activity.BaseActivity;
import com.base.library.fragment.BaseFragment;
import com.base.library.tabLayoutUtil.TabLayoutUtils;
import com.base.library.util.ActivityManage;
import com.base.library.util.CodeUtil;
import com.base.library.util.Exit;
import com.heung.household.R;
import com.heung.household.base.MainApplication;
import com.heung.household.entry.EntryFragment;
import com.heung.household.query.QueryFragment;

import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/20.
 */

public class MainActivity extends BaseActivity {
    private static final String KEY_INDEX = "index";
    private int mCurrentIndex = 0;
    private ViewPager viewPager;
    private TabLayout tab;
    private LinearLayout layout;
    private ArrayList<BaseFragment> listFragment = new ArrayList<>();
    private ArrayList<String> tabList = new ArrayList<>();

    public static String getRybld() {
        return Rybld;
    }

    public static void setRybld(String rybld) {
        Rybld = rybld;
    }

    private static String Rybld;
    private EntryFragment entryFragment;
    private QueryFragment queryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entryFragment = new EntryFragment();
        queryFragment = new QueryFragment();
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            if(entryFragment!=null){
                entryFragment.onDateBack(savedInstanceState);
            }
            if(queryFragment!=null){
                queryFragment.onDateBack(savedInstanceState);
            }
        }
        initView();
        hintStatus();
        hintTitle();
        setCanSwipe(false);
        setStatusBar(true);
        init();
        setRybld("&rybId=" + MainApplication.getLoginBean().getDatas().getID());
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
        if(entryFragment!=null){
            entryFragment.onDateSave(outState);
        }
        if(queryFragment!=null){
            queryFragment.onDateSave(outState);
        }
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tab = (TabLayout) findViewById(R.id.tab);
        layout = (LinearLayout) findViewById(R.id.layout);
    }

    private void init() {
        tabList.add("录入");
        tabList.add("查询");
        entryFragment.setRetainInstance(true);
        listFragment.add(entryFragment);
        queryFragment.setRetainInstance(true);
        tab.addTab(tab.newTab().setText(tabList.get(0)));
        listFragment.add(queryFragment);
        tab.addTab(tab.newTab().setText(tabList.get(1)));
        MyTableViewAdapter myTableViewAdapter = new MyTableViewAdapter(getSupportFragmentManager(), listFragment, tabList);
        viewPager.setAdapter(myTableViewAdapter);
        viewPager.setCurrentItem(mCurrentIndex, false);
        tab.setupWithViewPager(viewPager);
        int width = CodeUtil.px2dip(this, CodeUtil.getScreenWidth(this) / 5);
        TabLayoutUtils.setIndicator(this, tab, width, width);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentIndex = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 监听键盘按键
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //  监听键盘按键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            pressAgainExit();
            return true;
        }
        return super.onKeyDown(keyCode, event);

    }

    /**
     *
     */
    private Exit exit = new Exit();

    /**
     * 再按一次退出程序。
     */
    private void pressAgainExit() {
        if (exit.isExit()) {
            ActivityManage.getInstance().exit();
        } else {
            CodeUtil.showToastShort(this, "连按两次退出程序");
            exit.doExitInOneSecond();
        }
    }
}
