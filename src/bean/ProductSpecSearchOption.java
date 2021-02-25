package bean;

import java.io.Serializable;

public class ProductSpecSearchOption implements Serializable {
    public static int INT = 0;
    public static int STRING = 1;
    public static int DOUBLE = 2;

    private String optionName;
    private int valueType;
    private boolean isCanRange;
    private String[] value;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public boolean isCanRange() {
        return isCanRange;
    }

    public void setCanRange(boolean canRange) {
        isCanRange = canRange;
    }

    public String[] getValue() {
        return value;
    }

    public void setValue(String[] value) {
        this.value = value;
    }
}
