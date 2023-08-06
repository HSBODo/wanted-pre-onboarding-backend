package shop.pointman.wantedpreonboarding.service.serviceImpl;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.repository.PostRepository;
import shop.pointman.wantedpreonboarding.service.PostService;
import shop.pointman.wantedpreonboarding.vo.BaseVo;
import shop.pointman.wantedpreonboarding.vo.PostListVo;
import shop.pointman.wantedpreonboarding.vo.PostVo;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostVo savePost(Post post) throws Exception {

        PostVo response = new PostVo();
        String author = post.getAuthor();
        String title = post.getTitle();
        String content = post.getContent();

        if("".equals(author)){
            response.setMsg("작성자를 입력해주세요");
            response.setCode("02");
            response.setSuccess(false);
            return response;
        }

        if("".equals(title)){
            response.setMsg("글제목을 입력해주세요");
            response.setCode("03");
            response.setSuccess(false);
            return response;
        }

        if("".equals(content)){
            response.setMsg("글내용을 입력해주세요");
            response.setCode("04");
            response.setSuccess(false);
            return response;
        }
        Long postId = postRepository.save(post);
        response.setAuthor(author);
        response.setTitle(title);
        response.setContent(content);
        response.setMsg("글작성을 완료했습니다.");
        response.setCode("00");
        response.setId(String.valueOf(postId));
        response.setSuccess(true);
        return response;
    }

    @Override
    public PostVo updatePost(Post post) {
        PostVo result = new PostVo();
        Long id = post.getId();
        String content = post.getContent();



        Optional<Post> mayBePost = postRepository.findByPost(id);
        if(mayBePost.isEmpty()){
            result.setMsg("게시글이 존재하지 않습니다.");
            result.setCode("01");
            result.setSuccess(false);
            return result;
        }
        Post getPost = mayBePost.get();

        if (!post.getAuthor().equals(getPost.getAuthor())){
            result.setMsg("게시글 수정 권한이 없습니다.");
            result.setCode("05");
            result.setSuccess(false);
            return result;
        }
        Optional<Post> mayBeUpdatePost = postRepository.updatePost(id, content);
        if(mayBeUpdatePost.isEmpty()){
            result.setMsg("게시글 수정에 실패하였습니다.");
            result.setCode("04");
            result.setSuccess(false);
            return result;
        }else {
            result.setId(String.valueOf(id));
            result.setAuthor(getPost.getAuthor());
            result.setTitle(getPost.getTitle());
            result.setContent(content);
            result.setMsg("게시글 수정에 성공하였습니다");
            result.setCode("00");
            result.setSuccess(true);
        }

        return result;
    }

    @Override
    public BaseVo deletePost(Post post) {
        BaseVo result = new BaseVo();
        Long id = post.getId();
        Optional<Post> mayBePost = postRepository.findByPost(id);
        if(mayBePost.isEmpty()){
            result.setCode("01");
            result.setMsg("게시글이 존재하지 않습니다.");
            result.setSuccess(false);
            return result;
        }
        Post getPost = mayBePost.get();

        if (!post.getAuthor().equals(getPost.getAuthor())){
            result.setMsg("게시글 삭제 권한이 없습니다.");
            result.setCode("05");
            result.setSuccess(false);
            return result;
        }

        postRepository.deletePost(id);
        result.setMsg("게시글 삭제에 성공하셨습니다.");
        result.setCode("00");
        result.setSuccess(true);

        return result;
    }

    @Override
    public PostVo findByPost(Long id) {
        PostVo result = new PostVo();
        Optional<Post> mayBePost = postRepository.findByPost(id);
        if(mayBePost.isEmpty()){
            result.setCode("01");
            result.setMsg("게시글이 존재하지 않습니다.");
            result.setSuccess(false);
            return result;
        }
        Post post = mayBePost.get();
        result.setId(String.valueOf(post.getId()));
        result.setAuthor(post.getAuthor());
        result.setTitle(post.getTitle());
        result.setContent(post.getContent());
        result.setCode("00");
        result.setMsg("게시글조회성공");
        result.setSuccess(true);

        return result;
    }

    @Override
    public  PostListVo findByAll(int page, int limit) {
        PostListVo result = new PostListVo();

        int offset = (page*limit)-limit;

        List<Post> findAll = postRepository.findAll(offset,limit) ;
        if(findAll.isEmpty()){
            result.setCode("01");
            result.setMsg("게시글이 존재하지 않습니다.");
            result.setSuccess(false);
            return result;
        }
        result.setPosts(findAll);
        result.setCode("00");
        result.setMsg("게시글 조회에 성공하였습니다.");
        result.setSuccess(true);
        return result;
    }
}
