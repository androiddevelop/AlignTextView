package me.codeboy.android.aligntextview.example;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.TextView;

import me.codeboy.android.aligntextview.AlignTextView;

/**
 * AlignTextView例子
 *
 * @author YD
 */
public class AlignTextViewExample2 extends Activity {

    TextView textViewTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        textViewTv = new AlignTextView(this);

        setContentView(textViewTv);

        String text = "这是一段中英文混合的文本，I am very happy today." +
                "测试TextView文本对齐\n\nAlignTextView可以通过setAlign()方法设置每一段尾行的对齐方式,默认尾行居左对齐" +
                ".CBAlignTextView可以像原生TextView一样操作，但是需要使用getRealText()获取文本,欢迎访问open.codeboy.me";
        textViewTv.setText(text + text + text + text + text + text +
                text);

        textViewTv.setMovementMethod(new ScrollingMovementMethod());
    }
}