package com.mq.zhihu.dao;


import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mq.zhihu.common.JDBCUtil;
import com.mq.zhihu.entity.Favorite;

@Repository
public class FavoriteDAO {

	JDBCUtil util = new JDBCUtil();
	
	/**
	 * 添加收藏
	 * @param f
	 * @return
	 */
	public boolean addFavorite(Favorite f) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("insert into favorite(UserID, FavoriteName, FavoriteDescription, FavoriteDate)" +
				"values(?,?,?,?)", f.getUserId(), f.getFavoriteName(), f.getFavoriteDescription(),
				f.getFavoriteDate());
		if(i > 0 ){
			util.closeAll();
			return true;
		}else{
			util.closeAll();
			return false;
		}
		
	}
	
	/**
	 * 删除收藏
	 * @param FavoriteID
	 * @return
	 */
	public boolean deleteFavorite(int FavoriteID) {
		// TODO Auto-generated method stub
		int i = 0;
		i = util.update("delete from favorite where FavoriteID = ?", FavoriteID);
		if(i > 0){
			util.closeAll();
			return true;
		}else{
			util.closeAll();
		
			return false;}
	}
	
	/**
	 * 查询所有收藏
	 * @return
	 */
	public List<Favorite> queryAll() {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from favorite");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Favorite> list = new ArrayList<Favorite>();
		try {
			while(rs.next()){
				Favorite favorite = new Favorite();
				favorite.setFavoriteId(rs.getInt("FavoriteID"));
				favorite.setUserId(rs.getInt("UserID"));
				favorite.setFavoriteName(rs.getString("FavoriteName"));
				favorite.setFavoriteDescription(rs.getString("FavoriteDescription"));
				favorite.setFavoriteDate(df.format(rs.getTimestamp("FavoriteDate")));
				list.add(favorite);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			util.closeAll();
		}
		return list;
	}
	
	/**
	 * 根据用户id查询用户的所有收藏
	 * @param UserID
	 * @return
	 */
	public List<Favorite> queryByUserId(int UserID) {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		rs = util.query("select * from favorite where UserID=?", UserID);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Favorite> list = new ArrayList<Favorite>();
		try {
			while(rs.next()){
				Favorite favorite = new Favorite();
				favorite.setFavoriteId(rs.getInt("FavoriteID"));
				favorite.setUserId(rs.getInt("UserID"));
				favorite.setFavoriteName(rs.getString("FavoriteName"));
				favorite.setFavoriteDescription(rs.getString("FavoriteDescription"));
				favorite.setFavoriteDate(df.format(rs.getTimestamp("FavoriteDate")));
				list.add(favorite);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally{
			util.closeAll();
		}
		return list;
	}

}
