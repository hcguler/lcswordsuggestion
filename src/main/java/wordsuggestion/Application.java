package wordsuggestion;

import common.ApplicationConstants;
import util.DataPreProcessingUtils;
import util.KeyboardDistanceUtils;
import util.LcsUtils;
import util.MapUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    static JFrame jFrame = new JFrame();
    static JButton calculateButton = new JButton("Calculate");
    static JLabel inputLabel = new JLabel("Input: ");
    static JLabel resultLabel = new JLabel("Result: ");
    static JTextField inputField = new JTextField();
    static JTextArea resultField = new JTextArea();
    static String inputText;
    static Map<Integer, List<String>> mapStringUsingLength;

    static Map<String, Integer> resultMap;

    public static void main(String[] args) {
        //todo arayüz ve loglama düzenlenecek
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
        Map<String, Double> stringDoubleMap = KeyboardDistanceUtils.calculateCloseRangeCharacter(inputText, reverseSortedSelectedSizeMap);
        Map<String, Double> sortedMap = MapUtils.getSortedMap(stringDoubleMap);
        for (String str : sortedMap.keySet()) {
            stringBuilder.append(str)
                    .append(" : ")
                    .append(sortedMap.get(str))
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    private static void fillSortedResultMap(int length) {
        resultMap = new HashMap<>();
        calculateLcsPutInMapString(length);
        calculateLcsPutInMapString(length - 1);
        calculateLcsPutInMapString(length + 1);
        LcsUtils.calculateEditDistance(inputText,resultMap);
    }


    private static void calculateLcsPutInMapString(int length) {
        List<String> stringList = mapStringUsingLength.get(length);
        if (stringList != null && stringList.size() > 0) {
            for (String str : stringList) {
                int lcsValue = LcsUtils.calculateLcsDynamic(inputText, str);
                resultMap.put(str, lcsValue);
                if (resultMap.keySet().size() > ApplicationConstants.RESULT_SIZE) {
                    resultMap = MapUtils.getReverseSortedSelectedSizeMap(resultMap);
                }
            }
        }
//         her karakter için lcs sonucu yazdırma.
//        resultField.setText(getResultStringSet());
//        resultField.update(resultField.getGraphics());
        System.out.println(length + " character calculation completed....");
    }

    private static void initializeComponentPosition() {
        calculateButton.setBounds(360, 10, 100, 40);
        inputLabel.setBounds(10, 10, 100, 40);
        resultLabel.setBounds(10, 60, 100, 40);
        inputField.setBounds(100, 10, 250, 40);
        resultField.setBounds(100, 60, 250, 300);//x axis, y axis, width, height
        resultField.setEditable(false);

        final JScrollPane scrollableResultTextArea = new JScrollPane(resultField);
        scrollableResultTextArea.setBounds(100, 60, 250, 300);
        jFrame.add(inputLabel);
        jFrame.add(inputField);
        jFrame.add(calculateButton);
        jFrame.add(resultLabel);
        jFrame.add(scrollableResultTextArea);
        jFrame.setSize(480, 410);// width and  height
        jFrame.setLayout(null);
        jFrame.setVisible(true);
    }

}
