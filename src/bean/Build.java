package bean;

import java.io.Serializable;
import java.util.List;

public class Build implements Serializable {
    private String buildNo;
    private String userNo;
    private String buildName;
    private List<Product> products;

    public Build() {
    }

    public String getBuildNo() {
        return buildNo;
    }

    public void setBuildNo(String buildNo) {
        this.buildNo = buildNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getTotalPrice() {
    	int total = 0;

    	for(Product p: products) {
    		int price = Integer.parseInt(p.getPrice());
    		total += price;
    	}
    	return Integer.toString(total);
    }


}
