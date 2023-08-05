package shop.pointman.wantedpreonboarding.service;

import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.vo.PostVo;

import java.util.List;

public interface PostService {

    PostVo savePost(Post post) throws Exception;
    void updatePost(PostVo post);
    void deletePost(String id,String author);
    PostVo findByPost(Long id) throws Exception;
    List<PostVo> findByAll();
}
