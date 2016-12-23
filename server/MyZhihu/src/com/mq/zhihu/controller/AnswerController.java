package com.mq.zhihu.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.mq.zhihu.entity.Answer;
import com.mq.zhihu.entity.Comment;
import com.mq.zhihu.entity.Favorite;
import com.mq.zhihu.entity.Focus;
import com.mq.zhihu.entity.Mycollection;
import com.mq.zhihu.entity.Thanks;
import com.mq.zhihu.entity.Upvote;
import com.mq.zhihu.dao.AnswerDAO;
import com.mq.zhihu.dao.CommentDAO;
import com.mq.zhihu.dao.FavoriteDAO;
import com.mq.zhihu.dao.FocusDAO;
import com.mq.zhihu.dao.MycollectionDAO;
import com.mq.zhihu.dao.QuestionDAO;
import com.mq.zhihu.dao.ThanksDAO;
import com.mq.zhihu.dao.UpvoteDAO;
import com.mq.zhihu.dao.UpvoteNumDAO;
import com.mq.zhihu.dao.UserInfoDAO;
import com.mq.zhihu.entity.Userinfo;
import com.mq.zhihu.json.JsonTools;

@Controller
@SessionAttributes("user")
public class AnswerController {
	
	@Autowired
	private QuestionDAO questionDao;
	
	@Autowired
	private AnswerDAO answerDao;
	
	@Autowired
	private CommentDAO commentDao;
	
	@Autowired
	private FocusDAO focusDao;
	
	@Autowired
	private FavoriteDAO favoriteDao;
	
	@Autowired
	private MycollectionDAO mycollectionDao;
	
	@Autowired
	private UserInfoDAO userinfoDao;
	
	@Autowired
	private ThanksDAO thanksDao;
	
	@Autowired
	private UpvoteNumDAO upvoteNumDao;
	
	@Autowired
	private UpvoteDAO upvoteDao;
	
	//获取系统当前时间
	Timestamp date = new Timestamp(System.currentTimeMillis());
	
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//将Timestamp类型的时间转换成字符串类型
	String str_date = df.format(date);
	
	
	/**
	 * 踩
	 * @param oid
	 * @param uid
	 * @return
	 */
	@RequestMapping("/cai")
	@ResponseBody
	public String addCai(@RequestParam("objectId") Integer oid,
			@RequestParam("userId") Integer uid){
		
		System.out.println("uid"+uid+"oid"+oid);
		Upvote zan = new Upvote();
		zan.setCategory(0);//踩
		zan.setObjectId(oid);
		zan.setUserId(uid);
		upvoteDao.addCai(zan);
		
		String jsonString = JsonTools.createJsonString("zan", upvoteDao.queryAll());
		return jsonString;
	
	}
	
	/**
	 * 点赞
	 * @param oid
	 * @param uid
	 * @return
	 */
	@RequestMapping("/zan")
	@ResponseBody
	public String addZan(@RequestParam("objectId") Integer oid,
			@RequestParam("userId") Integer uid){
		
		System.out.println("uid"+uid+"oid"+oid);
		Upvote zan = new Upvote();
		zan.setCategory(1);//点赞
		zan.setObjectId(oid);
		zan.setUserId(uid);
		upvoteDao.addZan(zan);
		
		String jsonString = JsonTools.createJsonString("zan", upvoteDao.queryAll());
		return jsonString;
	
	}
	/**
	 * 表示感谢
	 * @param oid
	 * @param uid
	 * @param qid
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("/ExpressThanks")
	@ResponseBody
	public String expressThanks(@RequestParam("objectId") Integer oid,
			@RequestParam("userId") Integer uid, @RequestParam("questionId") Integer qid) throws SQLException{
		
		System.out.println("qid:"+qid+"uid"+uid+"oid"+oid);
		Thanks thanks = new Thanks();
		thanks.setCategory(1);
		thanks.setObjectId(oid);
		thanks.setUserId(uid);
			
		thanksDao.addThanks(thanks);
		
		String jsonString = JsonTools.createJsonString("ExpressThanks", thanksDao.queryAll());
		return jsonString;
	
	}
	/**
	 * 用户详情
	 * @return
	 */
	@RequestMapping("/personalDetail/{userId}")
	public String personalDetail(@PathVariable("userId") Integer uid, Map<String, Object> map){
		
		//通过用户id查找用户
		map.put("personal", userinfoDao.queryById(uid));
		
		//通过用户id查找该用户所有的回答
		map.put("myanswers", answerDao.queryByUserId(uid));
		
		//通过用户id查找该用户所有的收藏
		map.put("mycollections", favoriteDao.queryByUserId(uid));
		
		return "personalDetail";
	}
	
	/**
	 * 添加关注:0代表问题，1代表回答，2代表文章，3代表话题，4代表收藏夹，5代表用户
	 * @return
	 */
	@RequestMapping("/addFocus")
	public String addFocus(@RequestParam("userId") Integer uid, @RequestParam("objectId") Integer oid, 
					@RequestParam("category") Integer category){
		
		Focus focus = new Focus();
		
		focus.setCategory(category);
		focus.setUserId(uid);
		focus.setObjectId(oid);
		
		focusDao.addFocus(focus);
		
		return "question";
	}
	
