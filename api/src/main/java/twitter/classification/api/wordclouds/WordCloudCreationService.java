package twitter.classification.api.wordclouds;

import java.awt.Color;
import java.awt.Dimension;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import com.kennycason.kumo.CollisionMode;
import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.font.scale.SqrtFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;

public class WordCloudCreationService {

  private FrequencyAnalyzer frequencyAnalyzer;
  private Dimension dimension;
  private WordCloud wordCloud;

  public WordCloudCreationService() {

    dimension = new Dimension(100, 100);
    wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
    wordCloud.setPadding(1);
    wordCloud.setBackground(new CircleBackground(50));
    wordCloud.setColorPalette(new ColorPalette(new Color(0x86F177), new Color(0x69F534), new Color(0x40AAF1), new Color(0x40C5F1), new Color(0x40D3F1), new Color(0xFFFFFF)));
    wordCloud.setFontScalar(new SqrtFontScalar(10, 25));
  }

  /**
   * Returns a base64 string that can be rendered as an image in the frontend for a list of tweets
   *
   * @param tweets
   * @return
   * @throws IOException
   */
  public String base64String(List<String> tweets) throws IOException {

    frequencyAnalyzer = new FrequencyAnalyzer();
    List<WordFrequency> wordFrequencies = frequencyAnalyzer.load(tweets);
    wordCloud.build(wordFrequencies);

    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    ImageIO.write(wordCloud.getBufferedImage(), "png", Base64.getEncoder().wrap(byteArrayOutputStream));
    return byteArrayOutputStream.toString(StandardCharsets.ISO_8859_1.name());
  }
}
