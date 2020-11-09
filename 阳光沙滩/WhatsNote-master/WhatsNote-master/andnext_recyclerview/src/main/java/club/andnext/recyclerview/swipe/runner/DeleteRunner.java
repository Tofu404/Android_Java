package club.andnext.recyclerview.swipe.runner;

import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;

import club.andnext.recyclerview.swipe.SwipeActionHelper;
import club.andnext.recyclerview.swipe.SwipeRunner;

public class DeleteRunner extends SwipeRunner {

    View actionView;
    View slideView;

    int snapState;

    public DeleteRunner() {
        super(DIRECTION_RTL);
    }

    @Override
    public SwipeRunner accept(float distance) {
        if (actionView == null || slideView == null) {
            return null;
        }

        return super.accept(distance);
    }

    @Override
    public void add(View view) {
        this.actionView = view;
        this.slideView = null;

        if (view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;
            if (group.getChildCount() > 0) {
                View child = group.getChildAt(0);
                this.slideView = child;
            }
        }
    }

    @Override
    public void reset() {
        super.reset();

        this.snapState = SNAP_RIGHT;

        {
            this.actionView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public void expand(boolean smooth) {
        {
            this.actionView.setVisibility(View.VISIBLE);
        }

        {
            this.clearAnimation();
        }

        {
            float x = this.getFinalX();

            boolean showAction = true;
            int slideWidth = getWidth(slideView);

            float start = x;
            float end = (showAction) ? (-slideWidth) : 0;

            AnimationHelper anim = new AnimationHelper(ANIM_RECOVER, start, end);
            if (!smooth) {
                anim.setDuration(0);
            }
            this.addAnimation(anim);
        }
    }

    @Override
    public void onBegin() {
        super.onBegin();

        {
            this.snapState = SNAP_RIGHT;
        }

        {
            this.actionView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMove(float deltaX) {
        super.onMove(deltaX);

        float tx = this.getTranslationX();
        float max = this.getMaxDistance();

        // scroll offset
        if (tx <= -max) {
            if (scrollOffset == 0 && getAnimation(ANIM_SCROLL) == null) {

                float start = scrollOffset;
                float target = swipeView.getWidth() * 0.928f - Math.abs(tx);

                AnimationHelper anim = new AnimationHelper(ANIM_SCROLL, start, target);
                this.addAnimation(anim);
            }
        }

        // child
        {
            int state = (tx <= -max) ? SNAP_LEFT : SNAP_RIGHT;
            if (snapState != state) {

                this.removeAnimation(ANIM_SLIDE);

                float x = this.getFinalX();
                float startX = this.getSlideX(x, snapState);
                float targetX = this.getSlideX(x, state);
                if (startX != targetX) {
                    AnimationHelper anim = new AnimationHelper(ANIM_SLIDE, startX, targetX);
                    this.addAnimation(anim);
                }
            }

            this.snapState = state;
        }

    }

    @Override
    public void onEnd(float velocityX) {

        Integer action = null;

        float x = this.getFinalX();
        float tx = this.getTranslationX();
        float max = this.getMaxDistance();

        boolean showAction = false;
        int slideWidth = slideView.getWidth();

        {
            this.clearAnimation();
        }

        float start;
        float end = 0;

        while (true) {
            if (x == 0) {
                break;
            }

            if (x > 0) {
                start = x;
                end = 0;

                AnimationHelper anim = new AnimationHelper(ANIM_RECOVER, start, end);
                this.addAnimation(anim);

                break;
            }

            if (tx <= -max) {
                action = SwipeActionHelper.ACTION_RIGHT;

                start = x;
                end = 0;

                AnimationHelper anim = new AnimationHelper(ANIM_RECOVER, start, end);
                this.addAnimation(anim);

                break;
            }

            boolean fling = this.isFling(velocityX);
            if (fling) {
                showAction = true;

                if (velocityX > 0) {
                    showAction = false;
                }

            } else {
                boolean extend = (this.scrollOffset > 0 || getAnimation(ANIM_SCROLL) != null);
                if (extend) {
                    if (tx < -slideWidth) {
                        showAction = true;
                    }
                } else {
                    if (tx < -slideWidth / 2) {
                        showAction = true;
                    }
                }
            }

            {
                start = x;
                end = (showAction) ? (-slideWidth) : 0;

                AnimationHelper anim = new AnimationHelper(ANIM_RECOVER, start, end);
                this.addAnimation(anim);

                break;
            }
        }

        {
            super.onEnd(velocityX);
        }

        if (action != null) {
            parent.notifyActionBegin(this, action);
            parent.notifyActionEnd(this, action);
        }

        if (end != 0) {
            parent.notifyExpand(this);
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float x = this.getFinalX();

        // swipe view
        {
            float tx = x;
            swipeView.setTranslationX(tx);
        }

        // parent
        {
            float tx = x;
            tx += swipeView.getWidth();
            actionView.setTranslationX(tx);
        }

        // child
        {
            float tx = x;
            tx = getSlideX(tx, this.snapState);

            AnimationHelper anim = getAnimation(ANIM_SLIDE);
            if (anim != null) {
                float fx = tx;

                tx = anim.getValue();
                if (snapState == SNAP_RIGHT) {
                    tx = (tx > fx) ? fx : tx;
                }
            }

            slideView.setTranslationX(tx);
        }
    }

    @Override
    public float getFinalX() {

        AnimationHelper anim;

        if ((anim = getAnimation(ANIM_RECOVER)) != null) {
            this.initialDistance = anim.getValue();
            return initialDistance;
        }

        if ((anim = getAnimation(ANIM_SCROLL)) != null) {
            this.scrollOffset = anim.getValue();
        }

        return super.getFinalX();
    }

    @Override
    protected float getMaxDistance() {
        float f = 0.8f;
        float max = f * swipeView.getWidth();
        return max;
    }

    float getSlideX(float tx, int state) {

        if (state == SNAP_LEFT) {
            tx = 0;
        } else {
            tx += slideView.getWidth();
            tx = -tx;
            tx = (tx < 0)? 0: tx;
        }

        return tx;
    }

}
