package app.hyubuki.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "app.hyubuki.article",
        "hyubuki.support.common",
    }
)
public class ArticleApplication {

  public static void main(String[] args) {
    SpringApplication.run(ArticleApplication.class, args);
  }

}
