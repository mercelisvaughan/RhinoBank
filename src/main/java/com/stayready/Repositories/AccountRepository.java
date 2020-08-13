package com.stayready.Repositories;

import com.stayready.domains.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
