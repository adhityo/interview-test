package id.protection.interview.auth.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import id.protection.interview.auth.api.datamodel.Action;

@Repository("actionDao")
public interface ActionDao extends JpaRepository<Action,Integer>{

}
