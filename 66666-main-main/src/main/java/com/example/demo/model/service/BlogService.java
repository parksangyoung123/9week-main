package com.example.demo.model.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
// import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;
// import com.example.demo.model.repository.BlogRepository;
import com.example.demo.model.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // 생성자자동생성(부분)
public class BlogService{
    @Autowired // 객체주입자동화, 생성자1개면생략가능
    private final BoardRepository blogRepository; // 리포지토리선언

 
    
    public List<Board> findAll() { // 게시판전체목록조회
        return blogRepository.findAll();
    }

    // public Article save(AddArticleRequest request){
    //     // DTO가 없는 경우 이곳에 직접 구현 가능
    //     // public ResponseEntity<Article> addArticle(@RequestParam String title, @RequestParam String content) {
    //     // Article article = Article.builder()
    //     // .title(title)
    //     // .content(content)
    //     // .build();
    //     return blogRepository.save(request.toEntity());
    //     }

    public Optional<Board> findById(Long id) { // 게시판 특정 글 조회
        return blogRepository.findById(id);
        }

    public Board save(AddArticleRequest request){
        // DTO가 없는 경우 이곳에 직접 구현 가능
        return blogRepository.save(request.toEntity());
         }


    public Page<Board> findAll(Pageable pageable) {
    return blogRepository.findAll(pageable);
}

    public Page<Board> searchByKeyword(String keyword, Pageable pageable) {
    return blogRepository.findByTitleContainingIgnoreCase(keyword, pageable);
    } // LIKE 검색 제공(대소문자 무시)
    

    //     public void update(Long id, AddArticleRequest request) {
    //     Optional<Board> optionalArticle = blogRepository.findById(id); // 단일 글 조회

    //     optionalArticle.ifPresent(article -> { // 값이 있으면

    //     board.update(request.getTitle(), request.getContent()); // 값을 수정
    //     blogRepository.save(board); // Article 객체에 저장
    //     });
    //     }
    //     public void delete(Long id) {
    //         blogRepository.deleteById(id);
    //         }
            
}