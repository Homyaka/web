package com.example.web.reposit;

import com.example.web.models.CurrentUser;
import org.springframework.data.repository.CrudRepository;

public interface CurrentUserRepository extends CrudRepository<CurrentUser,Long> {
}
