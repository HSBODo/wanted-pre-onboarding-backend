package shop.pointman.wantedpreonboarding.service.serviceImpl;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.repository.MemberRepository;
import shop.pointman.wantedpreonboarding.service.AuthService;

import shop.pointman.wantedpreonboarding.service.SecurityService;
import shop.pointman.wantedpreonboarding.vo.AuthVo;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {



    private MemberRepository memberRepository;
    private SecurityService securityService;

    public AuthServiceImpl(MemberRepository memberRepository, SecurityService securityService) {
        this.memberRepository = memberRepository;
        this.securityService = securityService;
    }

    /**
     * 회원가입
     * @param account : 계정
     * @return authVo : 결과
     * */
    @Override
    public AuthVo join(Account account) throws Exception{
        AuthVo response = new AuthVo();
        String email = account.getEmail();
        String password = account.getPassword();

        boolean isValidationEmail = isValidationEmail(email);
        if(!isValidationEmail){
            response.setMsg("이메일 양식이 틀립니다!");
            response.setCode("02");
            response.setSuccess(false);
            return response;
        }

        boolean isValidationPassword = isValidationPassword(password);
        if(!isValidationPassword){
            response.setMsg("비밀번호는 8자 이상을 입력해주세요!!");
            response.setCode("03");
            response.setSuccess(false);
            return response;
        }
        boolean join = isJoin(email);

        if(!join){
            memberRepository.save(account);
            response.setMsg("회원가입에 성공하였습니다.");
            response.setCode("00");
            response.setSuccess(true);
        }else {
            response.setMsg("이미 가입된 이메일입니다.");
            response.setCode("05");
            response.setSuccess(false);
        }
        return response;
    }

    /**
     * 로그인
     * @param account : 계정
     * @return authVo : 결과
     * */
    @Override
    public AuthVo login(Account account) throws Exception{
        AuthVo response = new AuthVo();
        String email = account.getEmail();
        String password = account.getPassword();

        boolean isValidationEmail = isValidationEmail(email);
        if(!isValidationEmail){
            response.setMsg("이메일 양식이 틀립니다!");
            response.setCode("02");
            response.setSuccess(false);
            return response;
        }

        boolean isValidationPassword = isValidationPassword(password);
        if(!isValidationPassword){
            response.setMsg("비밀번호는 8자 이상을 입력해주세요!!");
            response.setCode("03");
            response.setSuccess(false);
            return response;
        }


        boolean isAuth = isAuth(account);
        if(isAuth){
            String token = securityService.getJWTToken(account.getEmail());
            response.setMsg("로그인에 성공하셨습니다.");
            response.setCode("00");
            response.setSuccess(true);
            response.setToken(token);
        }else {
            response.setMsg("로그인에 실패하였습니다.");
            response.setCode("04");
            response.setSuccess(false);

        }
        return response;
    }

    /**
     * email 형식 검사
     * @param email : 이메일
     * @return boolean : 이메일 형식 여부
     * */
    @Override
    public boolean isValidationEmail(String email) {
        boolean validation = false;
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()) {
            validation = true;
        }

        return validation;
    }
    /**
     * password 길이 검사
     * @param password : 비밀번호
     * @return boolean : 비밀번호 길이 검사
     * */
    @Override
    public boolean isValidationPassword(String password) {
        boolean validation = false;

        if (password.length()>=8){
            validation = true;
        }
        return validation;
    }

    /**
     * 이메일 중복확인
     * @param email : 계정
     * @return boolean : 결과
     * */
    @Override
    public boolean isJoin(String email) {
        Optional<Account> mayBeAccount = memberRepository.findByAccount(email);
        if(mayBeAccount.isEmpty()){
            return false;
        }
        return true;
    }
    /**
     * email,password 검증
     * @param account : 계정
     * @return boolean : 결과
     * */
    @Override
    public boolean isAuth(Account account) throws Exception{

        Optional<Account> mayBeAccount = memberRepository.findByAccount(account);
        if(mayBeAccount.isEmpty()){
            return false;
        }
        return true;
    }






}
