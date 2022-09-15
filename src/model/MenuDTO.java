package model;

public class MenuDTO {
	private String menuName;
	private int menuID;
	
	public MenuDTO() {}
	
	public MenuDTO(String menuName, int menuID) {
		super();
		this.menuName = menuName;
		this.menuID = menuID;
	}
	public MenuDTO(String menuName) {
		super();
		this.menuName = menuName;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}


	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
}

