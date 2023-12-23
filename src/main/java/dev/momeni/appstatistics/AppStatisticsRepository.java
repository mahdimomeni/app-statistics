package dev.momeni.appstatistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface AppStatisticsRepository extends JpaRepository<AppStatistics, Long> {
    @Query(
            value = "SELECT a FROM AppStatistics a WHERE a.reportTime >= :startDate " +
                    "AND a.reportTime <= :endDate AND a.type = :type ORDER BY a.reportTime ASC"
    )
    List<AppStatistics> findAppStatisticsByTypeAndDate(@Param("startDate") Date startDate,
                                                       @Param("endDate") Date endDate,
                                                       @Param("type") int type);
}
