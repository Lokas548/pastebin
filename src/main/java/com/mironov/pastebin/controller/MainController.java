package com.mironov.pastebin.controller;


import com.mironov.pastebin.entity.PostEntity;
import com.mironov.pastebin.repo.PostRepository;
import com.mironov.pastebin.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private final PostService postService;
    private final PostRepository postRepository;


    public MainController(PostService postService, PostRepository postRepository) {
        this.postService = postService;
        this.postRepository = postRepository;
    }


    @PostMapping("/post")
    public ResponseEntity postContent(@RequestBody PostEntity entity){
        postService.addContent(entity.getText(),entity.getLinkDurationTime());
        return ResponseEntity.ok().body("запись добавлена");
    }

    @GetMapping("/post/{link}")
    public ResponseEntity getContent(@PathVariable String link){
        Date date = new Date();
        PostEntity entity = postRepository.findByHash(link);
        if(entity == null){
            return ResponseEntity.badRequest().body("Ссылка не найдена");
        }
        else{
            Long postCreationTime = entity.getCreationTime();
            int postLifeDuration = entity.getLinkDurationTime() * 60;

            if(date.getTime() - postCreationTime < postLifeDuration){
                return ResponseEntity.ok().body(entity.getText());
            }
            else
                deleteExpiredContent(entity);
            return ResponseEntity.badRequest().body("Ссылка не актульна");
        }

    }

    @GetMapping("posts")
    public List<PostEntity> getAllPosts(){
        List<PostEntity> entityList = new ArrayList<>();
        postRepository.findAll().forEach(e-> entityList.add(e));
        return entityList;
    }

    @DeleteMapping("/post/deleted")
    public ResponseEntity deleteExpiredContent(PostEntity entity){
        postRepository.deleteById(entity.getPostId());
        return ResponseEntity.ok("пост удален");
    }

}
