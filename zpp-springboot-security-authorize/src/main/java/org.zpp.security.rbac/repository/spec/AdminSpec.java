package org.zpp.security.rbac.repository.spec;


import org.zpp.security.rbac.domain.Admin;
import org.zpp.security.rbac.dto.AdminCondition;
import org.zpp.security.rbac.repository.support.MySpecification;
import org.zpp.security.rbac.repository.support.QueryWraper;

public class AdminSpec extends MySpecification<Admin, AdminCondition> {

	public AdminSpec(AdminCondition condition) {
		super(condition);
	}

	@Override
	protected void addCondition(QueryWraper<Admin> queryWraper) {
		addLikeCondition(queryWraper, "username");
	}

}
