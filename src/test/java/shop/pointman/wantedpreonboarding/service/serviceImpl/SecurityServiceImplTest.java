package shop.pointman.wantedpreonboarding.service.serviceImpl;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.service.SecurityService;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class SecurityServiceImplTest {
    @Autowired
    private SecurityService securityService;

    @Test
    void getJWTToken_getDataFromJWTToken() {
        String email = "asdasdasdasdasd@scomasdfgha.3sd";
        String result = securityService.getJWTToken(email);
        log.info("JWT={}",result);
        String data = securityService.getDataFromJWTToken(result);
        log.info("email={}",data);
        Assertions.assertThat(data).isEqualTo(email);
    }

    @Test
    void encrypt_decrypt() throws Exception{
        String password = "asdzxczxczxv";
        String encrypt = securityService.encrypt(password);
        log.info("encrypt={}",encrypt);
        String decrypt = securityService.decrypt(encrypt);
        log.info("decrypt={}",decrypt);
        Assertions.assertThat(decrypt).isEqualTo(password);

    }
}