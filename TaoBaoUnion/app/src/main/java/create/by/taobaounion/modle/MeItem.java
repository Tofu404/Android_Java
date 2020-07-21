package create.by.taobaounion.modle;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MeItem implements MultiItemEntity {

    //item的类型
    public static final int HEADER = 1;
    public static final int NORMAL = 2;
    public static final int PARTITION = 3;

    private Class clazz = null;
    private int picId = 0;
    private int itemType = PARTITION;
    private String itemName = "";

    public MeItem(Class clazz, int picId, int itemType, String itemName) {
        this.clazz = clazz;
        this.picId = picId;
        this.itemType = itemType;
        this.itemName = itemName;
    }

    public MeItem() {
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
