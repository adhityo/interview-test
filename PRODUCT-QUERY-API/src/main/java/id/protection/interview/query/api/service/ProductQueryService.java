package id.protection.interview.query.api.service;

import java.util.Optional;

import org.jgroups.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import id.protection.interview.api.dto.ActionResult;
import id.protection.interview.api.dto.ProductDto;
import id.protection.interview.query.api.dao.ProductQueryDao;
import id.protection.interview.query.api.datamodel.Product;

@Service("productQueryService")
public class ProductQueryService {

	@Autowired
	private ProductQueryDao productQueryDao;

	@Transactional
	public ProductDto createProduct(ProductDto productDto) throws IllegalArgumentException, DataAccessException {

		Product product = new Product();
		product.load(productDto);
		
		Optional<Product> existingProduct = productQueryDao.findById(productDto.getUniqueCode());

		if (existingProduct.isEmpty()) {
			productQueryDao.save(product);
			
			productDto.setResponseCode("00");
			productDto.setResponseDescription("Product Succesfully Created");
		}
		else {
			productDto.setResponseCode("01");
			productDto.setResponseDescription("Product Already Existed");
		}


		return productDto;
	}
	@Transactional
	public ProductDto updateProduct(ProductDto productDto) throws IllegalArgumentException, DataAccessException  {
		
		Optional<Product> existingProduct = productQueryDao.findById(productDto.getUniqueCode());

		if (existingProduct.isPresent()) {
			Integer existingVersion = existingProduct.get().getVersion();
			
			if (!existingVersion.equals(productDto.getVersion())){
				Product product = existingProduct.get();
				product.load(productDto);						
			
				productQueryDao.save(product);
				
				productDto.setResponseCode("00");
				productDto.setResponseDescription("Product Data Succesfully Synchronized");
			}
			else {
				productDto.setResponseCode("01");
				productDto.setResponseDescription("Identical Product Version Already Existed");
			}
		}
		

		return productDto;
	}
	@Transactional
	public ProductDto deleteProduct(ProductDto productDto) throws IllegalArgumentException, DataAccessException  {

		Optional<Product> existingProduct = productQueryDao.findById(productDto.getUniqueCode());

		if (existingProduct.isPresent()) {
			
			productQueryDao.delete(existingProduct.get());

			productDto.setResponseCode("00");
			productDto.setResponseDescription("Product Successfully Deleted");
		}
		else {
			productDto.setResponseCode("01");
			productDto.setResponseDescription("Product Not Exist");
		}


		return productDto;
	}
	public ProductDto getProduct(String uniqueCode) throws IllegalArgumentException, DataAccessException  {
		
		ProductDto dto = new ProductDto();
		Optional<Product> product = productQueryDao.findById(uniqueCode);
		
		
		if (product.isPresent()) {
			dto = product.get().convertDto();
			dto.setResponseCode("00");
			dto.setResponseDescription("Product Succesfully Retrieved");
			dto.setResponseTransactionId(UUID.randomUUID().toString());
		}
		
		return dto;
				
	}
	public ActionResult retrieveProductList (Double priceStart, Double priceEnd, Double stockStart, Double stockEnd) throws IllegalArgumentException, DataAccessException {
		ActionResult result = new ActionResult();
		
		
		
		return result;
	}
}
