package com.example.lyclebackend.Member.repository;

import com.example.lyclebackend.Member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.walletAddress = :walletAddress")
    Member findByWalletAddress(String walletAddress);
}
