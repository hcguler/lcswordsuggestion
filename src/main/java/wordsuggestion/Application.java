package wordsuggestion;

import common.ApplicationConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import util.DataPreProcessingUtils;
import util.LcsUtils;
import util.MapUtils;

public class Application {
  static JFrame jFrame = new JFrame();//creating instance of JFrame
  static JButton calculateButton = new JButton("Calculate");
  static JLabel inputLabel = new JLabel("Input: ");
  static JLabel resultLabel = new JLabel("Result: ");
  static JTextField inputField = new JTextField();//creating instance of JButton
  static JTextArea resultField = new JTextArea();//creating instance of JButton
  static String inputText;
  static Map<Integer, List<String>> mapStringUsingLength;

  static Map<String, Integer> resultMap;

  public static void main(String[] args) {
    //todo 100 binden fazla kelime içeren 10,11 gibi karakter sayılı veriler için çok memory gerekiyor düzeltilmeli.
    //todo türkçe karakter sorunu çözülmeli.
    mapStringUsingLength = DataPreProcessingUtils.initializeSizeWithStringMap();
    initializeComponentPosition();
    calculateButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        System.out.println("Input olarak girilen veri: " + inputField.getText());
        inputText = inputField.getText();

        int length = inputField.getText().length();
        fillSortedResultMap(length);

        resultField.setText(getResultStringSet());
      }
    });
  }

  private static String getResultStringSet() {
    Map<String, Integer> reverseSortedSelectedSizeMap = MapUtils.getReverseSortedSelectedSizeMap(resultMap);
    StringBuilder stringBuilder = new StringBuilder("");
    for (String str : reverseSortedSelectedSizeMap.keySet()) {
      stringBuilder.append(str + " : " + reverseSortedSelectedSizeMap.get(str));
      stringBuilder.append("\n");
    }
    return stringBuilder.toString();
  }

  private static void fillSortedResultMap(int length) {
    resultMap = new HashMap<>();
    calculateLcsPutInMapString(length);
    calculateLcsPutInMapString(length - 1);
    calculateLcsPutInMapString(length + 1);
  }


  private static void calculateLcsPutInMapString(int length) {
    List<String> stringList = mapStringUsingLength.get(length);
    for (String str : stringList) {
      int lcsValue = LcsUtils.calculateLcs(inputText, str);
      resultMap.put(str, lcsValue);
      if (resultMap.keySet().size()> ApplicationConstants.RESULT_SIZE){
        resultMap = MapUtils.getReverseSortedSelectedSizeMap(resultMap);
      }
    }

    System.out.println(length + " character calculation completed....");
  }

  private static void initializeComponentPosition() {
    calculateButton.setBounds(210, 10, 100, 40);
    inputLabel.setBounds(10, 10, 100, 40);
    resultLabel.setBounds(10, 60, 100, 40);
    inputField.setBounds(100, 10, 100, 40);
    resultField.setBounds(100, 60, 100, 300);//x axis, y axis, width, height
    resultField.setEditable(false);

    final JScrollPane scrollableResultTextArea = new JScrollPane(resultField);
    scrollableResultTextArea.setBounds(100, 60, 100, 300);
    jFrame.add(inputLabel);
    jFrame.add(inputField);
    jFrame.add(calculateButton);
    jFrame.add(resultLabel);
    jFrame.add(scrollableResultTextArea);
    jFrame.setSize(330, 450);// width and  height
    jFrame.setLayout(null);
    jFrame.setVisible(true);
  }

}
