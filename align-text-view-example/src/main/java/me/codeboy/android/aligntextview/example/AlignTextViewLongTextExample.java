package me.codeboy.android.aligntextview.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.TextView;

import me.codeboy.android.aligntextview.AlignTextView;
import me.codeboy.common.base.net.CBHttp;

/**
 * AlignTextView 长文本例子
 *
 * @author yuedong.li
 */
public class AlignTextViewLongTextExample extends Activity {

    private AlignTextView mTextViewTv;
    private TextView mTextViewTv2;
    private String text = "这是一段中英文混合的文本,I am very happy today." +
            "测试TextView文本对齐\n\nAlignTextView可以通过setAlign()" +
            "方法设置每一段尾行的对齐方式,默认尾行居左对齐" +
            ".CBAlignTextView可以像原生TextView一样操作，但是需要使用getRealText()获取文本,欢迎访问open.codeboy.me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_align_text_view_long_text);

        mTextViewTv = (AlignTextView) findViewById(R.id.align_text_view);
        mTextViewTv2 = (TextView) findViewById(R.id.text_view);

        mTextViewTv.setText(text);
        mTextViewTv2.setText(text);

        mTextViewTv.setMovementMethod(new ScrollingMovementMethod());
        mTextViewTv2.setMovementMethod(new ScrollingMovementMethod());


        final Handler handler = new Handler() {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);

//                String text = msg.getData().getString("text");
                text = text + text;
                text = text + text;
                text = text + text;
                mTextViewTv.setText(text);
                mTextViewTv2.setText(text);
            }
        };

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                    String text = CBHttp.getInstance().connect("http://test.codeboy.me/text")
                            .execute();
                    Message message = new Message();
                    message.getData().putString("text", text);
                    message.what = 0;
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}