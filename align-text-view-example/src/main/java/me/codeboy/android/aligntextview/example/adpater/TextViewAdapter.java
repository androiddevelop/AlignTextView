package me.codeboy.android.aligntextview.example.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.codeboy.android.aligntextview.example.R;
import me.codeboy.android.aligntextview.example.vh.TextViewViewHolder;

/**
 * adapter
 * Created by YD on 01/14/2018.
 */
public class TextViewAdapter extends RecyclerView.Adapter<TextViewViewHolder> {
    private List<String> mTexts = new ArrayList<>();
    private Context mContext;

    public TextViewAdapter(Context context) {
        mContext = context;
    }

    /**
     * 设置数据
     * @param texts 文本
     */
    public void setData(List<String> texts) {
        if (texts == null || texts.size() == 0) {
            return;
        }
        mTexts.clear();
        mTexts.addAll(texts);
    }

    /**
     * 追加数据
     * @param texts 文本
     */
    public void appendData(List<String> texts) {
        if (texts == null || texts.size() == 0) {
            return;
        }
        mTexts.addAll(texts);
    }

    @Override
    public TextViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_text_view_recycler_view_item, parent, false);
        return new TextViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(TextViewViewHolder holder, int position) {
        String text = mTexts.get(position);
        if (!TextUtils.isEmpty(text)) {
            holder.textView.setText(mTexts.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mTexts.size();
    }
}
