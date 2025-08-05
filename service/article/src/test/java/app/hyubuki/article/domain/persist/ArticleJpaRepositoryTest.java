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

  Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  ArticleJpaRepository articleJpaRepository;

  @Test
  @DisplayName("findAllTest")
  void findAllTest() {
    // given
    List<Article> articles = articleJpaRepository.findAll(1L, 1499970L, 30L);

    // when

    // then
    log.info("articles.size : {}", articles.size());
  }

  @Test
  @DisplayName("findAllInfiniteScrollTest")
  void findAllInfiniteScrollTest() {
    // given
    List<Article> articles = articleJpaRepository.findAllInfiniteScroll(1L, 30L);
    log.info("articles.size : {}", articles.size());
    for (Article article : articles) {
      log.info("article_id = {}", article.getArticleId());
    }
    // when
    Long lastArticleId = articles.getLast().getArticleId();

    // then
    List<Article> infiniteScroll = articleJpaRepository.findAllInfiniteScroll(1L, 30L, lastArticleId);
    log.info("articles.size : {}", infiniteScroll.size());
    for (Article article : infiniteScroll) {
      log.info("infinite_article_id = {}", article.getArticleId());
    }
  }
}