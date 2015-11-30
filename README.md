# AlignTextView
字体对齐的textview
  
![截图](./screenshot-small.png)
  
## 系统要求
Android 4.0以上

## 快速使用

**build.gradle加入dependencies**

    compile 'me.codeboy.android:align-text-view:2.1.0'

**CBAlignTextView (新的版本，支持选择复制，排版效果也比较的好)**

    <me.codeboy.android.aligntextview.CBAlignTextView
            android:id="@+id/cbAlignTv"
            android:textIsSelectable="true"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>


如果需要支持android默认的选择复制，请在xml中加入以下代码:

    android:textIsSelectable="true"



**AlignTextView (旧的版本，不支持选择复制，但是排版效果更好)**

     <me.codeboy.android.aligntextview.AlignTextView
            android:id="@+id/alignTv"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"/>

## 相关方法

#### 1.CBAlignTextView

CBAlignTextView中增加了以下方法获取TextView的文本内容，请不要使用getText()获取

    getRealText()
   
由于Android L(5.0)之后对中文的版本进行了变化，造成不能由中文标点作为行首，所以为了能够使CBAlignTextView看起来更加工整，建议将中文符号用英文符号替换(默认不转换)，可以通过以下三种方式转化

- 使用转化函数转化标点符号:

    CBAlignTextViewUtil.replacePunctuation(String text)
   
   
- 在设置CBAlignTextView文本前(setText),调用以下方法:
 
    setPunctuationConvert(boolean convert)
    

- 可以直接在xml布局中进行设置

        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:cb="http://schemas.android.com/apk/res-auto"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical">
              <me.codeboy.android.aligntextview.CBAlignTextView
                    android:id="@+id/cbAlignTextView"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    cb:punctuationConvert="true"
                    android:textIsSelectable="true"
                    android:textSize="14dsp"/>    
        </LinearLayout>


#### 2.AlignTextView
AlignTextView是旧的版本，不支持选择复制，但是可以自定义最后一行的对齐方式

	setAlign(Align align)
	
设置每一段最后一行对齐方式，默认居左对齐  ，同时也可以在xml注释中设置对其方式:

	
        <LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
              xmlns:cb="http://schemas.android.com/apk/res-auto"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical">
              <me.codeboy.android.aligntextview.AlignTextView
                    android:id="@+id/alignTextView"
                    android:layout_width="match_parent"
                    android:layout_height="wrap_content"
                    cb:align="center"
                    android:textSize="14dsp"/>    
        </LinearLayout>

  
  
## 使用说明
1.  强烈建议使用`CBAlignTextView`而不是`AlignTextView`。
2.  `AlignTextView`与`CBAlignTextView`在对齐的时候不会对英文单词等进行考虑，它们都是以字符(character)为基础的,不是词(word)。
3.  使用CBAlignTextView时建议进行中文标点的转换。 
4.  demo项目位与app下，可以单独提取出me.codeboy.android.aligntextview.AlignTextView和me.codeboy.android.aligntextview.CBAlignTextView使用。

## 更新历史

###2.1.0
1. 修正`CBAlignTextView`的显示等bug
2. 加入xml注解，可以指定`AlignTextView`最后一行得到对齐方式等
3. 修正`CBAlignTextView`选择复制后浮层关闭在Android M(6.0)中失效问题


###2.0.3
1. 优化`AlignTextView`的性能(计算每行的字数)
2. 修正`AlignTextView`的padding问题(注意:项目使用中获取的paddingBottom可能与自己设置的不同,若需要代码设置paddingBottom，请在getPaddingBottom的基础上进行添加或减少)

###2.0.2
1. 整合项目，加入仓库，直接引用使用即可

###2.0
1. 加入`CBAlignTextView`,支持原生TextView的选择复制。
2. 修改包名(域名更换了，欢迎访问<http://codeboy.me>)

###1.1
1. 修正由于对齐造成AlignTextView行数减少，从而下方留出空白。