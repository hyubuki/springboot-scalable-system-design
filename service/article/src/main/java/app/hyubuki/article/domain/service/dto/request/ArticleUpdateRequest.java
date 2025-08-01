package app.hyubuki.article.domain.service.dto.request;

public record ArticleUpdateRequest(
    String title,
    String content
) {

}
