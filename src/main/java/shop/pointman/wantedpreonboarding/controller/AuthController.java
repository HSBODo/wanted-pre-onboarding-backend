package shop.pointman.wantedpreonboarding.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.service.AuthService;
import shop.pointman.wantedpreonboarding.vo.BaseVo;
import shop.pointman.wantedpreonboarding.vo.LoginVo;

@Controller
@RequestMapping(value = "/member/*")
public class AuthController {
    AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/join")
    @ResponseBody
    public BaseVo join(@ModelAttribute Account account){
        BaseVo response = new BaseVo();
        try{

            String email = account.getEmail();
            String password = account.getPassword();
            String result ="";
            boolean isValidationEmail = authService.isValidationEmail(email);
            if(!isValidationEmail){
                response.setMsg("이메일 양식이 틀립니다!");
                response.setCode("02");
                response.setSuccess(false);
                return response;
            }

            boolean isValidationPassword = authService.isValidationPassword(password);
            if(!isValidationPassword){
                response.setMsg("비밀번호는 8자 이상을 입력해주세요!!");
                response.setCode("03");
                response.setSuccess(false);
                return response;
            }

            if(isValidationEmail && isValidationPassword){
                response.setMsg("회원가입에 성공하였습니다.");
                response.setCode("00");
                response.setSuccess(true);
            }

        }catch (Exception e){
            response.setMsg(e.getMessage());
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginVo login(@ModelAttribute Account account){
        LoginVo response = new LoginVo();
        try{
            String email = account.getEmail();
            String password = account.getPassword();
            String result ="";
            boolean isValidationEmail = authService.isValidationEmail(email);
            if(!isValidationEmail){
                response.setMsg("이메일 양식이 틀립니다!");
                response.setCode("02");
                response.setSuccess(false);
                return response;
            }

            boolean isValidationPassword = authService.isValidationPassword(password);
            if(!isValidationPassword){
                response.setMsg("비밀번호는 8자 이상을 입력해주세요!!");
                response.setCode("03");
                response.setSuccess(false);
                return response;
            }


            boolean isJoin = authService.isJoin(account);
            if(isJoin){
                String token = authService.getJWTToken(account.getEmail());
                response.setMsg("로그인에 성공하셨습니다.");
                response.setCode("00");
                response.setSuccess(true);
                response.setToken(token);
            }else {
                response.setMsg("회원이 아닙니다!");
                response.setCode("04");
                response.setSuccess(false);

            }

        }catch (Exception e){
            response.setMsg(e.getMessage());
            response.setCode("01");
            response.setSuccess(false);
            return response;
        }
        return response;
    }
}
