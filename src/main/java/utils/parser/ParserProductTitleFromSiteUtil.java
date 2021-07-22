package utils.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import utils.properties.PropertiesUtils;

import java.io.IOException;

public class ParserProductTitleFromSiteUtil {
  final String URL = PropertiesUtils.URL;
  Document document;

  private Document getDocument() {
    try {
      document = Jsoup.connect(URL).get();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
    return document;
  }

  public Elements getProductName() {
    document = getDocument();
    Elements titleElem = document.select("h4 > a > span");
    return titleElem;
  }

}