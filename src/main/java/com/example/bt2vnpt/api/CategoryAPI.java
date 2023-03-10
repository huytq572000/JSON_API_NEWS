package com.example.bt2vnpt.api;

import com.example.bt2vnpt.Service.Impl.CategoryServicce;
import com.example.bt2vnpt.entity.Category;
import com.example.bt2vnpt.reponse.CustomException;
import com.example.bt2vnpt.reponse.Response;
import com.example.bt2vnpt.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryAPI {

    @Autowired
    private CategoryServicce categoryServicce;
    @Autowired
    CategoryRepository categoryRepository;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category){
        try {
            categoryServicce.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("200 OK", "Thêm thành công", category));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response("Lưu không thành công", "Lỗi định dạng các trường" , ""));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryServicce.getById(id);
        if(categoryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("200 OK", "Loại tin có id: "+id, categoryOptional));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new Response("404 NOT FOUND", "Không tìm thấy loại tin có id: " + id, ""));
        }
    }

    @GetMapping("/all-category")
    public ResponseEntity<Map<String,Object>> getAllCategory(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Category> list = categoryServicce.getAllCategory(pageNo, pageSize, sortBy);
        Map<String, Object> response = new HashMap<>();
        response.put("categoryList", list);
        response.put("totalItems", pageSize);// số phần tử dược lấy
        response.put("currentPage", pageNo);// vị trí trang được lấy
        response.put("totalPage", CategoryServicce.totalPage); // tổng số trang
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> categoryOptional = categoryServicce.getById(id);
        return categoryOptional.map(
                category1 -> {
            category.setId(category1.getId());
            categoryServicce.save(category);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new Response("200 OK", "Sửa thành công loại tin có id: "+id, category));
        }).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Response("Không thể sửa loại tin có id: "+id, "Không tìm thấy loại tin có  id: "+id , "")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
          return categoryServicce.getById(id).map(category -> {
              categoryServicce.deleteCategory(category);
              return ResponseEntity.status(HttpStatus.OK).body(
                      new Response("200", "Xóa thành công loại tin có id: "+id, ""));
          }).orElseGet(() -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                  new Response("Không thể xóa loại tin có id: "+id, "Không tìm thấy loại tin có  id: "+id , "")));
    }
}
