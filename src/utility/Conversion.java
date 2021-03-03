package utility;


public class Conversion {
    //引数をreplaceメソッドで変換する
    public static String conversionText(String text) {
        String conversion = text.replace("<", "&lt;").replace(">", "&gt;").replace("\r\n", "<br>").replace("'", "&#39;").replace("\"", "&quot;").replace(",", "&#44;");

        return conversion;
    }
}