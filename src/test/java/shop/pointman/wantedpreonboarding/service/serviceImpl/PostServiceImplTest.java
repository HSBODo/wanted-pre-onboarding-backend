package shop.pointman.wantedpreonboarding.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.service.PostService;
import shop.pointman.wantedpreonboarding.vo.BaseVo;
import shop.pointman.wantedpreonboarding.vo.PostListVo;
import shop.pointman.wantedpreonboarding.vo.PostVo;

import java.util.List;


@SpringBootTest
@Slf4j
class PostServiceImplTest {
    @Autowired
    private PostService postService;

    @Test
    @Transactional
    void savePost() throws Exception {
        Post post = new Post();
        post.setAuthor("테스트");
        post.setTitle("제목");
        post.setContent("글내용");
        PostVo result = postService.savePost(post);
        Assertions.assertThat(result.getCode()).isEqualTo("00");
        log.info("postId={}",result.getId());
    }

    @Test
    void findByPost() throws Exception{
        Long postId=252L;
        PostVo result = postService.findByPost(postId);
        Assertions.assertThat(String.valueOf(postId)).isEqualTo(result.getId());

    }

    @Test
    @Transactional
    void updatePost() {
        Post post = new Post();
        post.setAuthor("테스트");
        post.setContent("글내용 수정");
        post.setId(252L);
        PostVo result = postService.updatePost(post);
        log.info("msg={}",result.getMsg());
        Assertions.assertThat(result.getCode()).isEqualTo("00");


    }

    @Test
    @Transactional
    void deletePost() {
        Post post = new Post();
        post.setAuthor("테스트");
        post.setId(252L);
        BaseVo result = postService.deletePost(post);
        Assertions.assertThat(result.getCode()).isEqualTo("00");
    }

    @Test
    void findByAll() {
        int offset = 0;
        int limit = 10;
        PostListVo result = postService.findByAll(offset,limit);
        List<Post> posts = result.getPosts();
        for(Post post : posts){
            log.info("content={}",post.getContent());
        }
        Assertions.assertThat(posts.size()).isLessThan(limit);

    }
}