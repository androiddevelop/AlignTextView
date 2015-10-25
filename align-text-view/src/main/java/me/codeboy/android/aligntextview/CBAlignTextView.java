package me.codeboy.android.aligntextview;

import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 对齐的TextView
 * <p>
 * 为了能够使TextView能够很好的进行排版，同时考虑到原生TextView中以word进行分割排版，
 * 那么我们可以将要换行的地方进行添加空格处理，这样就可以在合适的位置换行，同时也不会
 * 打乱原生的TextView的排版换行选择复制等问题。为了能够使右端尽可能的对齐，将右侧多出的空隙
 * 尽可能的分配到该行的标点后面。达到两段对齐的效果。
 * </p>
 * Created by yuedong.lyd on 6/28/15.
 */
public class CBAlignTextView extends TextView {
    private List<Integer> addCharPosition = new ArrayList<Integer>();  //增加空格的位置
    private final char SPACE = ' '; //空格;
    private CharSequence oldText = ""; //旧文本，本来应该显示的文本
    private CharSequence newText = ""; //新文本，真正显示的文本
    private boolean inProcess = false; //旧文本是否已经处理为新文本
    private static List<Character> punctuation = new ArrayList<Character>(); //标点符号

    //标点符号用于在textview右侧多出空间时，将空间加到标点符号的后面
    static {
        punctuation.clear();
        punctuation.add(',');
        punctuation.add('.');
        punctuation.add('?');
        punctuation.add('!');
        punctuation.add(';');
        punctuation.add(')');
        punctuation.add(']');
        punctuation.add('}');
        punctuation.add('，');
        punctuation.add('。');
        punctuation.add('？');
        punctuation.add('！');
        punctuation.add('；');
        punctuation.add('）');
        punctuation.add('}');
    }

    public CBAlignTextView(Context context) {
        super(context);
    }

    public CBAlignTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    /**
     * 监听文本复制，对于复制的文本进行空格剔除
     *
     * @param id 操作id(复制，全部选择等)
     * @return 是否操作成功
     */
    @Override
    public boolean onTextContextMenuItem(int id) {
        if (id == android.R.id.copy) {

            if (isFocused()) {
                final int selStart = getSelectionStart();
                final int selEnd = getSelectionEnd();

                int min = Math.max(0, Math.min(selStart, selEnd));
                int max = Math.max(0, Math.max(selStart, selEnd));

                //利用反射获取选择的文本信息，同时关闭操作框
                try {
                    Class cls = getClass().getSuperclass();
                    Method getSelectTextMethod = cls.getDeclaredMethod("getTransformedText", new
                            Class[]{int.class, int.class});
                    getSelectTextMethod.setAccessible(true);
                    CharSequence selectedText = (CharSequence) getSelectTextMethod.invoke(this,
                            min, max);
                    Method closeMenuMethod = cls.getDeclaredMethod("stopSelectionActionMode");
                    closeMenuMethod.setAccessible(true);
                    closeMenuMethod.invoke(this);
                    copy(selectedText.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        } else {
            return super.onTextContextMenuItem(id);
        }
    }


    /**
     * 复制文本到剪切板，去除添加字符
     *
     * @param text 文本
     */
    private void copy(String text) {
        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context
                .CLIPBOARD_SERVICE);
        int start = newText.toString().indexOf(text);
        int end = start + text.length();
        StringBuilder sb = new StringBuilder(text);
        for (int i = addCharPosition.size() - 1; i >= 0; i--) {
            int position = addCharPosition.get(i);
            if (position < end && position >= start) {
                sb.deleteCharAt(position - start);
            }
        }
        android.content.ClipData clip = android.content.ClipData.newPlainText(null, sb.toString());
        clipboard.setPrimaryClip(clip);
    }


    /**
     * 处理多行文本
     *
     * @param paint 画笔
     * @param text  文本
     * @param width 最大宽度
     * @return 处理后的文本
     */
    private String processText(Paint paint, String text, int width) {
        if (text == null || text.length() == 0) {
            return "";
        }
        String[] lines = text.split("\\n");
        StringBuilder newText = new StringBuilder();
        for (String line : lines) {
            newText.append('\n');
            newText.append(processLine(paint, line, width - getPaddingLeft() - getPaddingRight()));
        }
        newText.deleteCharAt(0);
        return newText.toString();
    }


    /**
     * 处理单行文本
     *
     * @param paint 画笔
     * @param text  文本
     * @param width 最大宽度
     * @return 处理后的文本
     */
    private String processLine(Paint paint, String text, int width) {
        if (text == null || text.length() == 0) {
            return "";
        }
        StringBuilder old = new StringBuilder(text);
        int startPosition = 0; // 起始位置

        float chineseWidth = paint.measureText("中");
        float spaceWidth = paint.measureText(SPACE + "");

        //最大可容纳的汉字，每一次从此位置向后推进计算
        int maxChineseCount = (int) (width / chineseWidth);

        //减少一个汉字宽度，保证每一行前后都有一个空格
        maxChineseCount--;

        addCharPosition.clear();

        for (int i = maxChineseCount; i < old.length(); i++) {
            if (paint.measureText(old.substring(startPosition, i + 1)) > width - spaceWidth) {
                //右侧多余空隙宽度
                float gap = (width - spaceWidth - paint.measureText(old.substring(startPosition,
                        i)));

                List<Integer> positions = new ArrayList<Integer>();
                for (int j = startPosition; j < i; j++) {
                    char ch = old.charAt(j);
                    if (punctuation.contains(ch)) {
                        positions.add(j + 1);
                    }
                }

                //空隙最多可以使用几个空格替换
                int number = (int) (gap / spaceWidth);

                //多增加的空格数量
                int use = 0;

                if (positions.size() > 0) {
                    for (int k = 0; k < positions.size() && number > 0; k++) {
                        int times = number / (positions.size() - k);
                        int position = positions.get(k / positions.size());
                        for (int m = 0; m < times; m++) {
                            old.insert(position + m, SPACE);
                            addCharPosition.add(position + m);
                            use++;
                            number--;
                        }
                    }
                }

                //指针移动，将段尾添加空格进行分行处理
                i = i + use;
                old.insert(i, SPACE);
                addCharPosition.add(i);

                startPosition = i + 1;
                i = startPosition + maxChineseCount;
            }
        }

        return old.toString();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!inProcess) {
            oldText = text;
        }
        super.setText(text, type);
    }

    /**
     * 获取真正的text
     *
     * @return 返回text
     */
    public CharSequence getRealText() {
        return oldText;
    }


    /**
     * TextView在绘制的时候会经过measure,layout,draw阶段，在measure阶段TextView的
     * 宽度是不一定能获取到的，可能获取的是0，而在layout与draw阶段都可以获取到,windowFocus
     * 在draw阶段之后，所以在此进行文本的转化，当然也可以在layout等阶段进行
     *
     * @param hasWindowFocus 是否获取窗口焦点
     */
    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (!inProcess && oldText != null && oldText.length() != 0) {
            newText = processText(getPaint(), oldText.toString(), getWidth());
            setText(newText);
            int spaceWidth = (int) (getPaint().measureText(SPACE + ""));
            setPadding(getPaddingLeft() + spaceWidth, getPaddingTop(), getPaddingRight(),
                    getPaddingBottom());
            inProcess = true;
        }
        super.onWindowFocusChanged(hasWindowFocus);
    }
}