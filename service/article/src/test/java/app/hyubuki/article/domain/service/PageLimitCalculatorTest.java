package app.hyubuki.article.domain.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PageLimitCalculatorTest {

  @Test
  @DisplayName("calculatePageLimitTest")
  void calculatePageLimitTest() {
    // given
    calculatePageLimitTest(1L, 30L, 10L, 301L);
    calculatePageLimitTest(10L, 30L, 10L, 301L);

    calculatePageLimitTest(11L, 30L, 10L, 601L);
    // when

    // then
  }

  void calculatePageLimitTest(final Long page, final Long pageSize, final Long movablePageCount, final Long expected) {
      Long result = PageLimitCalculator.calculatePageLimit(page, pageSize, movablePageCount);
      assertThat(result).isEqualTo(expected);
  }
}