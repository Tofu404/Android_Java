package create.by.showme.beans;

public class BannerDataBean {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public BannerDataBean(int imageResource) {
        id = imageResource;
    }
}
