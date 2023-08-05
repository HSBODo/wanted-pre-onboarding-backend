package shop.pointman.wantedpreonboarding.service;

public interface SecurityService {
    String encrypt(String password) throws Exception;
    String decrypt(String password) throws  Exception;
    void  AES256Util() throws Exception;
    String getJWTToken(String email);
    String getDataFromJWTToken(String token);
}
