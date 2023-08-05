package shop.pointman.wantedpreonboarding.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.service.PostService;
import shop.pointman.wantedpreonboarding.vo.PostVo;


@SpringBootTest
@Slf4j
class PostServiceImplTest {
    @Autowired
    private PostService postService;

    @Test
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
    void updatePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void findByAll() {
    }
}