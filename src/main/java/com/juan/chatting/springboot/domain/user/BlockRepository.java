package com.juan.chatting.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Gatsjy
 * @since 2020-10-03
 */
public interface BlockRepository extends JpaRepository<Block, Long> {
}
