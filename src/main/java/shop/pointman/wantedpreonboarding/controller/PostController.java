package shop.pointman.wantedpreonboarding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.service.AuthService;
import shop.pointman.wantedpreonboarding.service.PostService;
import shop.pointman.wantedpreonboarding.vo.BaseVo;
import shop.pointman.wantedpreonboarding.vo.PostVo;

@Controller
@RequestMapping(value = "/post/*")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/add")
    @ResponseBody
    public BaseVo addPost(@ModelAttribute Post post){
        PostVo response = new PostVo();
        try{
            response = postService.savePost(post);
        }catch (Exception e){
            response.setMsg("개시글 작성에 실패하였습니다.");
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public BaseVo getPost(@PathVariable String id){
        PostVo response = new PostVo();
        try{
            Long postId = Long.parseLong(id);
            response = postService.findByPost(postId);
        }catch (Exception e){
            response.setMsg("개시글 조회에 실패하였습니다.");
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }
}
