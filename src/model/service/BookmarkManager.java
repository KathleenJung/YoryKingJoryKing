package model.service;
import model.dao.BookmarkDAO;


public class BookmarkManager {
	
	private static BookmarkManager bookmarkManager = new BookmarkManager();
	private BookmarkDAO bookmarkDAO;
	
	private BookmarkManager() {
		try {
			bookmarkDAO = new BookmarkDAO();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static BookmarkManager getInstance() {return bookmarkManager; }
	
	public int addBookmark(int recipeID, String memberID) throws Exception {
		return bookmarkDAO.addBookmark(recipeID, memberID);
		
	}

	public int deleteBookmark(int recipeID, String memberID) {
		return bookmarkDAO.deleteBookmark(recipeID, memberID);
	}
}
