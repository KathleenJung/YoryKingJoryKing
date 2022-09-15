package model.dao;
//join�ؼ� menuID�� �ƴ϶� �޴������γ����� �ؾ���

import java.util.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.RecipeDTO;
import model.ReviewDTO;
import model.dao.IngredientsDAO;
import model.dao.MenuDAO;
import model.dao.BookmarkDAO;

public class RecipeDAO {
	private JDBCUtil jdbcUtil= new JDBCUtil();
	private JDBCUtil jdbcUtil2= new JDBCUtil();
	
	
	public int addRecipe(RecipeDTO dto) throws Exception{ //�������߰�
		
		
		Object[] param;
		String query;		
		int result = 0;
		int addRecipeID = 0;
		
		int add_menu_result = -1;
		if(MenuDAO.existingMenu(dto.getMenuName()) == false){ // ���� �޴�
			//���� �޴���� menuT�� ���� �߰��ϰ�
			add_menu_result = MenuDAO.addMenu(dto.getMenuName(), dto.getMenuNationality()); //1 or 0
		}
		//�ִ��Ÿ� �ٷ�, �����Ÿ� �߰��ߴ� �޴� ID ã�ƿͼ� recipe���̺� ����
		//�� ��������� ����,,,,��
		
		
		
		//�߰��� ���������� �ưų�			���� �ִ� �޴����
		if(add_menu_result > 0 || MenuDAO.existingMenu(dto.getMenuName())) {
			//MenuID�� ã�ƿͼ�
			int findMenuID = MenuDAO.findMenuID(dto.getMenuName()); //-1 or ID

			//recipeT�� ����
			query = "insert into recipe(recipeID, hard, recontent, time, writerID, menuID, video, recipeTitle) "
					+ " values (recipe_seq.nextval, ?, ?, ?, ?, ?, ?, ?)";
			param = new Object[] {dto.getHard(), dto.getRecontent(), dto.getTime(), dto.getWriterID(), findMenuID, dto.getVideo(), dto.getRecipeTitle() };
			
			jdbcUtil.setSqlAndParameters(query, param);
				
			try {				
				result += jdbcUtil.executeUpdate();	// recipe���̺� insert �� ����
				
				if(result > 0)  //������ �� �ƴٸ�
					addRecipeID = findRecipeID(dto);
				
					
			} catch (Exception ex) {
				jdbcUtil.rollback();
				ex.printStackTrace();
			}finally {		
				jdbcUtil.commit();
				jdbcUtil.close();
				
			}
		}
		
		/* * ������ ���̺� �߰� �Ϸ� * */
		
		String ingr;
		
		Iterator<String> iterator = dto.getIngredientList().iterator();
		while (iterator.hasNext()){ //���� ��� �̸��� �˻�
			
			ingr = iterator.next();
			
			int add_in_result = 0;
			if(IngredientsDAO.existingIngredient(ingr) == false) { //���� ���� �ϴ� �߰���
				add_in_result = IngredientsDAO.addIngredient(ingr);
			}
			
			
			int findIngrID;
			//�߰��� ���������� �ưų�			���� �ִ� �����
			if(add_in_result > 0 || IngredientsDAO.existingIngredient(ingr)) {
				//ingredientID�� ã�ƿͼ�
				findIngrID = IngredientsDAO.findIngredientID(ingr); //-1 or ID
				//reci_ingredientsT�� ����
				query = "insert into reci_ingredients(recipeID, ingredientID) values (?, ?) ";
				param = new Object[] {addRecipeID, findIngrID}; ////��� ID ã�ƾ� ��
								//�Ʊ� �����ص� �߰��� ������ ID
				jdbcUtil.setSqlAndParameters(query, param);
					
				try {				
					result += jdbcUtil.executeUpdate();	// reci_ingredient���̺� insert �� ����
				} catch (Exception ex) {
					jdbcUtil.rollback();
					ex.printStackTrace();
				}finally {		
					jdbcUtil.commit();
					jdbcUtil.close();
					
				}
			}
			
			
		}	
		return result;
		
	}
	public int findRecipeID(RecipeDTO dto) {
		String sql = "select MAX(recipeID) from recipe";
		jdbcUtil.setSqlAndParameters(sql, null);
		int result = -1;
		try {
			ResultSet rs = jdbcUtil.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("MAX(recipeID)");
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		} /*
			 * finally { jdbcUtil.close(); // resource ��ȯ }
			 */
		return result;
	}
	
