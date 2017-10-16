package me.codeboy.android.aligntextview.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;

import me.codeboy.android.aligntextview.CBAlignTextView;

/**
 * AlignTextView 空指针
 *
 * @author yuedong.li
 */
public class AlignTextViewNpeExample extends Activity {

    private CBAlignTextView mTextViewTv;
    private String text = "这是一段中英文混合的文本,I am very happy today." +
            "测试TextView文本对齐\n\nAlignTextView可以通过setAlign()" +
            "方法设置每一段尾行的对齐方式,默认尾行居左对齐" +
            ".CBAlignTextView可以像原生TextView一样操作，但是需要使用getRealText()获取文本,欢迎访问open.codeboy.me";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_align_text_view_npe_text);

        mTextViewTv = (CBAlignTextView) findViewById(R.id.align_text_view);
        mTextViewTv.setText(text);



        final Handler handler = new Handler() {
            @Override
            public void dispatchMessage(Message msg) {
                super.dispatchMessage(msg);
                mTextViewTv.setText("");
            }
        };

        new Thread() {
            public void run() {
                try {
                    Thread.sleep(3000);
                    handler.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}