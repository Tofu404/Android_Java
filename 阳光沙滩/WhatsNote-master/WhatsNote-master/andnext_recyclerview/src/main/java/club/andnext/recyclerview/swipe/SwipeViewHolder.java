package club.andnext.recyclerview.swipe;

import android.graphics.Canvas;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import club.andnext.recyclerview.bridge.BridgeViewHolder;
import club.andnext.recyclerview.decoration.MarginDividerDecoration;

import java.util.Collections;
import java.util.List;

public abstract class SwipeViewHolder<T> extends BridgeViewHolder<T> implements SwipeActionHelper.Adapter, MarginDividerDecoration.Adapter {

    SwipeHolder swipeHolder;

    @Keep
    public SwipeViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public abstract int getLayoutResourceId();

    @Override
    public abstract void onViewCreated(@NonNull View view);

    @Override
    @CallSuper
    public void onBind(T item, int position) {

        itemView.setVisibility(View.VISIBLE);

        if (swipeHolder != null) {
            swipeHolder.reset();
        }
    }

    @CallSuper
    @Override
    public void onViewDetachedFromWindow() {
        super.onViewDetachedFromWindow();

        itemView.setVisibility(View.VISIBLE);

        if (swipeHolder != null) {
            swipeHolder.reset();
        }
    }

    public void hide() {
        itemView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActionBegin(SwipeActionHelper helper, int action) {

    }

    @Override
    public void onActionEnd(SwipeActionHelper helper, int action) {

    }

    protected void setSwipeHolder(SwipeHolder holder) {
        this.swipeHolder = holder;
    }

    protected SwipeHolder getSwipeHolder() {
        return this.swipeHolder;
    }

    @Override
    public boolean isSwiped(SwipeActionHelper helper) {
        if (swipeHolder != null) {
            return swipeHolder.isSwiped();
        }

        return false;
    }

    @Override
    public SwipeActionHelper.Adapter getActive(SwipeActionHelper helper) {
        if (swipeHolder != null) {
            if (swipeHolder.getActive() != null) {
                return this;
            }
        }

        return null;
    }

    @Override
    public int getDirection(SwipeActionHelper helper) {
        if (swipeHolder != null) {
            return swipeHolder.getDirection();
        }

        return 0;
    }

    @Override
    public View getSwipeView(SwipeActionHelper helper) {
        if (swipeHolder != null) {
            return swipeHolder.getSwipeView();
        }

        return null;
    }

    @Override
    public List<View> getTouchable(SwipeActionHelper helper) {
        return Collections.emptyList();
    }

    @Override
    public void clear(SwipeActionHelper helper) {
        if (swipeHolder != null) {
            swipeHolder.clear();
        }
    }

    @Override
    public void onBegin(SwipeActionHelper helper) {
        if (swipeHolder != null) {
            swipeHolder.onBegin();
        }
    }

    @Override
    public void onMove(SwipeActionHelper helper, float deltaX) {
        if (swipeHolder != null) {
            swipeHolder.onMove(deltaX);
        }
    }

    @Override
    public void onEnd(SwipeActionHelper helper, float velocityX) {
        if (swipeHolder != null) {
            swipeHolder.onEnd(velocityX);
        }
    }

    @Override
    public void onDraw(SwipeActionHelper helper, Canvas canvas) {
        if (swipeHolder != null) {
            swipeHolder.onDraw(canvas);
        }
    }

    @Override
    public void onDrawOver(SwipeActionHelper helper, Canvas canvas) {
        if (swipeHolder != null) {
            swipeHolder.onDrawOver(canvas);
        }
    }

    @Override
    public void onClear(SwipeActionHelper helper, int direction) {

    }

    @Override
    public float getTranslation(MarginDividerDecoration decoration) {
        if (itemView.getVisibility() != View.VISIBLE) {
            return itemView.getWidth();
        }

        if (swipeHolder != null) {
            return swipeHolder.getTranslationX();
        }

        return 0;
    }
}
