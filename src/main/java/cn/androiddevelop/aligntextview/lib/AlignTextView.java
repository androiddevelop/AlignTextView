package cn.androiddevelop.aligntextview.lib;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 两端对齐的textview，可以设置最后一行靠左，靠右，居中对齐
 * 
 * @author YD
 *
 */
public class AlignTextView extends TextView {
	private float textHeight; // 单行文字高度
	private int width; // textview宽度
	private List<String> lines = new ArrayList<String>(); // 分割后的行
	private List<Integer> tailLines = new ArrayList<Integer>(); // 尾行
	private Align align = Align.ALIGN_LEFT; // 默认最后一行左对齐

	// 尾行对齐方式
	public enum Align {
		ALIGN_LEFT, ALIGN_CENTER, ALIGN_RIGHT; // 居中，居左，居右,针对段落最后一行
	}

	public AlignTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		TextPaint paint = getPaint();
		paint.setColor(getCurrentTextColor());
		paint.drawableState = getDrawableState();
		width = getMeasuredWidth();
		String text = getText().toString();

		Paint.FontMetrics fm = paint.getFontMetrics();
		// 计算行高
		Layout layout = getLayout();

		// layout.getLayout()在4.4.3出现NullPointerException
		if (layout == null) {
			return;
		}

		textHeight = fm.descent - fm.ascent;

		textHeight = textHeight * layout.getSpacingMultiplier()
				+ layout.getSpacingAdd();

		float firstHeight = getTextSize();

		int gravity = getGravity();
		if ((gravity & 0x1000) == 0) { // 是否垂直居中
			firstHeight = firstHeight + (textHeight - firstHeight) / 2;
		}

		int paddingLeft = getPaddingLeft();
		int paddingRight = getPaddingRight();
		width = width - paddingLeft - paddingRight;

		lines.clear();
		tailLines.clear();

		// 文本含有换行符时，分割单独处理
		String[] items = text.split("\\n");
		for (String item : items) {
			calc(paint, item);
		}

		for (int i = 0; i < lines.size(); i++) {
			float drawY = i * textHeight + firstHeight;
			String line = lines.get(i);
			// 绘画起始x坐标
			float drawSpacingX = paddingLeft;
			float gap = (width - paint.measureText(line));
			float interval = gap / (line.length() - 1);

			// 绘制最后一行
			if (tailLines.contains(i)) {
				interval = 0;
				if (align == Align.ALIGN_CENTER)
					drawSpacingX += gap / 2;
				else if (align == Align.ALIGN_RIGHT)
					drawSpacingX += gap;
			}

			for (int j = 0; j < line.length(); j++) {
				float drawX = paint.measureText(line.substring(0, j))
						+ interval * j;
				canvas.drawText(line.substring(j, j + 1), drawX + drawSpacingX,
						drawY, paint);
			}
		}
	}

	/**
	 * 设置尾行对齐方式
	 * 
	 * @param align
	 */
	public void setAlign(Align align) {
		this.align = align;
		invalidate();
	}

	/**
	 * 计算每行应显示的文本数
	 * 
	 * @param text
	 * @return
	 */
	private void calc(Paint paint, String text) {
		if (text.length() == 0) {
			lines.add("\n");
			return;
		}
		StringBuffer sb = new StringBuffer("");
		int startPosition = 0; // 起始位置
		for (int i = 0; i < text.length(); i++) {
			if (paint.measureText(text.substring(startPosition, i + 1)) > width) {
				startPosition = i;
				lines.add(sb.toString());
				sb = new StringBuffer();
			}
			sb.append(text.charAt(i));
		}
		if (sb.length() > 0)
			lines.add(sb.toString());

		tailLines.add(lines.size() - 1);
	}
}