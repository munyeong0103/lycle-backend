package com.example.lyclebackend.Item.repository;

import com.example.lyclebackend.Item.dto.FindItemDto;
import com.example.lyclebackend.Item.dto.ItemListInDto;
import com.example.lyclebackend.Item.entity.QItem;
import com.example.lyclebackend.Member.entity.QMember;
import com.example.lyclebackend.Nft.entity.QNftItemLike;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

public class ItemRepositoryImpl implements CustomItemRepository{

    private final JPAQueryFactory queryFactory;


    QMember m = new QMember("m");
    QNftItemLike l = new QNftItemLike("l");
    QItem i = new QItem("i");

    public ItemRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    private BooleanExpression searchKeyword(String keyword){
        if(keyword.length() == 0) {
            return null;
        } else {
            return i.title.like("%" + keyword + "%");
        }
    }

    private OrderSpecifier<?> sortCondition(String sort) {
        if (sort.equals("recent")){
            return i.createdDate.desc();
        } else if (sort.equals("view")) {
            return i.viewCnt.desc();
        }
        return i.createdDate.desc();
    }

    @Override
    public List<ItemListInDto> findListBy(String keyword, String sort, Pageable pageable) {

        return queryFactory
                .select(Projections.bean(ItemListInDto.class, i.itemId, i.itemImg, i.title, i.price, i.viewCnt, i.createdDate))
                .from(i)
                .where(searchKeyword(keyword), i.isDelete.eq(Boolean.FALSE))
                .orderBy(sortCondition(sort))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public FindItemDto findItemBy(Long itemId, Long memberId) {
        return queryFactory
                .select(Projections.bean(FindItemDto.class, i.itemId, i.itemImg, i.title, i.createdDate, i.price, i.viewCnt, i.content))
                .from(i)
                .where(i.itemId.eq(itemId), i.isDelete.eq(Boolean.FALSE))
                .fetchOne();
    }

}
