package app.hyubuki.articleread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "app.hyubuki.articleread",
    }
)
public class ArticleReadApplication {
  public static void main(String[] args) {
    SpringApplication.run(ArticleReadApplication.class, args);
  }

}
