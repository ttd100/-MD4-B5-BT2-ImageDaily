package rikkei.academy.service;

import rikkei.academy.model.Comment;

import java.util.List;

public interface ICommentService {
    List<Comment> findAll();

    void save(Comment comment);

    Comment findById(int id);
}