	public int deleteRecipe(int recipeID, String memberID) throws SQLException{ //�����ǻ���
		
		String query2 = "delete from reci_ingredients where recipeID=?";
		jdbcUtil2.setSqlAndParameters(query2, new Object[] {recipeID});
		
		String query = "DELETE FROM recipe WHERE recipeID=?";	
		jdbcUtil.setSqlAndParameters(query, new Object[] {recipeID});
		
		
		int result = 0;
		try {			
			BookmarkDAO.deleteBookmark(recipeID, memberID);
			jdbcUtil2.executeUpdate();	// delete �� ����
			result = jdbcUtil.executeUpdate();
		} catch (Exception ex) {
			jdbcUtil2.rollback();
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil2.commit();
			jdbcUtil2.close();	// resource ��ȯ
			jdbcUtil.commit();
			jdbcUtil.close();

		}		
		return result;
	} 
	
	
	public int updateRecipe(RecipeDTO dto) throws SQLException{ //�����Ǽ���
		//1. reci_ingedients �ϴ� ���� ����
		String query = "delete from reci_ingredients where recipeID=?";
		jdbcUtil.setSqlAndParameters(query, new Object[] {dto.getRecipeID()});
		try {
			jdbcUtil.executeUpdate();
		}catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();

		}		
		
		//2. �ٽ� �߰���
		String ingr;
		Object[] param;
		Iterator<String> iterator = dto.getIngredientList().iterator();
		while (iterator.hasNext()){ //���� ��� �̸��� �˻�
			
			ingr = iterator.next();
			
			int add_in_result = 0;
			if(IngredientsDAO.existingIngredient(ingr) == false) { //���� ���� �ϴ� �߰���
				add_in_result = IngredientsDAO.addIngredient(ingr);
			}
			
			
			int findIngrID;
			//�߰��� ���������� �ưų�			���� �ִ� �����
			if(add_in_result > 0 || IngredientsDAO.existingIngredient(ingr)) {
				//ingredientID�� ã�ƿͼ�
				System.out.println("ingr= " + ingr);

				findIngrID = IngredientsDAO.findIngredientID(ingr); //-1 or ID
				System.out.println("findIngrID= " + findIngrID);
				
				//reci_ingredientsT�� ����
				query = "insert into reci_ingredients(recipeID, ingredientID) values (?, ?) ";
				param = new Object[] {dto.getRecipeID(), findIngrID}; ////��� ID ã�ƾ� ��
								//�Ʊ� �����ص� �߰��� ������ ID
				jdbcUtil.setSqlAndParameters(query, param);
					
				try {				
					int updated = jdbcUtil.executeUpdate();	// reci_ingredient���̺� insert �� ����
				} catch (Exception ex) {
					jdbcUtil.rollback();
					ex.printStackTrace();
				}finally {		
					jdbcUtil.commit();
					jdbcUtil.close();
					
				}
			}
			
			
		}	
		
		
		int add_menu_result = -1;
		
		if(MenuDAO.existingMenu(dto.getMenuName()) == false){ // ���� �޴�
			//���� �޴���� menuT�� ���� �߰��ϰ�
			add_menu_result = MenuDAO.addMenu(dto.getMenuName(), dto.getMenuNationality()); //1 or 0
		}
		
		int findMenuID = 0;
		//�߰��� ���������� �ưų�			���� �ִ� �޴����
		if(add_menu_result > 0 || MenuDAO.existingMenu(dto.getMenuName())) {
			//MenuID�� ã�ƿͼ�
			findMenuID = MenuDAO.findMenuID(dto.getMenuName()); //-1 or ID

		}
		
