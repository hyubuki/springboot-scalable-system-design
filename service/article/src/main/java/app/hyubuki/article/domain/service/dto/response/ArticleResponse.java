package app.hyubuki.article.domain.service.dto.response;

import app.hyubuki.article.entity.Article;
import java.time.LocalDateTime;

public record ArticleResponse(
    Long articleId,
    String title,
    String content,
    Long boardId,
    Long writerId,
    LocalDateTime createdAt,
    LocalDateTime modified_at
) {
  public static ArticleResponse from(Article article) {
    return new ArticleResponse(
        article.getArticleId(),
        article.getTitle(),
        article.getContent(),
        article.getWriterId(),
        article.getBoardId(),
        article.getCreatedAt(),
        article.getModifiedAt()
    );
  }
}
