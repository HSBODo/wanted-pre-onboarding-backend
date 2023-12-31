package shop.pointman.wantedpreonboarding.vo;

import lombok.Getter;
import lombok.Setter;
import shop.pointman.wantedpreonboarding.domain.Post;

import java.util.List;

@Getter
@Setter
public class PostVo extends BaseVo{
    private String id;
    private String title;
    private String content;
    private String author;
}
