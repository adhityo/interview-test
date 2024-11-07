package id.protection.interview.query.api.dao;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import id.protection.interview.query.api.datamodel.Product;

@Repository("productQueryDao")
public interface ProductQueryDao extends CassandraRepository<Product, String>{

}
