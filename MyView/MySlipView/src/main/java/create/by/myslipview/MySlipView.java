package create.by.myslipview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

public class MySlipView extends ViewGroup implements View.OnClickListener {

    private static final String TAG = "tianze";
    private View mInflateView;
    private View mChildAt;

    //定义监听接口
    private MyOnClickListener mMyOnClickListener = null;

    //滑动器
    private Scroller mScroller = new Scroller(getContext());
    private float mDownX;
    private float mMoveX;
    private float mUpX;
    private float mInterceptDownX;
    private float mInterceptMoveX;
    private int mTotalDuration;

    public MySlipView(Context context) {
        this(context, null);
    }

    public MySlipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySlipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //读取属性信息
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MySlipView, defStyleAttr, 0);
        int function = a.getInt(R.styleable.MySlipView_functions, 2);
        a.recycle();
    }

    @Override
    public void addView(View child) {

        //在没有触发添加子view的时候判断是否只有一个子view
        if (getChildCount() > 1) {
            throw new IllegalArgumentException("MySlipView can host only one direct child");
        }

        super.addView(child);
    }

    @Override
    public void addView(View child, int index) {
        if (getChildCount() > 1) {
            throw new IllegalStateException("MySlipView can host only one direct child");
        }

        super.addView(child, index);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        if (getChildCount() > 1) {
            throw new IllegalStateException("MySlipView can host only one direct child");
        }

        super.addView(child, params);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > 1) {
            throw new IllegalStateException("MySlipView can host only one direct child");
        }

        super.addView(child, index, params);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        //添加编辑子view
        mInflateView = LayoutInflater.from(getContext()).inflate(R.layout.layout_slip_item, this, false);
        iniView();
        addView(mInflateView);
    }

    private void iniView() {
        View readTv = mInflateView.findViewById(R.id.read_tv);
        View topTv = mInflateView.findViewById(R.id.top_tv);
        View deleteTv = mInflateView.findViewById(R.id.delete_tv);

        readTv.setOnClickListener(this);
        topTv.setOnClickListener(this);
        deleteTv.setOnClickListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /*获取父控件的大小*/
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);

        /*测量内容部分的宽高*/
        mChildAt = getChildAt(0);
        LayoutParams lp = mChildAt.getLayoutParams();
        int contentHeight = lp.height;
        int contentWidth = lp.width;
        int contentHeightMeasureSpec;
        switch (contentHeight) {
            case LayoutParams.MATCH_PARENT:
                contentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(parentHeight, MeasureSpec.EXACTLY);
                break;
            case LayoutParams.WRAP_CONTENT:
                contentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(parentHeight, MeasureSpec.AT_MOST);
                break;
            default:
                contentHeightMeasureSpec = MeasureSpec.makeMeasureSpec(contentHeight, MeasureSpec.EXACTLY);
                break;
        }
        mChildAt.measure(widthMeasureSpec, contentHeightMeasureSpec);

        /*测量编辑部分的内容*/
        int contentMeasuredHeight = mChildAt.getMeasuredHeight(); //获取到内容部分测量之后的高度
        //约定编辑的宽度是总的宽度的3/4;
        int editWidthSize = parentWidth / 2;
        //测量编辑部分的内容
        int inflateViewWidthSpec = MeasureSpec.makeMeasureSpec(editWidthSize, MeasureSpec.EXACTLY);
        int inflateViewHeightSpec = MeasureSpec.makeMeasureSpec(contentMeasuredHeight, MeasureSpec.EXACTLY);
        mInflateView.measure(inflateViewWidthSpec, inflateViewHeightSpec);

        /*测量viewGroup本身的大小并保存*/
        setMeasuredDimension(mChildAt.getMeasuredWidth() + editWidthSize, contentMeasuredHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //摆放内容部分
        int childLeft = 0;
        int childTop = 0;
        int childRight = childLeft + mChildAt.getMeasuredWidth();
        int childBottom = childTop + mChildAt.getMeasuredHeight();
        mChildAt.layout(childLeft, childTop, childRight, childBottom);
        //摆放编辑部分
        int editLeft = childRight;
        int editTop = 0;
        int editRight = editLeft + mInflateView.getMeasuredWidth();
        int editBottom = editTop + mInflateView.getMeasuredHeight();
        mInflateView.layout(editLeft, editTop, editRight, editBottom);
    }

    public void setMyOnClickListener(MyOnClickListener listener) {
        if (listener != null) {
            mMyOnClickListener = listener;
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (mMyOnClickListener != null) {
            if (id == R.id.read_tv) {
                mMyOnClickListener.onReadClick();
            } else if (id == R.id.top_tv) {
                mMyOnClickListener.onTopClick();
            } else if (id == R.id.delete_tv) {
                mMyOnClickListener.onDeleteClick();
            }
            mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0, mTotalDuration);
            invalidate();
        }
    }

    public interface MyOnClickListener {
        void onReadClick();

        void onTopClick();

        void onDeleteClick();
    }

    private boolean hasOpen = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == ACTION_DOWN) {
            mDownX = event.getX();
        } else if (action == MotionEvent.ACTION_MOVE) {
            //获取已经滑动的距离
            int scrollX = getScrollX();
            mMoveX = event.getX();
            //计算差值
            float dx = mMoveX - mDownX;
            float result = scrollX - dx;
            if (result < 0) {
                scrollTo(0, 0);
            } else if (result > mInflateView.getMeasuredWidth()) {
                scrollTo(mInflateView.getMeasuredWidth(), 0);
                hasOpen = true;
            } else {
                scrollBy((-(int) dx), 0);
            }
            mDownX = mMoveX;
        } else if (action == MotionEvent.ACTION_UP) {
            mUpX = event.getX();
            mTotalDuration = 1000;
            float needDuration = mTotalDuration * (getScrollX() / (float) mInflateView.getMeasuredWidth());
            if (hasOpen == false && getScrollX() > mInflateView.getMeasuredWidth() / 3) {
                mScroller.startScroll(getScrollX(), 0, mInflateView.getMeasuredWidth() - getScrollX(), 0, (int) needDuration);
                hasOpen = true;
                Log.e(TAG, "onTouchEvent: 1");
            } else if (hasOpen == true && getScrollX() > mInflateView.getMeasuredWidth() / 3) {
                mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0, (int) needDuration);
                hasOpen = false;
                Log.e(TAG, "onTouchEvent: 2");
            } else {
                mScroller.startScroll(getScrollX(), 0, -getScrollX(), 0, (int) needDuration);
                hasOpen = false;
                Log.e(TAG, "onTouchEvent: 3");
            }
            invalidate();
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            int currX = mScroller.getCurrX();
            scrollTo(currX, 0);
            invalidate();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case ACTION_DOWN:
                mInterceptDownX = ev.getX();
                break;
            case ACTION_MOVE:
                mInterceptMoveX = ev.getX();
                if (Math.abs(mInterceptDownX - mInterceptMoveX) > 0) {
                    Log.e(TAG, "onInterceptTouchEvent: " + (Math.abs(mInterceptDownX - mInterceptMoveX) > 0));
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }
}
