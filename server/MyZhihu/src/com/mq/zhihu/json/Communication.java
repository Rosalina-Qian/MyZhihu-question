package com.mq.zhihu.json;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mq.zhihu.dao.AnswerDAO;
import com.mq.zhihu.dao.CommentDAO;
import com.mq.zhihu.dao.FavoriteDAO;
import com.mq.zhihu.dao.FocusDAO;
import com.mq.zhihu.dao.MycollectionDAO;
import com.mq.zhihu.dao.QuestionDAO;
import com.mq.zhihu.dao.SupportNumDAO;
import com.mq.zhihu.dao.SupportorOpposeDAO;
import com.mq.zhihu.dao.ThanksDAO;
import com.mq.zhihu.dao.UserInfoDAO;
import com.mq.zhihu.entity.Answer;
import com.mq.zhihu.entity.Comment;
import com.mq.zhihu.entity.Favorite;
import com.mq.zhihu.entity.Focus;
import com.mq.zhihu.entity.Mycollection;
import com.mq.zhihu.entity.SupportorOppose;
import com.mq.zhihu.entity.Thanks;


@Controller
public class Communication {

	@Autowired
	private QuestionDAO questionDao;
	
	@Autowired
	private CommentDAO commentDao;
	
	@Autowired
	private UserInfoDAO userinfoDao;
	
	@Autowired
	private AnswerDAO answerDao;
	
	@Autowired
	private FocusDAO focusDao;
	
	@Autowired
	private FavoriteDAO favoriteDao;
	
	@Autowired
	private MycollectionDAO mycollectionDao;
	
	@Autowired
	private ThanksDAO thanksDao;
	
	@Autowired
	private SupportNumDAO supportNumDao;
	
	@Autowired
	private SupportorOpposeDAO SupportorOpposeDao;
	
	//获取系统当前时间
	Timestamp date = new Timestamp(System.currentTimeMillis());
		
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	//将Timestamp类型的时间转换成字符串类型
	String str_date = df.format(date);
	
	public Communication() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping("/allQuestions")
	@ResponseBody
	public String allQuestion(){
		String jsonString = JsonTools.createJsonString("allQuestions", questionDao.queryAll());
		return jsonString;
	}
	
	@RequestMapping("/allComments")
	@ResponseBody
	public String allComment(){
		String jsonString = JsonTools.createJsonString("allComments", commentDao.queryAll());
		return jsonString;
	}
	
	@RequestMapping("/allUsers")
	@ResponseBody
	public String allUser(){
		String jsonString = JsonTools.createJsonString("allUsers", userinfoDao.queryAll());
		return jsonString;
	}
	
	@RequestMapping("/allAnswers")
	@ResponseBody
	public String allAnswer(){
		String jsonString = JsonTools.createJsonString("allAnswers", answerDao.queryAll());
		return jsonString;
	}
	
	@RequestMapping("/allFocus")
	@ResponseBody
	public String allFocus(){
		String jsonString = JsonTools.createJsonString("allFocus", focusDao.queryAll());
		return jsonString;
	}
	
	@RequestMapping("/allSupportOrOppose")
	@ResponseBody
	public String allSupportOrOppose(){
		String jsonString = JsonTools.createJsonString("allSupportOrOppose", SupportorOpposeDao.queryAll());
		return jsonString;
	}
	
	@RequestMapping("/allThanks")
	@ResponseBody
	public String allThanks(){
		String jsonString = JsonTools.createJsonString("allThanks", thanksDao.queryAll());
		return jsonString;
	}
	
	@RequestMapping("/supportNum")
	@ResponseBody
	public String supportNum(){
		String jsonString = JsonTools.createJsonString("supportNum", supportNumDao.queryAll());
		return jsonString;
	}
	
	/**
	 * 客户端添加回答
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/return_myanswer")
	@ResponseBody
	public void myAnswer(@RequestParam("username") String userName,
					@RequestParam("questionId") Integer qid, @RequestParam("userId") Integer uid,
					@RequestParam("answerContent") String answerContent) throws UnsupportedEncodingException{
		
		String content = new String(answerContent.getBytes("iso-8859-1"),"utf-8");
		
		System.out.println("username: " + userName + " questionId: " + qid + " userId: " + uid + "\ncontent"+content);
		
		Answer answer = new Answer();
		
		answer.setQuestionId(qid);
		answer.setUserId(uid);
		answer.setAnswerContent(content);
		answer.setAnswerDate(str_date);
		
		answerDao.addAnswer(answer);
	}
	
	/**
	 * 客户端添加关注
	 * 0代表问题，1代表回答，2代表文章，3代表话题，4代表收藏夹，5代表用户
	 * @param qid
	 * @param category
	 * @param uid
	 */
	@RequestMapping("/return_focus")
	@ResponseBody
	public void addFocus(@RequestParam("objectId") Integer oid, @RequestParam("category")
				Integer category, @RequestParam("userId") Integer uid){
		
		System.out.println("objectId: " + oid + " category: " + category + " userId: " + uid );
		
		Focus focus = new Focus();
		
		focus.setCategory(category);
		focus.setUserId(uid);
		focus.setObjectId(oid);
		
		focusDao.addFocus(focus);
		
	}
	
