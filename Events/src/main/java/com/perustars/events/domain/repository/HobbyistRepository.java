package com.perustars.events.domain.repository;

import com.perustars.events.domain.model.Hobbyist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HobbyistRepository extends JpaRepository<Hobbyist, Long> {

}
