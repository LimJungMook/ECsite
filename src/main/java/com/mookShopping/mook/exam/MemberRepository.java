//package com.mookShopping.mook.exam;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.springframework.stereotype.Repository;
//
//
//public class MemberRepository {
//    @PersistenceContext
//    EntityManager em;
//
//    public Long save(MemberEntity member) {
//        em.persist(member);
//        return member.getId();
//    }
//
//    public MemberEntity find(Long id) {
//        return em.find(MemberEntity.class, id);
//
//    }
//
//}
