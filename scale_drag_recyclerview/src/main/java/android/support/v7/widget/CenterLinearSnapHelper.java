package android.support.v7.widget;

import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class CenterLinearSnapHelper extends LinearSnapHelper {
    @Nullable
    private OrientationHelper mVerticalHelper;
    @Nullable
    private OrientationHelper mHorizontalHelper;

    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return this.findCenterView(layoutManager, this.getVerticalHelper(layoutManager));
        } else {
            return layoutManager.canScrollHorizontally() ? this.findCenterView(layoutManager, this.getHorizontalHelper(layoutManager)) : null;
        }
    }

    @Nullable
    private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        } else {
            View closestChild = null;
            int center;
            if (layoutManager.getClipToPadding()) {
                center = helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
            } else {
                center = helper.getEnd() / 2;
            }

            int absClosest = Integer.MAX_VALUE;
            for (int i = 0; i < childCount; ++i) {
                View child = layoutManager.getChildAt(i);

                // int childCenter = helper.getDecoratedStart(child) + helper.getDecoratedMeasurement(child) / 2;

                //---- start -----
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                Rect insets = params.mDecorInsets;
                int childCenter;
                if (layoutManager.canScrollVertically()) {
                    childCenter = helper.getDecoratedStart(child) + insets.top + params.topMargin + child.getMeasuredHeight() / 2;
                } else {
                    childCenter = helper.getDecoratedStart(child) + insets.left + params.leftMargin + child.getMeasuredWidth() / 2;
                }
                //--- end ----

                int absDistance = Math.abs(childCenter - center);
                if (absDistance < absClosest) {
                    absClosest = absDistance;
                    closestChild = child;
                }
            }

            return closestChild;
        }
    }

    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (this.mVerticalHelper == null || this.mVerticalHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }

        return this.mVerticalHelper;
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (this.mHorizontalHelper == null || this.mHorizontalHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }

        return this.mHorizontalHelper;
    }
}