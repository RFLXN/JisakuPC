package bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ProductSpecInfo implements Serializable {
    private static final Map<String, ProductSpecInfo> infoMap = new HashMap<>();
    public static int INT = 0;
    public static int DOUBLE = 1;
    public static int STRING = 2;
    public static int BOOLEAN = 4;

    static {
        infoMap.put("tdp", new ProductSpecInfo("TDP", INT, "W"));
        infoMap.put("core", new ProductSpecInfo("コア数", INT, ""));
        infoMap.put("thread", new ProductSpecInfo("スレッド数", INT, ""));
        infoMap.put("socket", new ProductSpecInfo("ソケット", STRING));
        infoMap.put("frequency", new ProductSpecInfo("クロック", DOUBLE, "GHz"));
        infoMap.put("gpu", new ProductSpecInfo("GPU", STRING));
        infoMap.put("memorySize", new ProductSpecInfo("メモリーサイズ", INT, "GB"));
        infoMap.put("memoryType", new ProductSpecInfo("メモリータイプ", STRING));
        infoMap.put("ddr", new ProductSpecInfo("DDR タイプ", STRING));
        infoMap.put("dimm", new ProductSpecInfo("DIMM タイプ", STRING));
        infoMap.put("clock", new ProductSpecInfo("クロック", INT, "MHz"));
        infoMap.put("wifi", new ProductSpecInfo("WIFI 対応", BOOLEAN));
        infoMap.put("chipset", new ProductSpecInfo("チップセット", STRING));
        infoMap.put("usbTypeC", new ProductSpecInfo("USB TYPE-C 対応", BOOLEAN));
        infoMap.put("formfactor", new ProductSpecInfo("フォームファクタ", STRING));
        infoMap.put("thunderbolt", new ProductSpecInfo("Thunderbolt 対応", BOOLEAN));
        infoMap.put("size", new ProductSpecInfo("サイズ", STRING));
        infoMap.put("speed", new ProductSpecInfo("回転数", STRING));
        infoMap.put("serial", new ProductSpecInfo("インターフェイス", STRING));
        infoMap.put("volume", new ProductSpecInfo("容量", STRING));
        infoMap.put("flashMemoryType", new ProductSpecInfo("フラッシュメモリータイプ", STRING));
        infoMap.put("factor", new ProductSpecInfo("ファクタ", STRING));
        infoMap.put("80PLUS", new ProductSpecInfo("80PLUS認証", STRING));
        infoMap.put("type", new ProductSpecInfo("タイプ", STRING));
        infoMap.put("airVolume", new ProductSpecInfo("ファン容量", DOUBLE, "CFM"));
        infoMap.put("rpm", new ProductSpecInfo("回転数", INT, ""));
        infoMap.put("noiseLevel", new ProductSpecInfo("ノイズレベル", DOUBLE, "dB"));
        infoMap.put("color", new ProductSpecInfo("色", STRING));
        infoMap.put("date", new ProductSpecInfo("発売日", STRING));
        infoMap.put("plugin", new ProductSpecInfo("プラグイン対応", BOOLEAN));
    }

    private String specName;
    private int valueType;
    private String unit;

    private ProductSpecInfo() {
    }

    private ProductSpecInfo(String specName, int valueType) {
        this.specName = specName;
        this.valueType = valueType;
    }

    public ProductSpecInfo(String specName, int valueType, String unit) {
        this.specName = specName;
        this.valueType = valueType;
        this.unit = unit;
    }

    public static Map<String, ProductSpecInfo> getInfoMap() {
        return infoMap;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
