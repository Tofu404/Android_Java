package create.by.showme.beans;

public class MeItemBean {
    public static final int HEADER_TYPE =0;
    public static final int NORMAL_TYPE =1;
    public static final int DRIVER_TYPE =2;

    private int imageResourceId;
    private String itemText = "";
    private int itemType = 2;

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public MeItemBean(int imageResourceId, String itemText, int itemType) {
        this.imageResourceId = imageResourceId;
        this.itemText = itemText;
        this.itemType = itemType;
    }
}
