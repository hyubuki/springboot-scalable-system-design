package app.hyubuki.article.domain.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageLimitCalculator {

  public static Long calculatePageLimit(Long page, Long pageSize, Long movablePageCount) {

    // offset값 구하기
    // 몇개의 페이지를 제공할지에 따른 공식
    return (((page - 1) / movablePageCount) + 1) * pageSize * movablePageCount + 1;
  }
}
