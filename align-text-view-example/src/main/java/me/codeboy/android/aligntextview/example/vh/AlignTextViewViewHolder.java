package me.codeboy.android.aligntextview.example.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.codeboy.android.aligntextview.AlignTextView;
import me.codeboy.android.aligntextview.example.R;

/**
 * vh
 * Created by YD on 09/01/2017.
 */

public class AlignTextViewViewHolder extends RecyclerView.ViewHolder {
    public AlignTextView alignTextView;

    public AlignTextViewViewHolder(View itemView) {
        super(itemView);
        alignTextView = (AlignTextView) itemView.findViewById(R.id.align_text_view);
    }
}
