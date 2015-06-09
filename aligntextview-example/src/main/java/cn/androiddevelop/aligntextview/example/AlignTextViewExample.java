package cn.androiddevelop.aligntextview.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import test.NewTextView;

/**
 * AlignTextView例子
 *
 * @author YD
 */
public class AlignTextViewExample extends Activity {

    NewTextView alignTv;
    TextView justifyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_align_text_view);

        justifyTv = (TextView) findViewById(R.id.justifyTv);
        alignTv = (NewTextView) findViewById(R.id.alignTv);

        String text = "这是一段中英文混合的文本，aaaaaaaaaaaaaaaaaaaaaaaaaaaaa 用于测试TextView文本对齐，AlignTextView可以通过setAlign()方法设置每一段尾行的对齐方式，默认尾行居左对齐。 欢迎访问androiddevelop.cn";
        text += "\n\n" + text;
        justifyTv.setText(text);
        alignTv.setText(text);

        // 设置对齐方式，默认靠左
        // alignTv.setAlign(Align.ALIGN_CENTER);
    }
}