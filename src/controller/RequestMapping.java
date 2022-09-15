package controller;

import java.util.HashMap;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.bookmark.AddBookmarkController;
import controller.bookmark.DeleteBookmarkController;
import controller.member.DeleteMemberController;
import controller.member.JoinController;
import controller.member.LoginController;
import controller.member.LogoutController;
import controller.member.UpdateMemberController;
import controller.member.UpdateMemberFormController;
import controller.member.ViewMemberController;
import controller.menu.AllMenuController;
import controller.menu.FindMenuController;
import controller.mypage.MyPageController;
import controller.recipe.AddRecipeController;
import controller.recipe.AllRecipeController;
import controller.recipe.DeleteRecipeController;
import controller.recipe.FindByIngredientsController;
import controller.recipe.FindByMenuController;
import controller.recipe.UpdateRecipeController;
import controller.recipe.ViewRecipeController;
import controller.review.AddReviewController;
import controller.review.DeleteReviewController;
import controller.review.FindByWriterIDController;
import controller.review.SearchController;
import controller.review.UpdateReviewController;
import controller.review.UpdateReviewFormController;
import controller.mypage.MyBookmarkListController;
import controller.mypage.MyRecipeListController;
import controller.mypage.MyReviewListController;

public class RequestMapping {
    private static final Logger logger = LoggerFactory.getLogger(DispatcherServlet.class);
    
    private Map<String, Controller> mappings = new HashMap<String, Controller>();

    public void initMapping() {

    	mappings.put("/", new ForwardController("index.jsp"));
        mappings.put("/member/login/form", new ForwardController("/member/loginForm.jsp"));
        mappings.put("/member/login", new LoginController());
        mappings.put("/member/logout", new LogoutController());
        mappings.put("/member/view", new ViewMemberController());
        mappings.put("/member/join/form", new ForwardController("/member/joinForm.jsp"));
        mappings.put("/member/join", new JoinController());
        mappings.put("/member/update/form", new UpdateMemberFormController());
        mappings.put("/member/update", new UpdateMemberController());
        mappings.put("/member/main/form", new ForwardController("/member/main.jsp"));
        mappings.put("/member/main", new ForwardController("/member/FirstMain.jsp"));
        mappings.put("/member/delete", new DeleteMemberController());
        mappings.put("/member/detail/from", new ForwardController("/member/memberdetail.jsp"));
        
        mappings.put("/mypage/home", new MyPageController());
        mappings.put("/mypage/myReview", new MyReviewListController());
        mappings.put("/mypage/myRecipe", new MyRecipeListController());
        mappings.put("/mypage/myBookmark", new MyBookmarkListController());
        
        mappings.put("/bookmark/addBookmark", new AddBookmarkController());
        mappings.put("/bookmark/deleteBookmark", new DeleteBookmarkController());
        mappings.put("/mypage/home", new MyPageController());
        
        mappings.put("/menu/allMenu", new AllMenuController());
        mappings.put("/menu/findMenu", new FindMenuController());
        mappings.put("/menu/findMenu/form", new ForwardController("/menu/findMenuForm.jsp"));

        mappings.put("/recipe/allRecipe", new AllRecipeController());
        mappings.put("/recipe/addRecipe/form", new ForwardController("/recipe/addRecipeForm.jsp"));
        mappings.put("/recipe/addRecipe", new AddRecipeController());
        mappings.put("/recipe/view", new ViewRecipeController());
        mappings.put("/recipe/delete", new DeleteRecipeController());
        mappings.put("/recipe/updateRecipe", new UpdateRecipeController());
        mappings.put("/recipe/findByMenu", new FindByMenuController());
        mappings.put("/recipe/findByIngredients", new FindByIngredientsController());

        mappings.put("/review/create/form", new ForwardController("/review/addForm.jsp"));
        mappings.put("/review/create", new AddReviewController());
        mappings.put("/review/delete", new DeleteReviewController());
        mappings.put("/review/update", new UpdateReviewController());
        mappings.put("/review/update/form", new UpdateReviewFormController());
        mappings.put("/review/search", new SearchController());
        mappings.put("/review/search/form", new ForwardController("/review/searchForm.jsp"));
        
        
        mappings.put("/mypage/home", new MyPageController());
        
        mappings.put("/bookmark/addBookmark", new AddBookmarkController());
        mappings.put("/bookmark/deleteBookmark", new DeleteBookmarkController());
        mappings.put("/mypage/home", new MyPageController());
        
        
        logger.info("Initialized Request Mapping!");
    }

        public Controller findController(String uri) {	
        return mappings.get(uri);
    }
}
