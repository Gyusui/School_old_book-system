package com.SOB.bean;

public class BookBean {
	private int id;
	private String bookName;
	
	private String grade;
	private String bookObject;
	private String price;
	private String description;
	private String physical;
	//标识书的状态，是在售，还是用户上传
	private String status;
	//上传书的用户
	private String userId;
	
	private String pictureUrl;
	

	public BookBean() {
		super();
	}

	public BookBean(int id, String bookName, String grade, String bookObject,
			String price, String description, String physical, String status,
			String userId, String pictureUrl) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.grade = grade;
		this.bookObject = bookObject;
		this.price = price;
		this.description = description;
		this.physical = physical;
		this.status = status;
		this.userId = userId;
		this.pictureUrl = pictureUrl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getBookObject() {
		return bookObject;
	}

	public void setBookObject(String bookObject) {
		this.bookObject = bookObject;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPhysical() {
		return physical;
	}

	public void setPhysical(String physical) {
		this.physical = physical;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	

}
