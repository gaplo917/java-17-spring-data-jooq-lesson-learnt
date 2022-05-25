package com.gaplotech.lesson.one.repository;

import com.gaplotech.lesson.one.entity.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
