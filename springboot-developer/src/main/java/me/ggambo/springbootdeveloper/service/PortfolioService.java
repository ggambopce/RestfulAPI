package me.ggambo.springbootdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import me.ggambo.springbootdeveloper.domain.Article;
import me.ggambo.springbootdeveloper.dto.AddArticleRequest;
import me.ggambo.springbootdeveloper.dto.UpdateArticleRequest;
import me.ggambo.springbootdeveloper.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor // final이 붙거나 @NotNull이 붙은 필드의 생성자 추가
@Service // 빈으로 서블릿 컨테이너에 등록
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;

    public Article save(AddArticleRequest request) { //article데이터베이스에 저장메서드
        return portfolioRepository.save(request.toEntity());
    }
    public List<Article> findAll() {
        return portfolioRepository.findAll();
    }

    public Article findById(long id) {
        return portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void delete(long id) {
        portfolioRepository.deleteById(id);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = portfolioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(request.getTitle(), request.getContent());

        return  article;
    }
}
