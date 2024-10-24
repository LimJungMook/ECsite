package com.mookShopping.mook.repository;

import com.mookShopping.mook.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberRepository {

    @Autowired
    EntityManager em;

    public Long saveMember(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where name =: name", Member.class)
                .setParameter("name",name)
                .getResultList();
    }

    public Member login(Member member) {
        return em.createQuery("select m from Member m where name =: name and password =:password",Member.class)
                .setParameter("name", member.getName())
                .setParameter("password", member.getPassword())
                .getSingleResult();
    }
}
