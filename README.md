# DragBottom
适用于查看大图或者任意界面的下拉拖拽返回控件<br>
共享元素适用于5.0+<br>

![](https://github.com/vvinner/DragBottom/blob/master/case.gif)<br>

新增非共享元素模式下的拖拽平滑返回效果,参考IOS腾讯新闻-图片新闻的下拉拖拽返回效果
-------

![](https://github.com/vvinner/DragBottom/blob/master/case2.gif)<br>


新增API
```Java
//拖拽时是否进行缩放操作，默认TRUE
DragFrameLayout.setDragScale(false);
```
1、XML
-------
```Xml
<porster.dragbottom.drag.DragFrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
              android:layout_width="match_parent"
              android:layout_height="match_parent"
              android:orientation="vertical">
              //Your xml
</porster.dragbottom.drag.DragFrameLayout>
```
2、Theme  
-------
```Xml
<item name="android:windowIsTranslucent">true</item>
```
