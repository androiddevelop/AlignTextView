package me.codeboy.android.aligntextview.example.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.codeboy.android.aligntextview.CBAlignTextView;
import me.codeboy.android.aligntextview.example.R;

/**
 * vh
 * Created by YD on 09/01/2017.
 */

public class CBAlignTextViewViewHolder extends RecyclerView.ViewHolder {
    public CBAlignTextView cbAlignTextView;

    public CBAlignTextViewViewHolder(View itemView) {
        super(itemView);
        cbAlignTextView = (CBAlignTextView) itemView.findViewById(R.id.cb_align_text_view);
    }
}
