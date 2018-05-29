package com.whuthm.gc.repository;

import com.whuthm.gc.domain.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, String>  {
}
