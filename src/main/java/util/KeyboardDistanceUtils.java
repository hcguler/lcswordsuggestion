package util;

import com.sun.deploy.util.ArrayUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeyboardDistanceUtils {
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

  public static int CalculateCloseRangeCharacter(Character c,Map<Character, List<Character>> keyboardCloseRangeCharMap){
    keyboardCloseRangeCharMap.get(c);

    return 0;
  }
}
