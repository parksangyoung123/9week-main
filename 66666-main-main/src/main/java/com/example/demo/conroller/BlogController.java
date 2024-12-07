package com.example.demo.conroller;

// import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.service.AddArticleRequest;
import com.example.demo.model.service.BlogService;

import jakarta.servlet.http.HttpSession;

// import com.example.demo.model.domain.Article;
import com.example.demo.model.domain.Board;

@Controller // 컨트롤러 어노테이션 명시
public class BlogController {
    @Autowired
    BlogService blogService;


    @GetMapping("/board_list") // 새로운 게시판 링크 지정
    public String board_list(
            Model model,
            @RequestParam(defaultValue = "0") int page, // 기본 페이지 0
            @RequestParam(defaultValue = "") String keyword, // 검색 키워드 기본값
            HttpSession session) { // 세션 객체 전달
        // 세션 정보 확인
        String userId = (String) session.getAttribute("userId"); // 세션에서 userId 확인
        String email = (String) session.getAttribute("email"); // 세션에서 이메일 확인
    
        if (userId == null) { // 로그인이 필요한 경우
            return "redirect:/member_login"; // 로그인 페이지로 리다이렉트
        }
    
        // 디버깅용 출력
        System.out.println("세션 userId: " + userId);
    
        // 게시판 데이터 설정
        int startNum = page * 10; // 예: 페이지당 10개의 항목
        model.addAttribute("email", email); // 로그인 사용자 이메일 전달
        model.addAttribute("startNum", startNum); // 시작 번호 전달
        model.addAttribute("page", page); // 현재 페이지 전달
        model.addAttribute("keyword", keyword); // 검색 키워드 전달
    
        // TODO: 실제 게시판 데이터 처리 로직 추가
        // 예: blogService를 호출하여 게시글 데이터를 가져오는 방식
        // List<Board> boards = blogService.getBoards(page, keyword);
        // model.addAttribute("boards", boards);
    
        return "board_list"; // 게시판 페이지로 연결
    }
    

    // @GetMapping("/board_list") // 새로운 게시판 링크 지정
    //     public String board_list(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword) {
    //     PageRequest pageable = PageRequest.of(page, 3); // 한 페이지의 게시글 수
    //     Page<Board> list; // Page를 반환
    //     if (keyword.isEmpty()) {
    //     list = blogService.findAll(pageable); // 기본 전체 출력(키워드 x)
    //     } else {
    //     list = blogService.searchByKeyword(keyword, pageable); // 키워드로 검색
    //     }
    //     model.addAttribute("boards", list); // 모델에 추가
    //     model.addAttribute("totalPages", list.getTotalPages()); // 페이지 크기
    //     model.addAttribute("currentPage", page); // 페이지 번호
    //     model.addAttribute("keyword", keyword); // 키워드
    //     return "board_list"; // .HTML 연결
    //     }
    
    // @GetMapping("/board_list") // 새로운 게시판 링크 지정
    // public String board_list(Model model) {
    //     List<Board> list = blogService.findAll(); // 게시판 전체 리스트
    //     model.addAttribute("boards", list); // 모델에 추가
    //     return "board_list"; // .HTML 연결
    //     }

    @GetMapping("/board_write")
    public String board_write() {
        return "board_write";
    }

    @PostMapping("/api/boards") // 글쓰기 게시판 저장
    public String addboards(@ModelAttribute AddArticleRequest request) {
        blogService.save(request);
        return "redirect:/board_list"; // .HTML 연결
    }

    @GetMapping("/board_view/{id}") // 게시판 링크 지정
    public String board_view(Model model, @PathVariable Long id) {
        Optional<Board> list = blogService.findById(id); // 선택한 게시판 글

        if (list.isPresent()) {
            model.addAttribute("boards", list.get()); // 존재할 경우 실제 Article 객체를 모델에 추가
        } else {
            // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)
            return "/error_page/article_error"; // 오류 처리 페이지로 연결
        }
        return "board_view"; // .HTML 연결
    }

    // @PostMapping("/api/articles")
    // public String addArticle(@ModelAttribute AddArticleRequest request) {
    // Article saveArticle = blogService.save(request);
    // return "redirect:/article_list";
    // }

    // @GetMapping("/article_edit/{id}") // 게시판 링크 지정
    // public String article_edit(Model model, @PathVariable Long id) {
    // Optional<Article> list = blogService.findById(id); // 선택한 게시판 글
    // if (list.isPresent()) {
    // model.addAttribute("article", list.get()); // 존재하면 Article 객체를 모델에 추가
    // } else {
    // // 처리할 로직 추가 (예: 오류 페이지로 리다이렉트, 예외 처리 등)

    // return"/error_page/article_error"; // 오류 처리 페이지로 연결
    // }
    // return "article_edit"; // .HTML 연결

    // }

    // @PutMapping("/api/article_edit/{id}")
    // public String updateArticle(@PathVariable Long id, @ModelAttribute
    // AddArticleRequest request) {
    // blogService.update(id, request);
    // return "redirect:/article_list"; // 글 수정 이후 .html 연결
    // }

    // @DeleteMapping("/api/article_delete/{id}")
    // public String deleteArticle(@PathVariable Long id) {
    // blogService.delete(id);
    // return "redirect:/article_list";
    // }

}


