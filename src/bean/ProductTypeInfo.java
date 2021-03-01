package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProductTypeInfo implements Serializable {
    private static final Map<String, ProductTypeInfo> infoMap = new HashMap<>();

    static {
        infoMap.put("cpu", new ProductTypeInfo("CPU"));
        infoMap.put("gpu", new ProductTypeInfo("GPU"));
        infoMap.put("ram", new ProductTypeInfo("メモリー"));
        infoMap.put("storage", new ProductTypeInfo("ストレージ"));
        infoMap.put("case", new ProductTypeInfo("ケース"));
        infoMap.put("power_supply", new ProductTypeInfo("電源"));
        infoMap.put("cpu_cooler", new ProductTypeInfo("CPUクーラー"));
        infoMap.put("case_fan", new ProductTypeInfo("ケースファン"));
        infoMap.put("mother_board", new ProductTypeInfo("マザーボード"));
    }

    private String typeName;

    private ProductTypeInfo(String typeName) {
        this.typeName = typeName;
    }

    private ProductTypeInfo() {
    }

    public static Map<String, ProductTypeInfo> getInfoMap() {
        return infoMap;
    }

    public static String getTranslatedTypeName(String typeName) {
        return infoMap.get(typeName).getTypeName();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
