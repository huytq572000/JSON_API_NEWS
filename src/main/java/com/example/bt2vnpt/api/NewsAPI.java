package com.example.bt2vnpt.api;

import com.example.bt2vnpt.DTO.NewsDTO;
import com.example.bt2vnpt.DTO.NewsInfoDTO;
import com.example.bt2vnpt.Service.Impl.NewsService;
import com.example.bt2vnpt.converter.NewsConverter;
import com.example.bt2vnpt.entity.News;
import com.example.bt2vnpt.reponse.Response;
import com.example.bt2vnpt.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/news")
public class NewsAPI {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private NewsConverter newsConverter;

    @PostMapping
    public ResponseEntity<?> createNews(@RequestBody NewsDTO newsDTO){
        try {
            newsService.save(newsDTO);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("200 OK", "Thêm thành công", newsDTO));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response("Lưu không thành công", "Lỗi định dạng các trường", ""));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNews(@PathVariable Long id) {
        Optional<NewsDTO> news = newsService.getById(id);
        if (news.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("200 OK", "Tin tức có id: " + id, news)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("404 NOT FOUND", "Không tìm thấy tin tức có id: " + id, "")
            );
        }
    }



      @GetMapping("all-news")
      public ResponseEntity<Map<String,Object>> getAllNewsTest(
              @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
          List<NewsInfoDTO> list = newsService.getAllNews(pageNo, pageSize, sortBy);
        Map<String, Object> response = new HashMap<>();
        response.put("newsList", list);
        response.put("totalItems", pageSize);// số phần tử dược lấy
        response.put("currentPage", pageNo);// vị trí trang được lấy
        response.put("totalPage",newsService.totalPage);//tong so trang
        return new ResponseEntity<>(response, HttpStatus.OK);
      }

//    @GetMapping("all-news")
//    public List<NewsDTO> list(@RequestParam("pageNo") Optional<Integer> pageNo){
//        Pageable pageable = PageRequest.of(pageNo.orElse(0),3);
//        List<NewsDTO> page = newsService.getAllNewsTest(pageable);
//                return page;
//    }
//    @GetMapping("/all-news")
//        public List<NewsInfoDTO> getAllNewsTest(){
//        return newsService.listNews();
//        }
//    @GetMapping
//    public ResponseEntity<?> getAllNews(){
//        List<List<String>> newsList = newsService.getNews();
//        return new ResponseEntity<>(newsList, HttpStatus.OK);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateNews(@RequestBody NewsDTO updateNews,@PathVariable("id") Long id){
        try {
            updateNews.setId(id);
            newsService.update(updateNews);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("200 OK", "Sửa thành công tin tức có id: "+id, updateNews)
                );
            }catch (Exception e){
                if(newsService.checkNewsId == false){
//                    newsService.checkNewsId = true;
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                            new Response("404 NOT FOUND", "Không tìm thấy News có id: " +id, "")
                    );
                }
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new Response("Không thể sửa News có id: " +id, "Lỗi định dạng các trường", ""));
            }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNews(@PathVariable Long id){
//        Optional<News> newsOptional = newsService.getById(id);
//        if(newsOptional.isPresent()){
            newsService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("200 OK", "Xóa thành công tin tức có id: " +id, "")
            );
//        }else{
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                    new Response("404 NOT FOUND", "Không tìm thấy tin tức có id: " + id, "")
//            );
//        }
    }
}

