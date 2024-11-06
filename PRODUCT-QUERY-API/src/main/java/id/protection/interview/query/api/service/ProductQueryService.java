package id.protection.interview.query.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.protection.interview.api.dto.ProductDto;
import id.protection.interview.query.api.dao.ProductQueryDao;
import id.protection.interview.query.api.datamodel.Product;

@Service("productQueryService")
public class ProductQueryService {

	@Autowired
	private ProductQueryDao productQueryDao;

	@Transactional
	public ProductDto createProduct(ProductDto productDto) throws Exception {

		Product product = new Product(productDto);

		productQueryDao.save(product);

		return productDto;
	}
	@Transactional
	public ProductDto updateProduct(ProductDto productDto) throws Exception {

		Product product = new Product(productDto);

		productQueryDao.save(product);

		return productDto;
	}
	@Transactional
	public ProductDto deleteProduct(ProductDto productDto) throws Exception {

		Product product = new Product(productDto);

		productQueryDao.save(product);

		return productDto;
	}
	public ProductDto getProduct(String uniqueCode) throws Exception {
		Product product = productQueryDao.findById(uniqueCode);
		
		
		
	}
}
