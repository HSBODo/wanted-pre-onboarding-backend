package shop.pointman.wantedpreonboarding.vo;

import lombok.Getter;
import lombok.Setter;
import shop.pointman.wantedpreonboarding.domain.Post;

import java.util.List;

@Setter
@Getter
public class PostListVo extends BaseVo{
    private List<Post> posts;
}
