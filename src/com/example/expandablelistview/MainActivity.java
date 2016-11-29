package com.example.expandablelistview;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ExpandableListView myExpandable;
	String[] type=new String[]{"我的好友","大学同学","亲戚朋友"};
	String[][] info=new String[][]{{"张三","张四","张五"},{"李四","李武"},{"王五","王六","王二","王柳'"}};
	int[] groupImgs=new int[]{R.drawable.a,R.drawable.b,R.drawable.c};
	int[][] imgIds=new int[][]{
			{R.drawable.a,R.drawable.b,R.drawable.c},
			{R.drawable.a,R.drawable.b},
			{R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d}};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myExpandable=(ExpandableListView) this.findViewById(R.id.myExpandable);
	
		//创建一个自定的下拉列表适配器，用于设置内容与显示之间的关系
		ExpandableListAdapter myAdapter=new	BaseExpandableListAdapter(){

			//获取指定组中指定序号的项
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return info[groupPosition][childPosition];
			}

			//获取子项的ID
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;
			}

			//获取子项显示控件
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout layout=new LinearLayout(MainActivity.this);
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layout.setPadding(20, 0, 0, 0);
				ImageView itemImage=new ImageView(MainActivity.this);//创建图片视图
				itemImage.setPadding(20, 0, 0, 0);//设置图片的左边距
				itemImage.setImageResource(imgIds[groupPosition][childPosition]);
				layout.addView(itemImage);
				TextView textView= getTextView();//获取文本显示框
				textView.setText(getChild(groupPosition,childPosition).toString());//设置文本显示框的显示内容
				layout.addView(textView);//在线性布局中添加文本显示框

				return layout;
			}

			//获取指定组的项数
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return info[groupPosition].length;
			}

			//获取自定组对象
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return type[groupPosition];
			}

			//获取组的个数
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return type.length;
			}

			//获取组的ID
			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				
				LinearLayout layout=new LinearLayout(MainActivity.this);//线性布局
				layout.setOrientation(LinearLayout.HORIZONTAL);//设置线性布局方向
				layout.setGravity(Gravity.CENTER_VERTICAL);//设置垂直居中
				ImageView groupImg=new ImageView(MainActivity.this);//创建一个ImageView
				groupImg.setImageResource(groupImgs[groupPosition]);//设置ImageView的图片
				layout.addView(groupImg);//在线性布局中添加图片
				TextView textView= getTextView();//得到一个textView
				textView.setText(getGroup(groupPosition).toString());//设置TextView的显示内容
				layout.addView(textView);//在线性布局中添加textView
				return layout;//返回整个线性布局控件
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}

			//子项是否可以选择
			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}
			
			// 自己定义的一个获取TextView的方法
			public TextView getTextView(){		
				AbsListView.LayoutParams lp=new AbsListView.LayoutParams(
			    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);//设置宽度和高度
				TextView textview=new TextView(MainActivity.this);
				textview.setLayoutParams(lp);
				textview.setGravity(Gravity.CENTER_VERTICAL);//文字水平居中
				textview.setTextSize(20);//设置文字大小为20sp
				textview.setPadding(30, 0, 0, 0);//设置左边距为30pt
				textview.setTextColor(Color.BLACK);//设置文本颜色
				return textview;//获取文本显示框，自定义的			
			}
			
		};
		
		myExpandable.setAdapter(myAdapter);//将适配器与扩展下拉列表关联起来

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
