package com.example.lyclebackend.Quest.repository;

import com.example.lyclebackend.Quest.dto.FindSuccessQuestListDto;
import com.example.lyclebackend.Quest.entity.Quest;
import com.example.lyclebackend.Quest.entity.SuccessQuest;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SuccessQuestRepository extends JpaRepository<SuccessQuest, Long>, CustomSuccessQuestRepository {

    SuccessQuest findBySuccessQuestId(Long successQuestId);
}