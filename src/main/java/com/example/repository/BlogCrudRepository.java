package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.entity.Blog;

public interface BlogCrudRepository extends CrudRepository<Blog, Integer> {
}