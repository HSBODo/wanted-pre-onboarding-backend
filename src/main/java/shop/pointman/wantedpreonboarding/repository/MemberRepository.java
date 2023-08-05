package shop.pointman.wantedpreonboarding.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import shop.pointman.wantedpreonboarding.domain.Account;
import shop.pointman.wantedpreonboarding.service.SecurityService;


import java.util.List;
import java.util.Optional;


@Transactional
public class MemberRepository {
    private final EntityManager em;
    @Autowired
    SecurityService securityService;

    public MemberRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Account account) throws Exception{
        String password = account.getPassword();

        String encrypt = securityService.encrypt(password);
        account.setPassword(encrypt);

        em.persist(account);
    }


    public Optional<Account> findByAccount(String email) {
        Account findAccount = em.find(Account.class,email);
        return Optional.ofNullable(findAccount);
    }

    public Optional<Account> findByAccount(Account account) throws Exception {

        String email = account.getEmail();
        String password = account.getPassword();
        String decrypt = securityService.encrypt(password);
        List<Account> findKakaoMember = em.createQuery("select m from Account m where email=:email and password=:password", Account.class)
                .setParameter("email", email)
                .setParameter("password", decrypt)
                .getResultList();
        return  findKakaoMember.stream().findAny();
    }
}
