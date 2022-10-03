package hu.bme.aut.retelab2.repository;

import hu.bme.aut.retelab2.domain.Ad;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad feedback){
        feedback.setSecretCode(SecretGenerator.generate());
        return em.merge(feedback);
    }

    @Transactional
    public Ad modify(Ad feedback){
        Ad found = em.find(Ad.class, feedback.getId());
        if(found == null)
            return found;
        if(!found.getSecretCode().equals(feedback.getSecretCode()))
            return null;
        feedback.setCreation(found.getCreation());
        return em.merge(feedback);
    }

    public List<Ad> findByPrice(int min, int max){
        return em.createQuery("SELECT n FROM Ad n WHERE n.price BETWEEN ?1 AND ?2", Ad.class).setParameter(1,min).setParameter(2,max).getResultList();
    }

    @Scheduled(fixedDelay = 6000)
    @Transactional
    public void deleteExpired(){
        List<Ad> expired = em.createQuery("SELECT n FROM Ad n WHERE n.expiration < ?1").setParameter(1, LocalDateTime.now()).getResultList();
        for(Ad ad : expired)
            em.remove(ad);
    }
}

class SecretGenerator {
    private static final char[] CHARS =
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final Random RND = new Random();

    public static String generate() {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < 6; i++)
            sb.append(CHARS[RND.nextInt(CHARS.length)]);
        return sb.toString();
    }
}