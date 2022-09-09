package com.example.lyclebackend.Quest.repository;

import com.example.lyclebackend.Quest.entity.Quest;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestRepository extends JpaRepository<Quest, Long>, CustomQuestRepository {

     Quest findByQuestId(Long questId);

     void deleteByQuestId(Long questId);
}
