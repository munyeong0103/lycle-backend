package com.example.lyclebackend.Item.dto.repository;

import com.example.lyclebackend.Item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, CustomItemRepository {

    Item findByItemId(Long itemId);

    void deleteByItemId(Long itemId);

}