package com.stayready.Repositories;

import com.stayready.domains.Withdrawal;
import org.springframework.data.repository.CrudRepository;

public interface WithdrawRepository extends CrudRepository<Withdrawal, Long> {
}
