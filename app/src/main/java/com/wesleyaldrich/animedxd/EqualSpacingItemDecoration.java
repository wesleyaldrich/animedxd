package com.wesleyaldrich.animedxd;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class EqualSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private final int spanCount;
    private final int spacingPx; // dalam px

    public EqualSpacingItemDecoration(int spanCount, int spacingPx) {
        this.spanCount = spanCount;
        this.spacingPx = spacingPx;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        // Horizontal: gap hanya di sela kolom
        if (column == 0) {              // kolom paling kiri
            outRect.left = 0;
            outRect.right = spacingPx / 2;
        } else if (column == spanCount - 1) { // kolom paling kanan
            outRect.left = spacingPx / 2;
            outRect.right = 0;
        } else {                        // kolom tengah
            outRect.left = spacingPx / 2;
            outRect.right = spacingPx / 2;
        }

        // Vertikal: kasih jarak antar baris, tapi baris pertama jangan
        if (position >= spanCount) outRect.top = spacingPx;
        // outRect.bottom = 0; // opsional
    }
}
