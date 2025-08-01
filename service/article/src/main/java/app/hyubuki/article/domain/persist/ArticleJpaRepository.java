package app.hyubuki.article.domain.persist;

import app.hyubuki.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleJpaRepository extends JpaRepository<Article, Long> {

}
