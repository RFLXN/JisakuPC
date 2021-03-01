package utility;


public class Conversion{
//引数をreplaceメソッドで変換する
public static String conversionText(String text){
  String conversion=text.replace("\r\n","<br>");

  return conversion;
}
}