package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable{

	private String no;
	private String userno;
	private String buildno;
	private String title;
	private String description;
	private String date;
	private String pname;
	private String productno;
	private String userid;
	private ArrayList<?> list;
	private String type;
	private String price;

	public Post() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBuildno() {
        return buildno;
    }

    public void setBuildno(String buildno) {
        this.buildno = buildno;
    }

	public String getTitle() {
	        return title;
	}

	public void setTitle(String title) {
	        this.title = title;
	}
	public String getDescription() {
			return description;
	}


	public void setDescription(String description) {
			this.description = description;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
			this.date = date;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
			this.pname = pname;
	}

	public String getProductno() {
		return productno;
	}

	public void setProductno(String productno) {
			this.productno = productno;
	}

	public ArrayList<?> getList() {
		return list;
	}

	public void setList(ArrayList<?> list) {
		this.list = list;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
