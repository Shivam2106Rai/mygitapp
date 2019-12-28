package com.suunya.subscription.repository;

import com.suunya.subscription.entity.SearchItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<SearchItem,String> {
    @Query(value = "DELETE from user_searh_detail WHERE search_text = :search_text",nativeQuery = true)
    void DeleteUserSeachHistory(@Param(value = "search_text") String search_text);
}
