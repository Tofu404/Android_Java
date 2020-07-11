package create.by.mysimpleview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BounceView extends View {

    public BounceView(Context context) {
        this(context,null);
    }

    public BounceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BounceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
