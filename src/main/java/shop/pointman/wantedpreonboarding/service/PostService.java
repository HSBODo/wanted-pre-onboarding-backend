package shop.pointman.wantedpreonboarding.service;

import shop.pointman.wantedpreonboarding.domain.Post;
import shop.pointman.wantedpreonboarding.vo.BaseVo;
import shop.pointman.wantedpreonboarding.vo.PostListVo;
import shop.pointman.wantedpreonboarding.vo.PostVo;

import java.awt.print.Pageable;
import java.util.List;

public interface PostService {

    PostVo savePost(Post post) throws Exception;
    PostVo updatePost(Post post);
    BaseVo deletePost(Post post);
    PostVo findByPost(Long id) throws Exception;
    PostListVo findByAll(int page, int limit);
}
