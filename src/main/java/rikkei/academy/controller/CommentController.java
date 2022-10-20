package rikkei.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rikkei.academy.model.Comment;
import rikkei.academy.service.ICommentService;

@Controller
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("commentList", commentService.findAll());
        model.addAttribute("comment", new Comment());
        return "index";
    }

    @PostMapping("/comment")
    public String comment(Comment comment) {
        commentService.save(comment);
        return "redirect:/";
    }

    @PostMapping("/like")
    public String like(@RequestParam int id) {
        Comment comment = commentService.findById(id);
        comment.setLikeCount(comment.getLikeCount() + 1);
        commentService.save(comment);
        return "redirect:/";
    }
}