package shop.pointman.wantedpreonboarding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.service.AuthService;
import shop.pointman.wantedpreonboarding.vo.BaseVo;
import shop.pointman.wantedpreonboarding.vo.AuthVo;

@Controller
@RequestMapping(value = "/member/*")
public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/join")
    @ResponseBody
    public AuthVo join(@ModelAttribute Account account){
        AuthVo response = new AuthVo();
        try{
            response = authService.join(account);
        }catch (Exception e){
            response.setMsg("회원가입에 실패하였습니다.");
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }

    @PostMapping("/login")
    @ResponseBody
    public AuthVo login(@ModelAttribute Account account){
        AuthVo response = new AuthVo();
        try{
            response = authService.login(account);
        }catch (Exception e){
            response.setMsg("로그인에 실패하였습니다.");
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }
}
