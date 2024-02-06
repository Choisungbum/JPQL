package jpql;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            // TypedQuery VS Query
//            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);// TypedQuery : 반환값이 명확함
//            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);// typeQuery : 반환값이 명확함
//            Query query3 = em.createQuery("select m.username,m.age from Member m");// Query : 반환값이 명확하지 않을때 (String, int)

            // query.getResultList() : 값이 여러개가 있을경우
//            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);// TypedQuery : 반환값이 명확함
//            List<Member> resultList = query.getResultList();
//
//            for (Member member1 : resultList) {
//                System.out.println("member1 = " + member1);
//            }

            // query.getSingleResult(); : 값이 단 하나인 경우
//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 10L", Member.class);
//            Member result = query.getSingleResult();
//            System.out.println("result = " + result);

//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);
//            query.setParameter("username", "member1");
//            query.getSingleResult();
//            System.out.println("query = " + query);

            // 파라미터 바인딩
//            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
//                    .setParameter("username", "member1")
//                    .getSingleResult();
//            System.out.println("result = " + result);


            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : " + e);
            tx.rollback();
        } finally {
            // 실제 동장 코드 작성
            em.close();
        }

        // 실제 애플리케이션 종료시 매니저 팩토리 닫기
        emf.close();

    }
}
