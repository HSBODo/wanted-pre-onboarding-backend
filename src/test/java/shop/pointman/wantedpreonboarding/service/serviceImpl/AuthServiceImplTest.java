package shop.pointman.wantedpreonboarding.service.serviceImpl;


import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.pointman.wantedpreonboarding.service.AuthService;


@SpringBootTest
@Slf4j
class AuthServiceImplTest {

    @Autowired
    AuthService authService ;

    @Test
    void isValidationEmail() {
        String email = "asdasdasdasdasd@scomasdfgha.3sd";
        boolean result = authService.isValidationEmail(email);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void isValidationPassword() {
        String password = "12345678";
        boolean result = authService.isValidationPassword(password);
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void getJWTToken() {
        String email = "asdasdasdasdasd@scomasdfgha.3sd";
        String result = authService.getJWTToken(email);
        log.info("JWT={}",result);
        String data = authService.getDataFromJWTToken(result);
        log.info("email={}",data);
        Assertions.assertThat(data).isEqualTo(email);
    }
}