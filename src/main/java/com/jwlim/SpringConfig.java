package com.jwlim;

import com.jwlim.repository.JdbcMemberRepository;
import com.jwlim.repository.JdbcTemplateMemberRepository;
import com.jwlim.repository.MemberRepository;
import com.jwlim.repository.MemoryMemberRepository;
import com.jwlim.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
//        return new JdbcMemberRepository(dataSource);
        return new JdbcTemplateMemberRepository(dataSource);
//        return new MemoryMemberRepository();
    }
}
