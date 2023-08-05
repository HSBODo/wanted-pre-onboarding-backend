package shop.pointman.wantedpreonboarding;

import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import shop.pointman.wantedpreonboarding.repository.MemberRepository;
import shop.pointman.wantedpreonboarding.repository.PostRepository;

@Configuration
public class BeanConfig {
    private EntityManager em;
    public BeanConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public PostRepository postRepository(){
        return new PostRepository(em);
    }
    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepository(em);
    }
}
