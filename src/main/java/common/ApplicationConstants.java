package common;

public interface ApplicationConstants {
    String SOURCE_FILE = "wordset/full.txt.tr";
    String TARGET_DIRECTORY = "wordsetwithlength/";
    String FILE_NAME_SUFFIX = "_length_words.txt";
    String SPACE_SPLIT_REGEX = "\\s+";
    double DISTANCE_1_DIFFIRENT_LENGTH_WEIGHT = 0.5d;
    double DISTANCE_1_CLOSERANGECHARUSING_WEIGHT = 0.8d;
    int RESULT_SIZE = 15;
}
