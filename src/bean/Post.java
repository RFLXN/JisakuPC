package bean;

import java.io.Serializable;

public class Post implements Serializable{

	private String no;
	private String userno;
	private String buildno;
	private String title;
	private String description;
	private String date;

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
}
