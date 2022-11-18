package activities;

public class groceryInfo {
	static int id=0;
	private String name;
	private String gtin13;
	private String sku;
	private float price;
	private String condition;
	private String availabilty;
	private String currency;
	private String brand;
	private String breadcrumbs;
	private String description;
	private String image;
	private Float avg_rating;
	private int reviews_count;
	private String url;
	private String _id;
	private String crrawled_at;
	private String source;

	
	public groceryInfo() {
		super();
		this.name = "";
		this.gtin13 = "";
		this.sku = "";
		this.price = 0;
		this.condition = "";
		this.availabilty = "";
		this.currency = "";
		this.brand = "";
		this.breadcrumbs = "";
		this.description = "";
		this.image = "";
		this.avg_rating = 0.0f;
		this.reviews_count =0;
		this.url ="";
		this._id = "";
		this.crrawled_at = "";
		this.source = "";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGtin13() {
		return gtin13;
	}
	public void setGtin13(String gtin13) {
		this.gtin13 = gtin13;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getAvailabilty() {
		return availabilty;
	}
	public void setAvailabilty(String availabilty) {
		this.availabilty = availabilty;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getBreadcrumbs() {
		return breadcrumbs;
	}
	public void setBreadcrumbs(String breadcrumbs) {
		this.breadcrumbs = breadcrumbs;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Float getAvg_rating() {
		return avg_rating;
	}
	public void setAvg_rating(Float avg_rating) {
		this.avg_rating = avg_rating;
	}
	public int getReviews_count() {
		return reviews_count;
	}
	public void setReviews_count(int reviews_count) {
		this.reviews_count = reviews_count;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return _id;
	}
	public void setId(String id) {
		this._id = _id;
	}
	
	public String getCrrawled_at() {
		return crrawled_at;
	}
	public void setCrrawled_at(String crrawled_at) {
		this.crrawled_at = crrawled_at;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
