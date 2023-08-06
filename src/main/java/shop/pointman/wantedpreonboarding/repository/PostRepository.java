package shop.pointman.wantedpreonboarding.repository;

import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Transactional;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.vo.PostVo;

import java.awt.print.Pageable;
import java.util.List;
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

    public List<Post> findAll(int offset, int limit) {
        return  em.createQuery("select p from Post p order by p.regDate desc", Post.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
    public Optional<Post> updatePost(Long id, String content) {
        Post findPost = em.find(Post.class, id);
        findPost.setContent(content);

        return Optional.ofNullable(findPost);
    }

    public void deletePost(Long id) {
        Post findPost = em.find(Post.class, id);
        em.remove(findPost);
    }
}
