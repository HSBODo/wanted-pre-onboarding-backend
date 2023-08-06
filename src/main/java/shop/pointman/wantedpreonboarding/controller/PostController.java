package shop.pointman.wantedpreonboarding.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.service.PostService;
import shop.pointman.wantedpreonboarding.vo.BaseVo;
import shop.pointman.wantedpreonboarding.vo.PostListVo;
import shop.pointman.wantedpreonboarding.vo.PostVo;


@Slf4j
@Controller
@RequestMapping(value = "/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    @ResponseBody
    public PostVo createPost(@RequestBody Post post){
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

    @GetMapping("/{id}")
    @ResponseBody
    public PostVo findPost(@PathVariable String id){
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

    @GetMapping("/")
    @ResponseBody
    public PostListVo findAll(@RequestParam int page, @RequestParam int limit){
        PostListVo response = new PostListVo();
        try{
            response = postService.findByAll(page,limit);
        }catch (Exception e){
            response.setMsg("개시글 조회에 실패하였습니다.");
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }


    @PutMapping()
    @ResponseBody
    public PostVo updatePost(@RequestBody Post post){
        PostVo response = new PostVo();
        try{
            response = postService.updatePost(post);
        }catch (Exception e){
            response.setMsg("개시글 수정에 실패하였습니다.");
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }

    @DeleteMapping()
    @ResponseBody
    public BaseVo deletePost(@RequestBody Post post){
        BaseVo response = new BaseVo();
        try{
            response = postService.deletePost(post);
        }catch (Exception e){
            response.setMsg("개시글 삭제에 실패하였습니다.");
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }
}
