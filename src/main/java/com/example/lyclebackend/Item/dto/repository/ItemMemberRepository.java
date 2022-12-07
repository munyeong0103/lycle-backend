package com.example.lyclebackend.Item.dto.repository;

import com.example.lyclebackend.Item.entity.Item;
import com.example.lyclebackend.Item.entity.ItemMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemMemberRepository extends JpaRepository<ItemMember, Long> {

}