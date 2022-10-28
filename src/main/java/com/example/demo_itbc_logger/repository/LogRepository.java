package com.example.demo_itbc_logger.repository;

import com.example.demo_itbc_logger.model.Log;
import com.example.demo_itbc_logger.model.LogType;
import com.example.demo_itbc_logger.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    @Query("select l from Log l where (:dateFrom is null or l.date >= :dateFrom) and " +
            "( (:dateTo is null or l.date <= :dateTo) and  (:message is null or l.message like %:message%)" +
            "and  (:logType is null or l.type = :logType))")
    public List<Log> search(@Param("dateFrom") Date dateFrom,
                            @Param("dateTo")Date dateTo,
                            @Param("message")String message,
                            @Param("logType") LogType logType);


}
