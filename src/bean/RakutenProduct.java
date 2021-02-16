package bean;

public class RakutenProduct {
    private String genreId;
    private String productName;
    private String productUrlPc;
    private String productUrlMobile;
    private int minPrice;
    private int maxPrice;
    private int averagePrice;
    private String productId;
    private String mediumImageUrl;
    private String smallImageUrl;

    public RakutenProduct() {
    }

    public String getMediumImageUrl() {
        return mediumImageUrl;
    }

    public void setMediumImageUrl(String mediumImageUrl) {
        this.mediumImageUrl = mediumImageUrl;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductUrlPc() {
        return productUrlPc;
    }

    public void setProductUrlPc(String productUrlPc) {
        this.productUrlPc = productUrlPc;
    }

    public String getProductUrlMobile() {
        return productUrlMobile;
    }

    public void setProductUrlMobile(String productUrlMobile) {
        this.productUrlMobile = productUrlMobile;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(int averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
