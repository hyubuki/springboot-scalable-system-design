package app.hyubuki.article.domain.service.dto.request;

public record ArticleCreateRequest(
    String title,
    String content,
    Long writerId,
    Long boardId
) {

}