	/**
	 * 添加收藏夹
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/addFavortie")
	public String addFavorite(@RequestParam("userId") Integer uid, 
			@RequestParam("questionId") Integer qid, @RequestParam("objectId") Integer oid,
			@RequestParam("category") Integer category, @RequestParam("favoriteName") String favoriteName, 
			@RequestParam("favoriteDescription") String favoriteDescription, 
			Map<String, Object> map) throws UnsupportedEncodingException{
		
		System.out.println("userId:" + uid + " questionId:" + qid + "objectId:" + oid);
		
		//将输入框中的内容编码格式转换成utf-8格式
		String favoritename = new String(favoriteName.getBytes("iso-8859-1"),"utf-8");
		
		String favoritedescription = new String(favoriteDescription.getBytes("iso-8859-1"),"utf-8");
		
		System.out.println("favoritename:--"+favoritename+" \nfavoritedescription:"+favoritedescription);
		
		//添加收藏夹
		Favorite favorite = new Favorite();
		
		favorite.setUserId(uid);
		favorite.setFavoriteName(favoritename);
		favorite.setFavoriteDescription(favoritedescription);
		favorite.setFavoriteDate(str_date);
		
		favoriteDao.addFavorite(favorite);
		
		map.put("favorites", favoriteDao.queryAll());
		
		//添加到收藏
		Mycollection collection = new Mycollection();
		
		collection.setCategory(category);
		collection.setObjectId(oid);
		collection.setCollectionDate(str_date);
		
		mycollectionDao.addMycollection(collection);
		
		return "favorite";
	}
	
	
	/**
	 * 评论他人回答
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/replyComment", method=RequestMethod.POST)
	public String replyComment( @RequestParam("questionId") Integer qid, @RequestParam("userId") Integer uid,
			@RequestParam("objectId") Integer oid,@RequestParam("category") Integer category, 
			@RequestParam("commentContent") String commentContent) throws UnsupportedEncodingException{
		
		System.out.println(" questionId:" + qid + " userId:" + uid + " objectId:" + oid);
		
		//将输入框中的内容编码格式转换成utf-8格式
		String commentcontent = new String(commentContent.getBytes("iso-8859-1"),"utf-8");
		
		Comment comment = new Comment();
		
		comment.setCategory(category);
		comment.setObjectId(oid);
		comment.setUserId(uid);
		comment.setCommentContent(commentcontent);
		comment.setCommentTime(str_date);
		
		commentDao.addComment(comment);
		
		return "redirect:/showcomment/" + qid;
	}
	
	/**
	 * 添加回答
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/myanswer", method=RequestMethod.POST)
	public String myAnswer(@RequestParam("userName") String userName,
					@RequestParam("questionId") Integer qid, @RequestParam("userId") Integer uid,
					@RequestParam("answerContent") String answerContent) throws UnsupportedEncodingException{
		
		//将输入框中的内容编码格式转换成utf-8格式
		String answercontent = new String(answerContent.getBytes("iso-8859-1"),"utf-8");
		
		System.out.println("username: " + userName + " questionId: " + qid + " userId: " + uid);
		System.out.println(answercontent);
		
		Answer answer = new Answer();
		
		answer.setQuestionId(qid);
		answer.setUserId(uid);
		answer.setAnswerContent(answercontent);
		answer.setAnswerDate(str_date);
		
		answerDao.addAnswer(answer);
		
		return "redirect:/showcomment/" + qid;
	}
	
	/**
	 * 通过问题id查询该问题下所有的回答评论
	 * @return
	 */
	@RequestMapping(value="/showcomment/{questionId}", method=RequestMethod.GET)
	public String questiondetail(@PathVariable("questionId") Integer id, Map<String, Object> map){
		
		System.out.println("questionId:" + id);
		
		map.put("question", questionDao.queryById(id));
		
		map.put("answers", answerDao.queryAll());
		map.put("upNum", upvoteNumDao.queryAll());
		
		map.put("comments", commentDao.queryAll());
		map.put("otherComments", commentDao.queryOthers());
		return "comment";
	}
	
	/**
	 * 查询所有问题
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(value="/main", method=RequestMethod.POST)
	public String question(Model model, Userinfo user, HttpServletRequest request, Map<String, Object> map) throws UnsupportedEncodingException{
		
		//获取HttpSession对象
		HttpSession session = request.getSession();  
		
		//向session中添加数据user对象
	    session.setAttribute("user",user); 
		
	    model.addAttribute("user", user);
		
	    map.put("questions", questionDao.queryAll());
	    map.put("answers", answerDao.queryAll());
	    map.put("focus", focusDao.queryAll());
	    String username = new String(user.getUserName().getBytes("iso-8859-1"),"utf-8");
	    System.out.println("usename:" + username);
		
	    return "question";
	}
}
