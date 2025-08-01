package app.hyubuki.article.api;

import app.hyubuki.article.domain.service.ArticleService;
import app.hyubuki.article.domain.service.dto.request.ArticleCreateRequest;
import app.hyubuki.article.domain.service.dto.request.ArticleUpdateRequest;
import hyubuki.support.common.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping("/v1/articles/{articleId}")
  public CommonResponse<?> readArticle(@PathVariable Long articleId) {
    return CommonResponse.success(articleService.read(articleId));
  }

  @PostMapping("/v1/articles")
  public CommonResponse<?> createArticle(@RequestBody ArticleCreateRequest request) {
    return CommonResponse.success(articleService.create(request));
  }

  @PutMapping("/v1/articles/{articleId}")
  public CommonResponse<?> updateArticle(@PathVariable Long articleId, @RequestBody ArticleUpdateRequest request) {
    return CommonResponse.success(articleService.update(articleId, request));
  }

  @DeleteMapping("v1/articles/{articleId}")
  public CommonResponse<?> deleteArticle(@PathVariable Long articleId) {
    articleService.delete(articleId);
    return CommonResponse.success();
  }
}
