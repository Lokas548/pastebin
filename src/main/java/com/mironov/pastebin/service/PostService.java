package com.mironov.pastebin.service;


import com.mironov.pastebin.entity.PostEntity;
import com.mironov.pastebin.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class PostService {


    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity addContent(String content,int time){
        Date date = new Date();
        PostEntity entity = new PostEntity();
        String hashLink = linkGenerator();

        entity.setText(content);
        entity.setLinkDurationTime(time);
        entity.setCreationTime(date.getTime());
        entity.setHash(hashLink);

        return postRepository.save(entity);
    }

    private String linkGenerator(){
        String newHash = UUID.randomUUID().toString();
        if(postRepository.findByHash(newHash) != null){
            linkGenerator();
        }
        else{
            return newHash;
        }
        throw new Error("Ошибка генерации хэша для ссылки");
    }
}
