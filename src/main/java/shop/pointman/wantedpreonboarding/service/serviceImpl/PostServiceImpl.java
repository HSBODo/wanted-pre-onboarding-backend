package shop.pointman.wantedpreonboarding.service.serviceImpl;

import lombok.Setter;
import org.springframework.stereotype.Service;
import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.repository.PostRepository;
import shop.pointman.wantedpreonboarding.service.PostService;
import shop.pointman.wantedpreonboarding.vo.PostVo;

import java.util.List;
import java.util.Optional;

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

        response.setMsg("글작성을 완료했습니다.");
        response.setCode("00");
        response.setId(String.valueOf(postId));
        response.setSuccess(true);
        return response;
    }

    @Override
    public void updatePost(PostVo post) {

    }

    @Override
    public void deletePost(String id, String author) {

    }

    @Override
    public PostVo findByPost(Long id) {
        PostVo result = new PostVo();
        Post post = postRepository.findByPost(id).orElseThrow();
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
    public List<PostVo> findByAll() {
        return null;
    }
}
