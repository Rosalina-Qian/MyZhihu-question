/**
 * 
 */
package com.mq.adapter;

import java.util.List;

import com.example.mydemo.R;
import com.mq.activity.MyCommentActivity;
import com.mq.entities.Answer;
import com.mq.entities.Comment;
import com.mq.entities.SupportNum;
import com.mq.http.HttpClientThread;
import com.mq.operation.DateTool;
import com.mq.operation.JsonTools;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * @author mq
 *
 */
public class AnswerAdapter extends BaseAdapter{

	private List<Answer> list;

	private Context context;
	
	private LayoutInflater inflater;
	
	public AnswerAdapter(Context context) {
		this.context = context;
		inflater = LayoutInflater.from(context);
	}
	
	public AnswerAdapter(Context context, List<Answer> list) {
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}
	
	public void setAnswer(List<Answer> answer){
		this.list = answer;
	}

	//获取Answer的总条数
	@Override
	public int getCount() {
		return list.size();
	}

	//获取对应位置的Answer对象
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
			convertView = inflater.inflate(R.layout.item_answer, null);			
			holder = new Holder(convertView);			
			convertView.setTag(holder);		
		} else{		
			holder = (Holder) convertView.getTag();
		}
		
		final Answer answer = list.get(position);		
		
		holder.userId.setText(answer.getUserId().toString());		
		holder.answerContent.setText(answer.getAnswerContent());
		
		
		String sPNumString = HttpClientThread.acceptHttpClient("supportNum"); 
		final List<SupportNum> listsPNum= JsonTools.getSupportNum("supportNum", sPNumString);
		
		//返回支持数量
		if(listsPNum != null){
			int count_vote = 0;
			for(int i = 0; i < listsPNum.size(); i++){
				SupportNum spNum = listsPNum.get(i);
				if(spNum.getAnswerId().equals(answer.getAnswerId())){
					count_vote++;
				}
			}if(count_vote != 0){
				holder.agree.setText(""+count_vote+" 赞同 · ");
			} else{
				holder.agree.setText("");
			}
		} else{
			holder.agree.setText("");
		}
	
		
		
		
		//回答显示的时间
		long time = DateTool.getGapTime(answer.getAnswerDate());
		if(time >1 && time < 60){
			holder.date.setText("刚刚");
		} else if((time > 60 ||time == 60 ) && time < 3600){
			holder.date.setText(""+(time/60)+"分钟前");
        } else if((time > 3600 || time == 3600) && time < (24*3600)){
    	    holder.date.setText(""+(time/(60 * 60))+"小时前");
        } else if((time > (24*3600)  || time == (24*3600)) && time < (24*3600*30) ){
    	    holder.date.setText(""+(time/(60 * 60 * 24))+"天前");
        } else if(( (time/(60*60 * 24 * 30)) > 1 || (time/(60*60 * 24 * 30)) == 1) && (time/(60*60 * 24 * 30)) < 12){
        	holder.date.setText(""+(time/(60*60 * 24 * 30)) +"个月前");
        } else if(((time/(60 * 60 * 24 * 30 * 12)) > 1 || (time/(60 * 60 * 24 * 30 * 12)) == 1 )){
        	holder.date.setText(""+(time/(60 * 60 * 24 * 30 * 12)) +"年前");
        }
		
		String commentString = HttpClientThread.acceptHttpClient("allComments");
		List<Comment> listcomment = JsonTools.getComment("allComments", commentString);
		
		//统计该回答的评论数量
		if(listcomment != null){
			int commentCount = 0;
			for(int i = 0; i < listcomment.size(); i++){
				Comment comment = listcomment.get(i);
				if( answer.getAnswerId().equals(comment.getObjectId()) && comment.getCategory().equals(1)){
					commentCount++;
				}
			}
			if(commentCount != 0){
				holder.commentNum.setText(""+commentCount+" 评论 · ");
			}else{
				holder.commentNum.setText("");
			}
			
			//查询对该做出的评论
			holder.commentNum.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					Intent intent = new Intent(context, MyCommentActivity.class);
					intent.putExtra("answerDetail", new Answer(answer.getAnswerId(), answer.getUserId(),
							answer.getQuestionId(), answer.getAnswerContent(), answer.getAnswerDate()));
					
					context.startActivity(intent);
					
				}
			});			
		}
		return convertView;
	}
	
	
	/**
	 * 初始化item_answer组件
	 * @author mq
	 *
	 */
	class Holder{
		
		private TextView userId, answerContent;		
		private TextView agree, commentNum, date;		
		
		public  Holder(View v){			
			userId = (TextView) v.findViewById(R.id.userId);			
			answerContent = (TextView) v.findViewById(R.id.answerContent);
			agree = (TextView) v.findViewById(R.id.agree);
			commentNum = (TextView) v.findViewById(R.id.commentNum);
			date = (TextView) v.findViewById(R.id.date);
		}
	}

	
	
}
