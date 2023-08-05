package shop.pointman.wantedpreonboarding.repository;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.vo.PostVo;

import java.util.Optional;

@Transactional
public class PostRepository {
    private final EntityManager em;

    public PostRepository(EntityManager em) {
        this.em = em;
    }

    public Long save(Post post){
        em.persist(post);
        return post.getId();
    }

    public Optional<Post> findByPost(Long id) {
        Post findPost = em.find(Post.class,id);
        return Optional.ofNullable(findPost);
    }
}
