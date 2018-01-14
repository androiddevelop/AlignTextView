package me.codeboy.android.aligntextview.example;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import me.codeboy.android.aligntextview.example.adpater.TextViewAdapter;

/**
 * AlignTextView例子 recyclerView例子
 *
 * @author yuedong.li
 */
public class TextViewRecyclerViewExample extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_align_text_view_recycler_view);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final TextViewAdapter adapter = new TextViewAdapter(this);
        List<String> texts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            texts.add("欢迎访问codeboy.me，序号:" + i + "欢迎访问codeboy.me，序号:" + i + "欢迎访问codeboy.me，序号:" + i);
        }
        adapter.setData(texts);
        recyclerView.setAdapter(adapter);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                List<String> texts2 = new ArrayList<>();
                for (int i = 20; i < 40; i++) {
                    texts2.add("欢迎访问codeboy.me，序号:" + i);
                }
                adapter.appendData(texts2);
                adapter.notifyDataSetChanged();
            }
        }, 100);

    }
}