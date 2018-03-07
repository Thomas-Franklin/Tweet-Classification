package twitter.classification.classifier.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;

public class RandomDataUtil {

  private static final Lorem lorem = LoremIpsum.getInstance();


  public static String randomTweetBody() {

    return lorem.getWords(10, 15);
  }

  public static String randomUsername() {

    return lorem.getName();
  }

  public static List<String> randomHashtagList() {

    List<String> stringList = new ArrayList<>();
    stringList.add(lorem.getWords(1));

    return stringList;
  }

  public static Long randomId() {

    return ((long) ThreadLocalRandom.current().nextInt(1000, 10000 + 1));
  }

  public static String randomClassificationValue() {

    return ThreadLocalRandom.current().nextBoolean() ? "rumour" : "non-rumour";
  }
}