	/**
	 * 客户端对回答做出评论
	 * @param uid
	 * @param oid
	 * @param category
	 * @param commentContent
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/return_replyComment")
	@ResponseBody
	public void myComment(@RequestParam("userId") Integer uid, @RequestParam("objectId") Integer oid,
					@RequestParam("category") Integer category, @RequestParam("commentContent") String commentContent)
					throws UnsupportedEncodingException{
		
		String content = new String(commentContent.getBytes("iso-8859-1"),"utf-8");
		
		System.out.println("oid: " + oid + " category: " + category + " userId: " + uid + "\ncontent"+content);
		
		Comment comment = new Comment();
		
		comment.setCategory(category);//回答
		comment.setObjectId(oid);
		comment.setUserId(uid);
		comment.setCommentContent(content);
		comment.setCommentTime(str_date);
		
		commentDao.addComment(comment);
	}
		
	/**
	 * 客户端添加收藏夹
	 * @param uid
	 * @param oid
	 * @param category
	 * @param favoriteName
	 * @param favoriteDescription
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/return_addCollection")
	@ResponseBody
	public void addFavorite(@RequestParam("userId") Integer uid, @RequestParam("objectId") Integer oid,
			@RequestParam("category") Integer category, @RequestParam("favoritename") String favoriteName, 
			@RequestParam("favoriteDescription") String favoriteDescription) throws UnsupportedEncodingException{
		
		String favoritename = new String(favoriteName.getBytes("iso-8859-1"),"utf-8");
		
		String favoritedescription = new String(favoriteDescription.getBytes("iso-8859-1"),"utf-8");
		
		System.out.println("userId: " + uid + " category: " + category + " objectId: " + oid + " favoritename: " + favoritename
				+ " favoritedescription: " + favoritedescription);
		//添加收藏夹
		Favorite favorite = new Favorite();
				
		favorite.setUserId(uid);
		favorite.setFavoriteName(favoritename);
		favorite.setFavoriteDescription(favoritedescription);
		favorite.setFavoriteDate(str_date);
				
		favoriteDao.addFavorite(favorite);
		
		//添加到收藏
		Mycollection collection = new Mycollection();
				
		collection.setCategory(category);
		collection.setObjectId(oid);
		collection.setCollectionDate(str_date);
				
		mycollectionDao.addMycollection(collection);
	}
	
	/**
	 * 客户端返回感谢
	 * @param oid
	 * @param uid
	 * @param category
	 * @throws SQLException 
	 */
	@RequestMapping("/return_ExpressThanks")
	@ResponseBody
	public void expressThanks(@RequestParam("objectId") Integer oid,
			@RequestParam("userId") Integer uid, @RequestParam("category") Integer category) throws SQLException{
		
		
		System.out.println("category:"+category+"uid:"+uid+"oid:"+oid);
		
		Thanks thanks = new Thanks();
			
		thanks.setCategory(category);
		thanks.setObjectId(oid);
		thanks.setUserId(uid);
			
		thanksDao.addThanks(thanks);
	}
	
//	/**
//	 * 客户端返回取消感谢
//	 * @param oid
//	 * @param uid
//	 */
//	@RequestMapping("/return_cancelThanks")
//	@ResponseBody
//	public void cancelThanks(@RequestParam("objectId") Integer oid,
//			@RequestParam("userId") Integer uid){
//		
//		System.out.println("uid:"+uid+"oid:"+oid);
//		
//		thanksDao.deleteThanks(uid, oid);
//	}
	
	/**
	 * 客户端返回支持
	 * @param uid
	 * @param aid
	 * @param supportOrOppose
	 */
	@RequestMapping("/return_support")
	@ResponseBody
	public void support(@RequestParam("userId") Integer uid, @RequestParam("answerId") Integer aid,
			@RequestParam("supportOrOppose") Integer supportOrOppose){
		
		
		System.out.println("support:"+supportOrOppose+"uid:"+uid+"answerId:"+aid);
		
		SupportorOppose so = new SupportorOppose();
		
		so.setAnswerId(aid);
		so.setUserId(uid);
		so.setSupportOrOppose(supportOrOppose);
		
		SupportorOpposeDao.addSupport(so);
	}
	
	/**
	 * 客户端返回反对
	 * @param uid
	 * @param aid
	 * @param supportOrOppose
	 */
	@RequestMapping("/return_oppose")
	@ResponseBody
	public void oppose(@RequestParam("userId") Integer uid, @RequestParam("answerId") Integer aid,
			@RequestParam("supportOrOppose") Integer supportOrOppose){
		
		
		System.out.println("Oppose:"+supportOrOppose+"uid:"+uid+"answerId:"+aid);
		
		SupportorOppose so = new SupportorOppose();
		
		so.setAnswerId(aid);
		so.setUserId(uid);
		so.setSupportOrOppose(supportOrOppose);
		
		SupportorOpposeDao.addOppose(so);
	}
}