		String sql = "UPDATE recipe "
				+ "SET recontent=?, hard=?, time=?, video=?, recipeTitle=?, menuID=? "
				+ "WHERE recipeID=?";
		param = new Object[] {dto.getRecontent(), dto.getHard(), dto.getTime(), dto.getVideo(), dto.getRecipeTitle(), findMenuID, dto.getRecipeID() };				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil�� update���� �Ű� ���� ����
		int result = 0;
		try {				
			result = jdbcUtil.executeUpdate();	// update �� ����
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource ��ȯ
		}		
		return result;
	}///������ --> �׳� ó������ �ְ� �̤�         
	
	//������Detail ��ȸ
	public RecipeDTO viewRecipe(int recipeID) throws SQLException{
		String sql1 = "SELECT r.recipeTitle, m.menuName, r.hard, r.recontent, r.time, r.writerID, r.video, r.recipeID, m.nationality "
      		   + "FROM recipe r, menu m "
      		   + "where r.menuID = m.menuID and r.recipeID=?";
		jdbcUtil.setSqlAndParameters(sql1, new Object[] {recipeID});		// JDBCUtil�� query�� ����
		
		String sql2 = "select i.ingredientName from reci_ingredients ri, ingredients i "
				+ "where i.ingredientID = ri.ingredientID and ri.recipeID=?";
		jdbcUtil2.setSqlAndParameters(sql2, new Object[] {recipeID});		
		
		try {
			ResultSet rs1 = jdbcUtil.executeQuery();			// query ����	
			ResultSet rs2 = jdbcUtil2.executeQuery();
			
			ArrayList<String> ins = new ArrayList<String>();
			while(rs2.next()) {
				ins.add(rs2.getString("ingredientName"));
			}
	
			int bookmarkNum = 0;
			RecipeDTO result = null;	
			if (rs1.next()) {
				bookmarkNum = BookmarkDAO.getBookmarkNum(recipeID);
				result = new RecipeDTO(			
						rs1.getInt("recipeID"),
						rs1.getString("menuName"),
						rs1.getString("writerID"),
						rs1.getString("recontent"),
						rs1.getString("hard"),
						rs1.getString("time"),
						rs1.getString("video"),
						rs1.getString("recipeTitle"),
						ins,
						rs1.getString("nationality"),
						bookmarkNum);
			}		
			
			return result;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	
	//��ü ������ ����Ʈ ���
	public List<RecipeDTO> allRecipeList() throws SQLException {
        String sql = "SELECT r.recipeTitle, m.menuName, r.hard, r.time, r.writerID, r.video, r.recipeID, m.nationality "
        		   + "FROM recipe r, menu m "
        		   + "where r.menuID = m.menuID "
        		   + "ORDER BY r.recipeID";
		jdbcUtil.setSqlAndParameters(sql, null);		// JDBCUtil�� query�� ����
					
		int bookmarkNum = 0;
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<RecipeDTO> recipeList = new ArrayList<RecipeDTO>();	
			while (rs.next()) {
				bookmarkNum = BookmarkDAO.getBookmarkNum(rs.getInt("recipeID"));
				RecipeDTO dto = new RecipeDTO(			
						rs.getInt("recipeID"),
						rs.getString("menuName"),
						rs.getString("writerID"),
						null,
						rs.getString("hard"),
						rs.getString("time"),
						rs.getString("video"),
						rs.getString("recipeTitle"), 
						null,
						rs.getString("nationality"),
						bookmarkNum); 
				recipeList.add(dto);				
			}		
			return recipeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();		// resource ��ȯ
		}
		return null;
	}
	public List<RecipeDTO> findRecipeByWriter(String writerID) { //�ۼ��ڷ� ������ �˻�
		String sql ="SELECT r.recipeTitle, m.menuName, r.hard, r.time, r.writerID, r.video, r.recipeID, m.nationality "
     		   + "FROM recipe r, menu m "
     		   + "where r.menuID = m.menuID and r.writerID = ?"
     		   + "ORDER BY recipeID";
		jdbcUtil.setSqlAndParameters(sql, new Object[] {writerID});		// JDBCUtil�� query�� ����
					
		int bookmarkNum = 0;
		try {
			ResultSet rs = jdbcUtil.executeQuery();			// query ����			
			List<RecipeDTO> writerRecipeList = new ArrayList<RecipeDTO>();	// User���� ����Ʈ ����
			while (rs.next()) {
				bookmarkNum = BookmarkDAO.getBookmarkNum(rs.getInt("recipeID"));
				RecipeDTO dto = new RecipeDTO(		
					rs.getInt("recipeID"),
					rs.getNString("menuName"),
					rs.getString("writerID"),
					null,
					rs.getString("hard"),
					rs.getString("time"),
					rs.getString("video"),
					rs.getString("recipeTitle"), 
					null,
					rs.getString("nationality"),
					bookmarkNum);	//////////////////////////����
				writerRecipeList.add(dto);			
			}		
			return writerRecipeList;					
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			jdbcUtil.close();	
		}
		return null;
	}
	
	public List<RecipeDTO> findRecipeByMenu(int menuID) { //�޴��� ������ �˻�
			String sql ="SELECT r.recipeTitle, m.menuName, r.hard, r.recontent, r.time, r.writerID, r.video, r.recipeID, m.nationality "
     		   + "FROM recipe r, menu m "
     		   + "where r.menuID = m.menuID and r.menuID=? "
     		   + "ORDER BY recipeID";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {menuID});		// JDBCUtil�� query�� ����
						
			int bookmarkNum = 0;
			try {
				ResultSet rs = jdbcUtil.executeQuery();			// query ����			
				List<RecipeDTO> menuRecipeList = new ArrayList<RecipeDTO>();	// User���� ����Ʈ ����
				while (rs.next()) {
					bookmarkNum = BookmarkDAO.getBookmarkNum(rs.getInt("recipeID"));
					RecipeDTO dto = new RecipeDTO(			
							rs.getInt("recipeID"),
							rs.getNString("menuName"),
							rs.getString("writerID"),
							null,
							rs.getString("hard"),
							rs.getString("time"),
							rs.getString("video"),
							rs.getString("recipeTitle"), 
							null,
							rs.getString("nationality"),
							bookmarkNum);	
					menuRecipeList.add(dto);				
				}		
				return menuRecipeList;					
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			return null;
	}
	
	
	public List<RecipeDTO> findRecipeByIngredients(String[] ingredientList) { //���� �����ǰ˻�(���� ����Ʈ ����)
		HashSet<Integer> recipeIDs = new HashSet<Integer>();
		
		String query = "select ir.recipeID "
				+ "from ingredients i, reci_ingredients ir "
				+ "where i.ingredientID = ir.ingredientID and i.ingredientName LIKE ? "
				+ "ORDER BY ir.recipeID";
		for(String ing : ingredientList) {
			System.out.println(ing);
			jdbcUtil.setSqlAndParameters(query, new Object[] {"%" + ing + "%"} );
			try {
				ResultSet rs = jdbcUtil.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt("recipeID"));
					recipeIDs.add(rs.getInt("recipeID"));
				}
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		System.out.println(recipeIDs);
		jdbcUtil.close();
		
		//recipeIDs ==> ������ ���� �����ǵ��� id��

		List<RecipeDTO> ingreRecipeList = new ArrayList<RecipeDTO>();
		query = "SELECT r.recipeTitle, m.menuName, r.hard, r.recontent, r.time, r.writerID, r.video, r.recipeID, m.nationality "
	     		   + "FROM recipe r, menu m "
	     		   + "where r.menuID = m.menuID and r.recipeID=? "
	     		   + "ORDER BY recipeID";
		
		int bookmarkNum = 0;
		for(int id : recipeIDs) {
			jdbcUtil.setSqlAndParameters(query, new Object[] {id});		
			try {
				ResultSet rs = jdbcUtil.executeQuery();			// query ����			
				while (rs.next()) {
					bookmarkNum = BookmarkDAO.getBookmarkNum(rs.getInt("recipeID"));
					RecipeDTO dto = new RecipeDTO(			
							rs.getInt("recipeID"),
							rs.getNString("menuName"),
							rs.getString("writerID"),
							null,
							rs.getString("hard"),
							rs.getString("time"),
							rs.getString("video"),
							rs.getString("recipeTitle"), 
							null,
							rs.getString("nationality"),
							bookmarkNum);
					ingreRecipeList.add(dto);			
				}	
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				
			}
			
		}
		jdbcUtil.close();// resource ��ȯ
		return ingreRecipeList;
		
		
	}
	
	
	
	
	public List<RecipeDTO> findRecipeByHard(String hard) { //���̵��� ������ �˻�
			String sql ="SELECT r.recipeTitle, m.menuName, r.hard, r.recontent, r.time, r.writerID, r.video, r.recipeID, m.nationality "
	     		   + "FROM recipe r, menu m "
	     		   + "where r.menuID = m.menuID and r.hard=? "
	     		   + "ORDER BY recipeID";
			jdbcUtil.setSqlAndParameters(sql, new Object[] {hard});		
				
			int bookmarkNum=0;
			try {
				ResultSet rs = jdbcUtil.executeQuery();						
				List<RecipeDTO> hardRecipeList = new ArrayList<RecipeDTO>();	
				while (rs.next()) {
					bookmarkNum = BookmarkDAO.getBookmarkNum(rs.getInt("recipeID"));
					RecipeDTO dto = new RecipeDTO(	
							rs.getInt("recipeID"),
							rs.getString("menuName"),
							rs.getString("writerID"),
							rs.getString("reci_content"),
							rs.getString("hard"),
							rs.getString("time"),
							rs.getString("video"),
							rs.getString("recipeTitle"), 
							null,
							rs.getString("nationality"),
							bookmarkNum);	
					hardRecipeList.add(dto);			
				}
				return hardRecipeList;					
				
			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				jdbcUtil.close();		// resource ��ȯ
			}
			return null;
	}
	
	
}
