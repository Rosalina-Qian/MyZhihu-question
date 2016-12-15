/**
 * 
 */
package com.mq.adapter;

import java.util.List;

import com.example.mydemo.R;
import com.mq.entities.Comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author mq
 *
 */
public class CommentAdapter extends BaseAdapter{

	private List<Comment> list;
	
	@SuppressWarnings("unused")
	private Context context;
	
	private LayoutInflater inflater;
	
	public CommentAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	public CommentAdapter(Context context, List<Comment> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	
	public void setComment(List<Comment> comment){
		this.list = comment;
	}

	//获取Comment的总条数
	@Override
	public int getCount() {
		return list.size();
	}

	//获取对应位置的Comment对象
	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	//获取位置
	@Override
	public long getItemId(int position) {
		return position;
	}

	//获取视图信息
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		Holder holder = null;
		
		if(convertView == null){
		
			convertView = inflater.inflate(R.layout.item_comment, null);
			
			holder = new Holder(convertView);
			
			convertView.setTag(holder);
		
		} else{
		
			holder = (Holder) convertView.getTag();
		}
		
		Comment comment = list.get(position);
		
		holder.objectid.setText(comment.getObjectId().toString());
		
		holder.commentContent.setText(comment.getCommentContent());
		
		holder.commentdate.setText(comment.getCommentTime());
		
		return convertView;
	}
	
	//初始化item_comment组件
	class Holder{
		
		private TextView objectid, commentContent, commentdate;
		
		public  Holder(View v){
			
			objectid = (TextView) v.findViewById(R.id.objectId);
			
			commentContent = (TextView) v.findViewById(R.id.commentContent);
			
			commentdate = (TextView) v.findViewById(R.id.date_comment);
		}
	}

}
