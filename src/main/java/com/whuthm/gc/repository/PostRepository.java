package com.whuthm.gc.repository;

import com.whuthm.gc.domain.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, String> {
}
