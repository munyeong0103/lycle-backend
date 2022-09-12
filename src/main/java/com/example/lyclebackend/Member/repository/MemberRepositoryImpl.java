package com.example.lyclebackend.Member.repository;

import com.example.lyclebackend.Member.dto.FindMyPageDto;
import com.example.lyclebackend.Member.entity.QMember;
import com.example.lyclebackend.Nft.dto.NftItemListInDto;
import com.example.lyclebackend.Nft.entity.QNftItem;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class MemberRepositoryImpl implements CustomMemberRepository {

    private final JPAQueryFactory queryFactory;

    QMember m = new QMember("m");

    QNftItem n = new QNftItem("n");

    public MemberRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public FindMyPageDto findMemberBy(Long myPageMemberId, Long memberId) {
        return queryFactory
                .select(Projections.bean(FindMyPageDto.class, m.memberId, m.accountName, m.nickname, m.email, m.profileImg, m.walletAddress, m.tokenCnt,  m.memberId.eq(memberId).as("isOwner")))
                .from(m)
                .where(m.memberId.eq(myPageMemberId))
                .fetchOne();

    }

    private BooleanExpression searchKeyword(String keyword){
        if(keyword.length() == 0) {
            return null;
        } else {
            return n.title.like("%" + keyword + "%");
        }
    }

    private OrderSpecifier<?> sortCondition(String sort) {
        if (sort.equals("recent")){
            return n.createdDate.desc();
        } else if (sort.equals("view")) {
            return n.viewCnt.desc();
        } else if (sort.equals("like")) {
            return n.nftItemLikeList.size().desc();
        }
        return n.createdDate.desc();
    }

    @Override
    public List<NftItemListInDto> findListBy(String keyword, String sort, Pageable pageable, Long memberId) {

        return queryFactory
                .select(Projections.bean(NftItemListInDto.class, m.memberId, m.nickname, m.profileImg, n.nftItemId, n.nftItemImg, n.title, n.price, n.viewCnt, n.nftItemLikeList.size().as("likeCnt"), n.createdDate))
                .from(n)
                .leftJoin(n.seller, m)
                .where(n.nftItemLikeList.any().memberId.eq(memberId).and(searchKeyword(keyword)))
                .orderBy(sortCondition(sort))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
