package com.example.lyclebackend.Nft.repository;

import com.example.lyclebackend.Nft.entity.QNftItemLike;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class NftItemLikeRepositoryImpl implements CustomNftItemLikeRepository{
    private final JPAQueryFactory queryFactory;

    QNftItemLike l = new QNftItemLike("l");

    public NftItemLikeRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Long findByNftItemAndMember(Long nftItemId, Long memberId) {
        Long nftItemLikeId = queryFactory
                .select(l.nftItemLikeId)
                .from(l)
                .where(l.member.memberId.eq(memberId), l.nftItem.nftItemId.eq(nftItemId))
                .fetchOne();
        return nftItemLikeId;
    }
}
