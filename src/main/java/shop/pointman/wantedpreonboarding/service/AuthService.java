package shop.pointman.wantedpreonboarding.service;

import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.vo.BaseVo;
import shop.pointman.wantedpreonboarding.vo.AuthVo;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public interface AuthService {
    boolean isValidationEmail(String email);
    boolean isValidationPassword(String password);
    boolean isJoin(String email);
    boolean isAuth(Account account) throws Exception;
    AuthVo join(Account account) throws Exception;
    AuthVo login(Account account) throws Exception;

}
