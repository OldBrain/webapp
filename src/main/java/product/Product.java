package product;

public class Product {
  long id;
  String title;
  float cost;

  public Product(String title) {
    this.id = (long) Math.ceil(Math.random() * (100000 - 10000) + 10000);
    this.title = title;
    this.cost = (float) Math.ceil(Math.random() * (10000 - 1000) + 1000);
  }

  public long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public float getCost() {
    return cost;
  }
}
