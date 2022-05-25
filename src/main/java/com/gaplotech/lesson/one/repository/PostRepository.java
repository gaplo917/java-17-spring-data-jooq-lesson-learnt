package com.gaplotech.lesson.one.repository;

import com.gaplotech.lesson.one.entity.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
}
