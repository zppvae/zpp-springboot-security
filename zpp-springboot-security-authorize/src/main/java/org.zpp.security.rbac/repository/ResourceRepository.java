package org.zpp.security.rbac.repository;


import org.springframework.stereotype.Repository;
import org.zpp.security.rbac.domain.Resource;


@Repository
public interface ResourceRepository extends BaseRepository<Resource> {

	Resource findByName(String name);

}
