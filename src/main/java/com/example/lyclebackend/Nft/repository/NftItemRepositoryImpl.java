package com.example.lyclebackend.Nft.repository;


import com.example.lyclebackend.Member.entity.QMember;
import com.example.lyclebackend.Nft.dto.BuyNftItemDto;
import com.example.lyclebackend.Nft.dto.FindNftItemDto;
import com.example.lyclebackend.Nft.dto.NftItemListInDto;
import com.example.lyclebackend.Nft.entity.QNftItem;
import com.example.lyclebackend.Nft.entity.QNftItemLike;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

@Slf4j
public class NftItemRepositoryImpl implements CustomNftItemRepository {
    private final JPAQueryFactory queryFactory;


    QMember m = new QMember("m");
    QNftItemLike l = new QNftItemLike("l");
    QNftItem n = new QNftItem("n");

    public NftItemRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
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
    public List<NftItemListInDto> findListBy(String keyword, String sort, Pageable pageable) {

        return queryFactory
                .select(Projections.bean(NftItemListInDto.class, m.memberId, m.nickname, m.profileImg, n.nftItemId, n.nftItemImg, n.title, n.price, n.viewCnt, n.nftItemLikeList.size().as("likeCnt"), n.createdDate))
                .from(n)
                .leftJoin(n.seller, m)
                .where(searchKeyword(keyword))
                .orderBy(sortCondition(sort))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Boolean findNftItemLikeBy(Long nftItemId, Long memberId) {
        Long nftItemLikeId = queryFactory
                .select(l.nftItemLikeId)
                .from(l)
                .where(l.member.memberId.eq(memberId), l.nftItem.nftItemId.eq(nftItemId))
                .fetchOne();
        return nftItemLikeId != null;
    }

    @Override
    public BuyNftItemDto buyNftItem(Long nftItemId) {
        return queryFactory
                .select(Projections.bean(BuyNftItemDto.class, n.nftItemImg, m.walletAddress.as("sellerWalletAddress")))
                .from(n)
                .leftJoin(n.seller, m)
                .where(n.nftItemId.eq(nftItemId))
                .fetchOne();
    }

    @Override
    public FindNftItemDto findNftItemBy(Long nftItemId, Long memberId) {
        return queryFactory
                .select(Projections.bean(FindNftItemDto.class, n.nftItemId, n.nftItemImg, m.profileImg, m.nickname, m.profileImg, n.title, n.createdDate, n.price, n.viewCnt, n.nftItemLikeList.size().as("likeCnt"), n.content, n.seller.memberId.as("sellerId"), n.seller.memberId.eq(m.memberId).as("isOwner")))
                .from(n)
                .leftJoin(n.seller, m)
                .where(n.nftItemId.eq(nftItemId))
                .fetchOne();
    }


}
