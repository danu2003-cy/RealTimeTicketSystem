package org.ticketsystem.ticeketsystem_be.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.ticketsystem.ticeketsystem_be.Entity.Vendor;

@Repository
@EnableJpaRepositories
public interface VendorRepo extends JpaRepository<Vendor, Integer> {
}
