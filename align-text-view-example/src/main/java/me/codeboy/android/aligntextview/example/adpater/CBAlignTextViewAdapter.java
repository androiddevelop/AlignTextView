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
import me.codeboy.android.aligntextview.example.vh.CBAlignTextViewViewHolder;

/**
 * adapter
 * Created by YD on 10/14/2017.
 */
public class CBAlignTextViewAdapter extends RecyclerView.Adapter<CBAlignTextViewViewHolder> {
    private List<String> mTexts = new ArrayList<>();
    private Context mContext;

    public CBAlignTextViewAdapter(Context context) {
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
    public CBAlignTextViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.activity_cb_align_text_view_recycler_view_item, parent, false);
        return new CBAlignTextViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CBAlignTextViewViewHolder holder, int position) {
        String text = mTexts.get(position);
        if (!TextUtils.isEmpty(text)) {
            holder.cbAlignTextView.setPunctuationConvert(true);
            holder.cbAlignTextView.reset();
            holder.cbAlignTextView.setText(mTexts.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mTexts.size();
    }
}
