package app;

import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import product.Product;
import utils.parser.ParserProductTitleFromSiteUtil;
import utils.properties.PropertiesUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//http://localhost:8080/products/task1

public class Task1 extends HttpServlet {
  private static Logger logger = LoggerFactory.getLogger(Task1.class);
  private ParserProductTitleFromSiteUtil productTitle = new ParserProductTitleFromSiteUtil();
  private Product product;


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    headGenerate(resp);
    productGenerate(resp);
    closeTeg(resp);
  }

  private void closeTeg(HttpServletResponse resp) {
    try {
      resp.getWriter().printf("</body></html>");
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }
  }

  private void headGenerate(HttpServletResponse resp) {
    try {
      resp.getWriter().printf(getHead());
      resp.getWriter().printf("<html><body>");
      resp.getWriter().printf("<h1>" + "Наши товары:" + "</h1>" + "");
      resp.getWriter().printf("<h6>" + "(источник:" + PropertiesUtils.SHORT_URL + ")" + "</h6>");
    } catch (IOException ioException) {
      ioException.printStackTrace();
    }

  }

  private void productGenerate(HttpServletResponse resp) {
    int n = 0;
    for (Element productName : productTitle.getProductName()) {
      product = new Product(productName.text());
      n++;
      try {
        resp.getWriter().printf("<h5>" + "№" + n + ". " + product.getId() + " " + product.getTitle() + " цена:" + product.getCost() + "</h5>");
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
      if (n >= PropertiesUtils.NUMBER_PRODUCTS) return;
    }
  }

  private String getHead() {
    return "<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />";
  }


  @Override
  public void init() throws ServletException {
    logger.debug("Init");
  }
}
