package bean;

import java.io.Serializable;

public class RakutenItem implements Serializable {
    private String genreId;
    private String itemCode;
    private String shopName;
    private String itemName;
    private int price;
    private String itemUrl;
    private String[] smallImageUrls;
    private String[] mediumImageUrls;

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getItemUrl() {
        return itemUrl;
    }

    public void setItemUrl(String itemUrl) {
        this.itemUrl = itemUrl;
    }

    public String[] getSmallImageUrls() {
        return smallImageUrls;
    }

    public void setSmallImageUrls(String[] smallImageUrls) {
        this.smallImageUrls = smallImageUrls;
    }

    public String[] getMediumImageUrls() {
        return mediumImageUrls;
    }

    public void setMediumImageUrls(String[] mediumImageUrls) {
        this.mediumImageUrls = mediumImageUrls;
    }
}
