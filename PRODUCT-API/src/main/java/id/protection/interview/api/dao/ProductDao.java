package id.protection.interview.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import id.protection.interview.api.datamodel.Product;

@Repository("productDao")
public interface ProductDao extends JpaRepository<Product,Long>
{

    @Query ("SELECT o FROM Product o WHERE o.deletedStatus = 0 AND o.productUniqueCode = :productUniqueCode ")
	public Product getProduct(@Param("productUniqueCode") String productUniqueCode);
}
