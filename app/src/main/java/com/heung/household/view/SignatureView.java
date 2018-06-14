package com.heung.household.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.base.library.util.CodeUtil;
import com.base.library.view.papyrus.PapyrusView;
import com.base.library.view.papyrus.PathPointBean;

import java.util.ArrayList;

/**
 * 作者：王东一
 * 创建时间：2017/8/21.
 */

public class SignatureView extends View {
    private Paint linePaint, backgroundPaint;
    public ArrayList<PathPointBean> pathList = new ArrayList<>();
    public ArrayList<PathPointBean> pathNextList = new ArrayList<>();
    private PapyrusView.onListen onListen;
    private int onDownPosition;

    public int getOnDownPosition() {
        return onDownPosition;
    }

    public void setOnDownPosition(int onDownPosition) {
        this.onDownPosition = onDownPosition;
    }

    private PapyrusHandler handler = new PapyrusHandler();

    public ArrayList<PathPointBean> getPathList() {
        return pathList;
    }

    public void setPathList(ArrayList<PathPointBean> pathList) {
        this.pathList = pathList;
    }

    public ArrayList<PathPointBean> getPathNextList() {
        return pathNextList;
    }

    public void setPathNextList(ArrayList<PathPointBean> pathNextList) {
        this.pathNextList = pathNextList;
    }

    public PapyrusView.onListen getOnListen() {
        return onListen;
    }

    public void setOnListen(PapyrusView.onListen onListen) {
        this.onListen = onListen;
    }

    private int width, height;

    public SignatureView(Context context) {
        super(context);
        init();
    }

    public SignatureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SignatureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SignatureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public interface onListen {
        void canClean(boolean clean);

        void canBack(boolean back);

        void canNext(boolean next);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, width, height, backgroundPaint);
        for (int i = 0; i < onDownPosition; i++) {
            canvas.drawPath(pathList.get(i).getPath(), linePaint);
        }
        if (onDownPosition < pathList.size())
            canvas.drawPath(pathList.get(onDownPosition).getPath(), linePaint);
        if (getOnListen() != null) {
            //前进
            if (pathList.size() > 0) {
                getOnListen().canBack(true);
            } else {
                getOnListen().canBack(false);
            }
            //后退
            if (pathNextList.size() > 0) {
                getOnListen().canNext(true);
            } else {
                getOnListen().canNext(false);
            }
            //删除
            if (pathList.size() == 0 && pathNextList.size() == 0) {
                getOnListen().canClean(false);
            } else {
                getOnListen().canClean(true);
            }
        }
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
        width=widthMeasureSpec;
        height=heightMeasureSpec;
    }

    private void init() {
        //画笔
        linePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        linePaint.setColor(ContextCompat.getColor(getContext(), com.base.library.R.color.text_black));
        linePaint.setAntiAlias(true);
        linePaint.setStrokeWidth(5);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeCap(Paint.Cap.ROUND);
        //背景
        backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setTextAlign(Paint.Align.CENTER);
        backgroundPaint.setColor(ContextCompat.getColor(getContext(), com.base.library.R.color.transparent));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getPointerCount() == 1) {
                    PathPointBean pathPointBean = new PathPointBean();
                    int position = pathList.size() > 0 ? pathList.size() : 0;
                    pathPointBean.setPosition(position);
                    pathPointBean.setPath(new Path());
                    pathList.add(pathPointBean);
                    onDownPosition = position;
                    pathList.get(onDownPosition).getPath().moveTo(event.getX(), event.getY());
                    getParent().requestDisallowInterceptTouchEvent(true);
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1) {
                    pathList.get(onDownPosition).getPath().lineTo(event.getX(), event.getY());
                    if (pathNextList.size() > 0)
                        pathNextList.clear();
                    invalidate();
                } else {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
        }

        return true;
    }

    public void clean() {
        onDownPosition = 0;
        pathList.clear();
        pathNextList.clear();
        invalidate();
    }

    public void back() {
        if (pathList.size() > 0) {
            int end = pathList.size() - 1;
            PathPointBean bean = new PathPointBean();
            bean.setPath(pathList.get(end).getPath());
            bean.setPosition(pathList.get(end).getPosition());
            pathNextList.add(bean);
            pathList.remove(end);
            if (pathList.size() == 0) {
                onDownPosition = end;
                handler.sendEmptyMessage(1);
            } else
                for (int i = 0; i < pathList.size(); i++) {
                    onDownPosition = i;
                    handler.sendEmptyMessage(1);
                }
        }
    }

    public void next() {
        if (pathNextList.size() > 0) {
            int end = pathNextList.size() - 1;
            PathPointBean bean = new PathPointBean();
            bean.setPath(pathNextList.get(end).getPath());
            bean.setPosition(pathNextList.get(end).getPosition());
            pathList.add(bean);
            pathNextList.remove(end);
            if (pathList.size() == 0) {
                onDownPosition = end;
                handler.sendEmptyMessage(1);
            } else
                for (int i = 0; i < pathList.size(); i++) {
                    onDownPosition = i;
                    handler.sendEmptyMessage(1);
                }
        }
    }

    @SuppressLint("HandlerLeak")
    private class PapyrusHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    invalidate();
                    break;
            }
        }
    }

}
