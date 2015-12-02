package me.codeboy.android.aligntextview.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import me.codeboy.android.aligntextview.CBAlignTextView;

/**
 * AlignTextView例子
 *
 * @author YD
 */
public class AlignTextViewExample extends Activity {

    TextView textViewTv;
    TextView alignTv;
    TextView justifyTv;
    CBAlignTextView cbAlignTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_align_text_view);

        textViewTv = (TextView) findViewById(R.id.textview);
        justifyTv = (TextView) findViewById(R.id.justifyTextView);
        alignTv = (TextView) findViewById(R.id.alignTextView);
        cbAlignTv = (CBAlignTextView) findViewById(R.id.cbAlignTextView);

        final String text = "这是一段中英文混合的文本，I am very happy today。 aaaaaaaaaa," +
                "测试TextView文本对齐\n\nAlignTextView可以通过setAlign()方法设置每一段尾行的对齐方式, 默认尾行居左对齐. " +
                "CBAlignTextView可以像原生TextView一样操作, 但是需要使用getRealText()获取文本, 欢迎访问open.codeboy.me";
        textViewTv.setText(text);
        justifyTv.setText(text);
        alignTv.setText(text);
//        cbAlignTv.setPunctuationConvert(true);
        cbAlignTv.setText(text);

        alignTv.setMovementMethod(new ScrollingMovementMethod());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                alignTv.setText(text + text);
            }
        },5000);

    }
}