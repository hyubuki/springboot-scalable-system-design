package app.hyubuki.article.domain.persist;

import app.hyubuki.article.entity.Article;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ArticleJpaRepositoryTest {

  Logger logger = LoggerFactory.getLogger(ArticleJpaRepositoryTest.class);
  @Autowired
  ArticleJpaRepository articleJpaRepository;

  @Test
  @DisplayName("findAllTest")
  void findAllTest() {
    // given
    List<Article> articles = articleJpaRepository.findAll(1L, 1499970L, 30L);

    // when

    // then
    logger.info("articles.size : {}", articles.size());
    articles.forEach(System.out::println);
  }
}