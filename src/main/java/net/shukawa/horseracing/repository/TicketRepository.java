package net.shukawa.horseracing.repository;

import net.shukawa.horseracing.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** 馬券情報 */
@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Long> {}
