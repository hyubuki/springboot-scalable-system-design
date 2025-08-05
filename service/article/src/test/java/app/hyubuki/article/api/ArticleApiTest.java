package app.hyubuki.article.api;

import static org.assertj.core.api.Assertions.assertThat;

import app.hyubuki.article.domain.service.dto.request.ArticleCreateRequest;
import app.hyubuki.article.domain.service.dto.request.ArticleUpdateRequest;
import app.hyubuki.article.domain.service.dto.response.ArticleResponse;
import hyubuki.support.common.response.ApiResult;
import hyubuki.support.common.response.CommonResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ArticleApiTest {

  Long id = Long.MIN_VALUE;
  RestClient restClient = RestClient.create("http://local.hyubuki.com:9000");

  @Test
  @Order(1)
  @DisplayName("createTest")
  void createTest() {
    // given
    CommonResponse<ArticleResponse> response = create(new ArticleCreateRequest("hi", "my content", 1L, 1L));
    // when
    
    // then
    ArticleResponse article = response.getData();
    assertThat(response.getData()).isInstanceOf(ArticleResponse.class);
    id = article.articleId();
    assertThat(article.title()).isEqualTo("hi");
    assertThat(article.content()).isEqualTo("my content");
    assertThat(article.writerId()).isEqualTo(1L);
    assertThat(article.boardId()).isEqualTo(1L);

    System.out.println("response = " + response.getData().toString());
  }

  // TODO(25. 7. 31.) :  ParmeterizedTypeReference 학습
  // Jackson 라이브러리는 필드 순서 보장을 위해 HashMap 대신 LinkedHashMap 활용
  // Jackson은 Generic Type을 Erasure하기 때문에, Data Type을 명확히 하려면 ParmeterizedTypeReference 명시해야 한다.
  CommonResponse<ArticleResponse> create(ArticleCreateRequest request) {
    return restClient.post()
        .uri("/v1/articles")
        .body(request)
        .retrieve()
        .body(new ParameterizedTypeReference<CommonResponse<ArticleResponse>>() {});
  }

  @Test
  @Order(2)
  @DisplayName("readTest")
  void readTest() {
    // given
    CommonResponse<ArticleResponse> response = read(id);
    // when

    // then
    ArticleResponse article = response.getData();
    assertThat(response.getData()).isInstanceOf(ArticleResponse.class);
    assertThat(article.title()).isEqualTo("hi");
    assertThat(article.content()).isEqualTo("my content");
    assertThat(article.writerId()).isEqualTo(1L);
    assertThat(article.boardId()).isEqualTo(1L);
  }

  CommonResponse<ArticleResponse> read(Long articleId) {
    return restClient.get()
        .uri("/v1/articles/{articleId}", articleId)
        .retrieve()
        .body(new ParameterizedTypeReference<CommonResponse<ArticleResponse>>() {});
  }

  @Test
  @Order(3)
  @DisplayName("updateTest")
  void updateTest() {
    // given
    CommonResponse<ArticleResponse> response = update(id, new ArticleUpdateRequest("hi-update", "my content-update"));
    // when

    // then
    ArticleResponse article = response.getData();
    assertThat(response.getData()).isInstanceOf(ArticleResponse.class);
    assertThat(article.title()).isEqualTo("hi-update");
    assertThat(article.content()).isEqualTo("my content-update");
    assertThat(article.writerId()).isEqualTo(1L);
    assertThat(article.boardId()).isEqualTo(1L);
  }

  CommonResponse<ArticleResponse> update(Long articleId, ArticleUpdateRequest request) {
    return restClient.put()
        .uri("/v1/articles/{articleId}", articleId)
        .body(request)
        .retrieve()
        .body(new ParameterizedTypeReference<CommonResponse<ArticleResponse>>() {});
  }

  @Test
  @Order(4)
  @DisplayName("deleteTest")
  void deleteTest() throws InterruptedException {
    CommonResponse<?> response = restClient.delete()
        .uri("/v1/articles/{articleId}", id)
        .retrieve()
        .body(CommonResponse.class);
    // then
    assertThat(response.getApiResult()).isEqualTo(ApiResult.SUCCESS);
  }
}