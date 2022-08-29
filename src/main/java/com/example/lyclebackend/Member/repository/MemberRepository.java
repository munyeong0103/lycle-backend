package com.example.lyclebackend.Member.repository;

import com.example.lyclebackend.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.accountName = :accountName")
    Member findByAccountName(String accountName);

    Boolean existsByAccountName(String accountName);

    Boolean existsByNickname(String nickname);

    Boolean existsByEmail(String email);

    Boolean existsByWalletAddress(String walletAddress);

    @Query("select m.memberId from Member m where m.accountName = :accountName")
    Long findMemberIdByAccountName(String accountName);

    Member findByMemberId(Long memberId);
}
