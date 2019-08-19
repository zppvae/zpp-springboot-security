package org.zpp.security.rbac.repository;


import org.springframework.stereotype.Repository;
import org.zpp.security.rbac.domain.Admin;


@Repository
public interface AdminRepository extends BaseRepository<Admin> {

	Admin findByUsername(String username);

}
