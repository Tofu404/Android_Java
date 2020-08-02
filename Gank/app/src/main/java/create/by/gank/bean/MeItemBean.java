package create.by.gank.bean;

public class MeItemBean {

    public static final int HEADER_TYPE = 0;
    public static final int NORMAL_TYPE = 1;
    public static final int DIVIDE_TYPE = 2;

    private int imageId = 0;
    private String userName = "";
    private String userAccount = "";
    private String item_title = "";
    private int itemType = 0;
    private int groupId = 0;


    public MeItemBean(int imageId, String userName, String userAccount, int itemType) {
        this.imageId = imageId;
        this.userName = userName;
        this.userAccount = userAccount;
        this.itemType = itemType;
    }

    public MeItemBean(int imageId, String item_title, int itemType) {
        this.imageId = imageId;
        this.item_title = item_title;
        this.itemType = itemType;
    }

    public MeItemBean(int itemType) {
        this.itemType = itemType;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getItem_title() {
        return item_title;
    }

    public void setItem_title(String item_title) {
        this.item_title = item_title;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "MeItemBean{" +
                "imageId=" + imageId +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", item_title='" + item_title + '\'' +
                ", itemType=" + itemType +
                ", groupId=" + groupId +
                '}';
    }
}
