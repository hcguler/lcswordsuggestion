package util;

import common.ApplicationConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyboardDistanceUtils {
  public static final int EDIT_DISTANCE_1_CHAR_INCORRECT_VALUE=2;

  public static Map<Character, List<Character>> getQkeyboardCloseRangeCharacterMap(){
    Map<Character, List<Character>> qKeyboardCloseRangeCharacterMap=new HashMap<>();
    qKeyboardCloseRangeCharacterMap.put('q', getStringToCharArrayList("asw21"));
    qKeyboardCloseRangeCharacterMap.put('w', getStringToCharArrayList("1qasde32"));
    qKeyboardCloseRangeCharacterMap.put('e', getStringToCharArrayList("2wsdfr43"));
    qKeyboardCloseRangeCharacterMap.put('r', getStringToCharArrayList("3edfgt54"));
    qKeyboardCloseRangeCharacterMap.put('t', getStringToCharArrayList("4rfghy65"));
    qKeyboardCloseRangeCharacterMap.put('y', getStringToCharArrayList("5tghju76"));
    qKeyboardCloseRangeCharacterMap.put('u', getStringToCharArrayList("6yhjkı87"));
    qKeyboardCloseRangeCharacterMap.put('ı', getStringToCharArrayList("7ujklo98"));
    qKeyboardCloseRangeCharacterMap.put('o', getStringToCharArrayList("8ıklşp09"));
    qKeyboardCloseRangeCharacterMap.put('p', getStringToCharArrayList("9olşiğ*0"));
    qKeyboardCloseRangeCharacterMap.put('ğ', getStringToCharArrayList("0pşi,ü-*"));
    qKeyboardCloseRangeCharacterMap.put('ü', getStringToCharArrayList("*ği,-"));
    qKeyboardCloseRangeCharacterMap.put('a', getStringToCharArrayList("qwsxz<"));
    qKeyboardCloseRangeCharacterMap.put('s', getStringToCharArrayList("qazxcdew"));
    qKeyboardCloseRangeCharacterMap.put('d', getStringToCharArrayList("wsxcvfre"));
    qKeyboardCloseRangeCharacterMap.put('f', getStringToCharArrayList("edcvbgtr"));
    qKeyboardCloseRangeCharacterMap.put('g', getStringToCharArrayList("rfvbnhyt"));
    qKeyboardCloseRangeCharacterMap.put('h', getStringToCharArrayList("tgbnmjuy"));
    qKeyboardCloseRangeCharacterMap.put('j', getStringToCharArrayList("yhnmökıu"));
    qKeyboardCloseRangeCharacterMap.put('k', getStringToCharArrayList("ujmöçloı"));
    qKeyboardCloseRangeCharacterMap.put('l', getStringToCharArrayList("ıköç.şpo"));
    qKeyboardCloseRangeCharacterMap.put('ş', getStringToCharArrayList("olç.iğp"));
    qKeyboardCloseRangeCharacterMap.put('i', getStringToCharArrayList("pş.,üğ"));
    qKeyboardCloseRangeCharacterMap.put('z', getStringToCharArrayList("asx<"));
    qKeyboardCloseRangeCharacterMap.put('x', getStringToCharArrayList("zasdc"));
    qKeyboardCloseRangeCharacterMap.put('c', getStringToCharArrayList("xsdfv"));
    qKeyboardCloseRangeCharacterMap.put('v', getStringToCharArrayList("cdfgb"));
    qKeyboardCloseRangeCharacterMap.put('b', getStringToCharArrayList("vfghn"));
    qKeyboardCloseRangeCharacterMap.put('n', getStringToCharArrayList("bghjm"));
    qKeyboardCloseRangeCharacterMap.put('m', getStringToCharArrayList("nhjkö"));
    qKeyboardCloseRangeCharacterMap.put('ö', getStringToCharArrayList("mjklç"));
    qKeyboardCloseRangeCharacterMap.put('ç', getStringToCharArrayList("öklş."));
    return qKeyboardCloseRangeCharacterMap;
  }
  private static List<Character> getStringToCharArrayList(String s){
    return s.chars().mapToObj((i) -> Character.valueOf((char)i)).collect(Collectors.toList());
  }

  public static Map<String,Double> calculateCloseRangeCharacter(String inputText, Map<String,Integer> resultMap){
    Map<String,Double> closeRangeResultMap = new HashMap<>();
    for (String s : resultMap.keySet()) {
      int editDistance = resultMap.get(s);
      if (s.length()==inputText.length()){
        if (editDistance==EDIT_DISTANCE_1_CHAR_INCORRECT_VALUE){
          //1 karakter farklı olduğu için klawye yakınlık kontrolü yapılır varsa uzaklık düşürülür.
          if (checkCloseRangeCharakter(inputText,s)){
            closeRangeResultMap.put(s, (double) editDistance- ApplicationConstants.DISTANCE_1_CLOSERANGECHARUSING_WEIGHT);
          }else{
            //klawye yakın karakter olma durumu mevcut değil değer aynen basılır.
            closeRangeResultMap.put(s, (double) editDistance);
          }
        }else{
          //uzaklık büyük olduğu için klawye yakınlığı veya fazla karakter basımı ele alınmadı.
          closeRangeResultMap.put(s, (double) editDistance);
        }
      }else{
        if (editDistance==1){
          //fazladan veya eksik bir karakter basmış olabilir.
          closeRangeResultMap.put(s, (double) editDistance- ApplicationConstants.DISTANCE_1_DIFFIRENT_LENGTH_WEIGHT);
        }else{
          //uzaklık büyük olduğu için klawye yakınlığı veya fazla karakter basımı ele alınmadı.
          closeRangeResultMap.put(s, (double) editDistance);
        }
      }
    }
    return closeRangeResultMap;
  }

  //girilen metindeki farklı karakterin klawyede yakın olduğu karakter listesi getirilir.
  //yakın karakter listesinde 1 yakınlıklı kelimenin farklı karakteri varsa aslında bu kelimeyi girmek istemiş olabilir.
  private static boolean checkCloseRangeCharakter(String inputText, String s) {
    Map<Character, List<Character>> qkeyboardCloseRangeCharacterMap = getQkeyboardCloseRangeCharacterMap();
    char[] inputTextCharArray = inputText.toCharArray();
    char[] sCharArray = s.toCharArray();
    int numberOfDifferenctCharUsingIndex=0;
    boolean closeRangeCharExist=false;
    for (int i = 0; i < inputText.length(); i++) {
      if (inputTextCharArray[i]!=sCharArray[i]){
        List<Character> characters = qkeyboardCloseRangeCharacterMap.get(inputTextCharArray[i]);
        numberOfDifferenctCharUsingIndex++;
        if (characters.contains(sCharArray[i])){
          closeRangeCharExist=true;
        }
      }
    }
    if (numberOfDifferenctCharUsingIndex==1 && closeRangeCharExist){
      return true;
    }
    return false;
  }
}
