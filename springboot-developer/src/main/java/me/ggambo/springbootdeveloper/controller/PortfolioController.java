package me.ggambo.springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import me.ggambo.springbootdeveloper.domain.Article;
import me.ggambo.springbootdeveloper.dto.AddArticleRequest;
import me.ggambo.springbootdeveloper.dto.ArticleResponse;
import me.ggambo.springbootdeveloper.service.PortfolioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController //HTTP Response Body에 객체 데이터를 JSON형식으로 반환하는 컨트롤러
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/api/articles")
    //HTTP 메서드가 POST일때 전달받은 URL과 동일하면 메서드로 요청본문 값 매핑
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request){
        Article savedArticle = portfolioService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle); //요청한 자원 생성 저장된 글 정보를 응답 객체에 담아 전송
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        List<ArticleResponse> articles = portfolioService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();

        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable long id) {
        Article article = portfolioService.findById(id);

        return  ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }
}
