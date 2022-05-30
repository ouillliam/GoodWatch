package goodwatch.app.comment;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @PostMapping(value = "/comment")
    public String saveComment(@ModelAttribute("commentForm") CommentForm commentForm){
        this.commentService.saveComment(commentForm);
        return "redirect:movie/" + commentForm.getMovieID() ;
    }
    
}
