package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.IngredientsDTO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class IngredientsDAO {

	private static JDBCUtil jdbcUtil = new JDBCUtil();
	

	//기존재 재료 검사
	public static boolean existingIngredient(String ing) throws SQLException {
		String sql = "SELECT count(*) FROM ingredients WHERE ingredientName=?";      
		jdbcUtil.setSqlAndParameters(sql, new Object[] {ing});	// JDBCUtil에 query문과 매개 변수 설정

		try {
			ResultSet rs = jdbcUtil.executeQuery();		// query 실행
			if (rs.next()) {
				int count = rs.getInt(1);
				return (count == 1 ? true : false);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource 반환
		}
		return false;
	}
	
	public static int findIngredientID(String ingreName) throws SQLException{
		String query = "select ingredientID " + 
				"from ingredients " + 
				"where ingredientName LIKE ? ";
		jdbcUtil.setSqlAndParameters(query, new Object[] {"%"+ingreName+"%"});
		System.out.println(ingreName);
		int result = 0;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			if(rs.next()) {
				result = rs.getInt("ingredientID");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	// 재료 추가
	public static int addIngredient(String ingreName) throws SQLException{
		//private static 
		String query = "INSERT INTO ingredients(ingredientID, ingredientName) VALUES(in_seq.nextval, ?)";
		jdbcUtil.setSqlAndParameters(query, new Object[] {ingreName});
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		return 0;
	}
}

