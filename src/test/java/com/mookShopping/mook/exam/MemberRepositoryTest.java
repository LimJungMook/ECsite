//package com.mookShopping.mook.exam;
//
//import jakarta.transaction.Transactional;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//@SpringBootTest
//@ExtendWith(SpringExtension.class)
//class MemberRepositoryTest {
//    @Autowired
//    MemberRepository memberRepository;
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void save() {
//        //given
//        MemberEntity member = new MemberEntity();
//        member.setUsername("mook");
//
//        //when
//        Long savedMember = memberRepository.save(member);
//
//        //then
//        Assertions.assertThat(1L).isEqualTo(savedMember);
//
//    }
//
//    @Test
//    @Transactional
//    @Rollback(value = false)
//    void findAll() {
//        //given
//        MemberEntity member = new MemberEntity();
//        member.setUsername("mook");
//
//        //when
//        Long memberID = memberRepository.save(member);
//        MemberEntity savedMember = memberRepository.find(memberID);
//
//        //then
//        Assertions.assertThat(memberID).isEqualTo(savedMember.getId());
//        Assertions.assertThat(member.getUsername()).isEqualTo(savedMember.getUsername());
//        Assertions.assertThat(member).isEqualTo(savedMember);
//    }
//
//
//}