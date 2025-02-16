package com.mironov.pastebin.repo;

import com.mironov.pastebin.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends CrudRepository<PostEntity,Long> {
    PostEntity findByHash(String hash);
}
