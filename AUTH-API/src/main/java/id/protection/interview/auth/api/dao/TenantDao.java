package id.protection.interview.auth.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.protection.interview.auth.api.datamodel.Tenant;

@Repository("tenantDao")
public interface TenantDao extends JpaRepository<Tenant,Integer>{

    @Query ("SELECT o FROM Tenant o WHERE o.deletedStatus = 0 AND o.tenantName = :tenantName ")
	public Tenant getTenant(@Param("tenantName") String tenantName);
}
