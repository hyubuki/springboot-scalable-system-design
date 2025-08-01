package app.hyubuki.article.domain.service;

import app.hyubuki.article.domain.persist.ArticleJpaRepository;
import app.hyubuki.article.domain.service.dto.request.ArticleCreateRequest;
import app.hyubuki.article.domain.service.dto.request.ArticleUpdateRequest;
import app.hyubuki.article.domain.service.dto.response.ArticleResponse;
import app.hyubuki.article.entity.Article;
import hyubuki.support.common.snowflake.Snowflake;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final Snowflake snowflake = new Snowflake();
  private final ArticleJpaRepository articleJpaRepository;

  @Transactional
  public ArticleResponse create(ArticleCreateRequest request) {
    Article article = articleJpaRepository.save(
        Article.create(snowflake.nextId(), request.title(), request.content(), request.writerId(), request.boardId())
    );

    return ArticleResponse.from(article);
  }

  @Transactional
  public ArticleResponse update(Long articleId, ArticleUpdateRequest request) {
    Article article = articleJpaRepository.findById(articleId).orElseThrow(
        // 미정
    );
    //
    article.update(request.title(), request.content());
    return ArticleResponse.from(article);
  }

  public ArticleResponse read(Long articleId) {
    return ArticleResponse.from(articleJpaRepository.findById(articleId).orElseThrow());
  }

  @Transactional
  public void delete(Long articleId) {
    articleJpaRepository.deleteById(articleId);
  }
}
