package com.mookShopping.mook.service;

import com.mookShopping.mook.domain.Member;
import com.mookShopping.mook.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {
    @Autowired
    MemberRepository memberRepository;

    @Transactional
    public Long saveMember(Member member) {
        duplicateMember(member.getName());
        return memberRepository.saveMember(member);
    }

    public Member findOneMember(Long id) {
        return memberRepository.findOne(id);
    }

    public Member login(Member member) {
        return memberRepository.login(member);
    }

    private void duplicateMember(String name) {
        List<Member> existMember = memberRepository.findByName(name);
        if (!existMember.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
}
