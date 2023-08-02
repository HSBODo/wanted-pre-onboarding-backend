package shop.pointman.wantedpreonboarding.service;

import shop.pointman.wantedpreonboarding.domain.Account;

public interface AuthService {
    boolean isValidationEmail(String email);
    boolean isValidationPassword(String password);
    boolean isJoin(Account account);

    String getJWTToken(String email);

    String getDataFromJWTToken(String token);


}
