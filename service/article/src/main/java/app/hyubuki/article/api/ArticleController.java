package app.hyubuki.article.api;

import app.hyubuki.article.domain.service.ArticleService;
import app.hyubuki.article.domain.service.dto.request.ArticleCreateRequest;
import app.hyubuki.article.domain.service.dto.request.ArticleUpdateRequest;
import app.hyubuki.article.domain.service.dto.response.ArticleResponse;
import hyubuki.support.common.response.CommonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ArticleController {

  private final ArticleService articleService;

  @GetMapping("/v1/articles/{articleId}")
  public CommonResponse<?> read(@PathVariable Long articleId) {
    return CommonResponse.success(articleService.read(articleId));
  }

  @GetMapping("/v1/articles")
  public CommonResponse<?> readAll(
      @RequestParam Long boardId,
      @RequestParam Long offset,
      @RequestParam Long limit) {
    return CommonResponse.success(articleService.readAll(boardId, offset, limit));
  }

  @GetMapping("/v1/articles/infinite-scroll")
  public CommonResponse<?> readAllInfiniteScroll(
      @RequestParam Long boardId,
      @RequestParam Long limit,
      @RequestParam(value = "lastArticleId", required = false) Long lastArticleId
  ) {
    return CommonResponse.success(articleService.readAllInfiniteScroll(boardId, limit, lastArticleId));
  }

  @PostMapping("/v1/articles")
  public CommonResponse<?> create(@RequestBody ArticleCreateRequest request) {
    return CommonResponse.success(articleService.create(request));
  }

  @PutMapping("/v1/articles/{articleId}")
  public CommonResponse<?> update(@PathVariable Long articleId, @RequestBody ArticleUpdateRequest request) {
    return CommonResponse.success(articleService.update(articleId, request));
  }

  @DeleteMapping("v1/articles/{articleId}")
  public CommonResponse<?> delete(@PathVariable Long articleId) {
    articleService.delete(articleId);
    return CommonResponse.success();
  }
}