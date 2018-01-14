package me.codeboy.android.aligntextview.example.vh;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.codeboy.android.aligntextview.example.R;

/**
 * vh
 * Created by YD on 14/01/2018.
 */

public class TextViewViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public TextViewViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(R.id.text_view);
    }
}
