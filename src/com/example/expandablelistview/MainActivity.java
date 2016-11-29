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
	String[] type=new String[]{"�ҵĺ���","��ѧͬѧ","��������"};
	String[][] info=new String[][]{{"����","����","����"},{"����","����"},{"����","����","����","����'"}};
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
	
		//����һ���Զ��������б�������������������������ʾ֮��Ĺ�ϵ
		ExpandableListAdapter myAdapter=new	BaseExpandableListAdapter(){

			//��ȡָ������ָ����ŵ���
			@Override
			public Object getChild(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return info[groupPosition][childPosition];
			}

			//��ȡ�����ID
			@Override
			public long getChildId(int groupPosition, int childPosition) {
				// TODO Auto-generated method stub
				return childPosition;
			}

			//��ȡ������ʾ�ؼ�
			@Override
			public View getChildView(int groupPosition, int childPosition,
					boolean isLastChild, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				LinearLayout layout=new LinearLayout(MainActivity.this);
				layout.setOrientation(LinearLayout.HORIZONTAL);
				layout.setPadding(20, 0, 0, 0);
				ImageView itemImage=new ImageView(MainActivity.this);//����ͼƬ��ͼ
				itemImage.setPadding(20, 0, 0, 0);//����ͼƬ����߾�
				itemImage.setImageResource(imgIds[groupPosition][childPosition]);
				layout.addView(itemImage);
				TextView textView= getTextView();//��ȡ�ı���ʾ��
				textView.setText(getChild(groupPosition,childPosition).toString());//�����ı���ʾ�����ʾ����
				layout.addView(textView);//�����Բ���������ı���ʾ��

				return layout;
			}

			//��ȡָ���������
			@Override
			public int getChildrenCount(int groupPosition) {
				// TODO Auto-generated method stub
				return info[groupPosition].length;
			}

			//��ȡ�Զ������
			@Override
			public Object getGroup(int groupPosition) {
				// TODO Auto-generated method stub
				return type[groupPosition];
			}

			//��ȡ��ĸ���
			@Override
			public int getGroupCount() {
				// TODO Auto-generated method stub
				return type.length;
			}

			//��ȡ���ID
			@Override
			public long getGroupId(int groupPosition) {
				// TODO Auto-generated method stub
				return groupPosition;
			}

			@Override
			public View getGroupView(int groupPosition, boolean isExpanded,
					View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				
				LinearLayout layout=new LinearLayout(MainActivity.this);//���Բ���
				layout.setOrientation(LinearLayout.HORIZONTAL);//�������Բ��ַ���
				layout.setGravity(Gravity.CENTER_VERTICAL);//���ô�ֱ����
				ImageView groupImg=new ImageView(MainActivity.this);//����һ��ImageView
				groupImg.setImageResource(groupImgs[groupPosition]);//����ImageView��ͼƬ
				layout.addView(groupImg);//�����Բ��������ͼƬ
				TextView textView= getTextView();//�õ�һ��textView
				textView.setText(getGroup(groupPosition).toString());//����TextView����ʾ����
				layout.addView(textView);//�����Բ��������textView
				return layout;//�����������Բ��ֿؼ�
			}

			@Override
			public boolean hasStableIds() {
				// TODO Auto-generated method stub
				return false;
			}

			//�����Ƿ����ѡ��
			@Override
			public boolean isChildSelectable(int groupPosition,
					int childPosition) {
				// TODO Auto-generated method stub
				return true;
			}
			
			// �Լ������һ����ȡTextView�ķ���
			public TextView getTextView(){		
				AbsListView.LayoutParams lp=new AbsListView.LayoutParams(
			    ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);//���ÿ�Ⱥ͸߶�
				TextView textview=new TextView(MainActivity.this);
				textview.setLayoutParams(lp);
				textview.setGravity(Gravity.CENTER_VERTICAL);//����ˮƽ����
				textview.setTextSize(20);//�������ִ�СΪ20sp
				textview.setPadding(30, 0, 0, 0);//������߾�Ϊ30pt
				textview.setTextColor(Color.BLACK);//�����ı���ɫ
				return textview;//��ȡ�ı���ʾ���Զ����			
			}
			
		};
		
		myExpandable.setAdapter(myAdapter);//������������չ�����б��������

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
