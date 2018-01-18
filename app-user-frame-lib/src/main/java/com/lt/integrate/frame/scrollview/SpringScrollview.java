package com.lt.integrate.frame.scrollview;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

/**
 * Created by iclick on 2018/1/15.
 * 通过getChildAt(0)方法获取到scrollView的唯一一个子view—inner， 并用矩形Rect–>normal记录子view的初始位置(left,top,right，bottom)；
 在onTouchEvent()方法的ACTION_MOVE中，根据子view inner滑动的距离，用view.setLayout(left,top,right,bottom)对子view inner进行滑动;
 在onTouchEvent()方法的ACTION_UP中，对子view –> inner设置帧动画，让其回缩到原始位置。

 出现的问题：
 scrollView中的子view，必须是溢出全屏的(比如说子view中含有较长的listview之类)，才能实现弹性效果，
 就是设置scrollView或者它的子View的layout_height为match_content也没用。这种效果就看起来很尴尬，
 在安全中心，退出程序之类的按钮上滑动就没反应，在下面灰色部分上下滑动就有弹性效果。这是个bug，得治！

 解决原理：
 根据事件分发机制，重写子view的dispatchTouchEvent(),onInterceptTouchEvent(),onTouchEvent();
 根据事件分发机制，重写父类->scrollView的dispatchTouchEvent(),onInterceptTouchEvent(),onTouchEvent()
 关于android的事件分发机制，在文章http://blog.csdn.net/a394268045/article/details/51889797 有详细介绍

 最终解决方案
 如果在子view中处理，那么这个父View BounceScrollView就没有通用性，而且，当子view类型不同时，还得处理各种想不到的问题。
 所以，在父view中处理弹性效果是最好的。但是，当我在子view上下滑动时，BounceScrollView居然没有调用onTouchEvent()！
 而不管是在画红圈区域上下滑动，还是在按钮上滑动，都会调用父View的dispatchTouchEvent()方法。OK，
 那我们就可以将弹性效果放到dispatchTouchEvent()中，并加上上下滑动的判断，最终代码如下：
 */

public class SpringScrollview extends ScrollView {

    private View inner;// 孩子View
    private float y;// 点击时y坐标
    private Rect normal = new Rect();// 矩形(这里只是个形式，只是用于判断是否需要动画.)
    private boolean isCount = false;// 是否开始计算

    public SpringScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /***
     * 根据 XML 生成视图工作完成.该函数在生成视图的最后调用，在所有子视图添加完之后. 即使子类覆盖了 onFinishInflate
     * 方法，也应该调用父类的方法，使该方法得以执行.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() > 0) {
            inner = getChildAt(0);
        }
    }

    /***
     * 监听touch
     */
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (inner != null) {
            commOnTouchEvent(ev);
        }

        return super.onTouchEvent(ev);
    }

    /***
     * 触摸事件
     *
     * @param ev
     */
    public void commOnTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                // 手指松开.
                if (isNeedAnimation()) {
                    animation();
                    isCount = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                final float preY = y;// 按下时的y坐标
                float nowY = ev.getY();// 时时y坐标
                int deltaY = (int) (preY - nowY);// 滑动距离
                if (!isCount) {
                    deltaY = 0; // 在这里要归0.
                }

                y = nowY;
                // 当滚动到最上或者最下时就不会再滚动，这时移动布局
                if (isNeedMove()) {
                    // 初始化头部矩形
                    if (normal.isEmpty()) {
                        // 保存正常的布局位置
                        normal.set(inner.getLeft(), inner.getTop(),
                                inner.getRight(), inner.getBottom());
                    }
                    // 移动布局
                    inner.layout(inner.getLeft(), inner.getTop() - deltaY / 2,
                            inner.getRight(), inner.getBottom() - deltaY / 2);
                }
                isCount = true;
                break;

            default:
                break;
        }
    }

    /***
     * 回缩动画
     */
    public void animation() {
        // 开启移动动画
        TranslateAnimation ta = new TranslateAnimation(0, 0, inner.getTop(), normal.top);
        ta.setDuration(200);
        inner.startAnimation(ta);
        // 设置回到正常的布局位置
        inner.layout(normal.left, normal.top, normal.right, normal.bottom);

        normal.setEmpty();

    }

    // 是否需要开启动画
    public boolean isNeedAnimation() {
        return !normal.isEmpty();
    }

    /***
     * 是否需要移动布局 inner.getMeasuredHeight():获取的是控件的总高度
     * getHeight()：获取的是屏幕的高度
     * @return
     */
    public boolean isNeedMove() {
        int offset = inner.getMeasuredHeight() - getHeight();
        int scrollY = getScrollY();
        // 0是顶部，后面那个是底部
        if (scrollY == 0 || scrollY == offset) {
            return true;
        }
        return false;
    }
}
