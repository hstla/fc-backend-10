package fastcampus.projectboard.repository;

import fastcampus.projectboard.config.JpaConfig;
import fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    JpaRepositoryTest(@Autowired ArticleRepository articleRepository,
                      @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    public void givenTestData_whenSelecting_thenWorksFine() throws Exception {
        //given

        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(300);
    }

    @DisplayName("insert 테스트")
    @Test
    public void givenTestData_whenInserting_thenWorksFine() throws Exception {
        //given
        long previousCount = articleRepository.count();
        //when
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "new hashtag"));
        //then
        assertThat(articleRepository.count())
                .isEqualTo(1 + previousCount);

    }

    @DisplayName("update 테스트")
    @Test
    public void givenTestData_whenUpdating_thenWorksFine() throws Exception {
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        article.setHashtag("@springboot");
        //when
        Article savedArticle = articleRepository.saveAndFlush(article);
        //then
        assertThat(savedArticle)
                .hasFieldOrPropertyWithValue("hashtag", "@springboot");

    }

    @DisplayName("delete 테스트")
    @Test
    public void givenTestData_whenDeleting_thenWorksFine() throws Exception {
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentSize = article.getArticleComments().size();

        //when
        articleRepository.delete(article);
        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentSize);
    }
}