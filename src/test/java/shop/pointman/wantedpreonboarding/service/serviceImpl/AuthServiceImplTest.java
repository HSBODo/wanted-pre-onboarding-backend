package shop.pointman.wantedpreonboarding.service.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.service.AuthService;

import shop.pointman.wantedpreonboarding.service.SecurityService;
import shop.pointman.wantedpreonboarding.vo.AuthVo;



@SpringBootTest
@Slf4j
class AuthServiceImplTest {

    @Autowired
    AuthService authService ;
    @Autowired
    SecurityService securityService;

    @Test
    @Transactional
    @DisplayName("회원가입 테스트")
    void join() throws Exception{
        AuthVo result = new AuthVo();
        Account account = new Account();
        account.setEmail("test1@test.com");
        account.setPassword("12345678");
        result = authService.join(account);
        Assertions.assertThat(result.getCode()).isEqualTo("00");
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception{
        AuthVo result = new AuthVo();
        Account account = new Account();
        account.setEmail("test@test1.com");
        account.setPassword("12345678");
        result = authService.login(account);
        Assertions.assertThat(result.getCode()).isEqualTo("00");
        log.info("JWT={}",result.getToken());
    }

    @Test
    @DisplayName("이메일 검증 테스트")
    void isValidationEmail() {
        String email = "asdasdasdasdasd@scomasdfgha.3sd";
        boolean result = authService.isValidationEmail(email);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비밀번호 검증 테스트")
    void isValidationPassword() {
        String password = "12345678";
        boolean result = authService.isValidationPassword(password);
        Assertions.assertThat(result).isTrue();
    }



    @Test
    void isAuth() throws Exception{
        Account account = new Account();
        account.setEmail("test@test1.com");
        account.setPassword("12345678");
        boolean result = authService.isAuth(account);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void isJoin() {
        Account account = new Account();
        account.setEmail("test@test1.com");
        account.setPassword("12345678");
        String email = account.getEmail();
        boolean result = authService.isJoin(email);
        Assertions.assertThat(result).isTrue();
    }
}