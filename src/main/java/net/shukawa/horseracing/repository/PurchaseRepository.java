package net.shukawa.horseracing.repository;

import net.shukawa.horseracing.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** 購入情報 */
@Repository
public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {}
