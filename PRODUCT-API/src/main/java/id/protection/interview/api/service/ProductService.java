
package id.protection.interview.api.service;

import java.util.UUID;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import id.protection.interview.api.dao.ProductDao;
import id.protection.interview.api.datamodel.Product;
import id.protection.interview.api.dto.ActivityLogDto;
import id.protection.interview.api.dto.ProductDto;
import id.protection.interview.api.messaging.sender.ActivityLogSender;
import id.protection.interview.api.messaging.sender.ProductDataDeleteSyncSender;
import id.protection.interview.api.messaging.sender.ProductDataSyncSender;
import id.protection.interview.api.messaging.sender.ProductDataUpdateSyncSender;
import id.protection.interview.api.security.dto.ActionUser;
import id.protection.interview.api.util.LogUtil;
import jakarta.transaction.Transactional;

// imports+ 

// imports- 

@Service("productService")
public class ProductService

{

	@Autowired
	private ProductDao productDao;

	@Autowired
	private ActivityLogSender activityLogSender;
	
	@Autowired
	private ProductDataUpdateSyncSender productDataUpdateSyncSender;
	
	@Autowired
	private ProductDataDeleteSyncSender productDataDeleteSyncSender;
	
	@Autowired
	private ProductDataSyncSender productDataSyncSender;

	/*
	 * Method create (Product object) berfungsi untuk melakukan penambahan sebuah
	 * object kedalam database
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil kreasi,lengkap dengan assigned primary key, exception
	 * jika gagal
	 */

	@Transactional
	public ProductDto create(Product product, ActionUser user) throws NullPointerException, JsonProcessingException {
		ActivityLogDto log = new ActivityLogDto();
		UUID transactionId = UUID.randomUUID();
		ObjectMapper objectMapper = new ObjectMapper();

		product.setCreatedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		product.setDeletedStatus(0);
		if (user != null) {
			product.setCreatedBy(user.getUsername());
			LogUtil.initializeActivityLog(log, user);
		}

		product.setUniqueCode(UUID.randomUUID().toString());
		product.setStatus(ProductDto.STATUS_OPEN);

		productDao.saveAndFlush(product);

		ProductDto dto = product.convertDto();

		LogUtil.populateActivityLog(log, "-", objectMapper.writeValueAsString(dto), transactionId, "CREATE Product");

		dto.setResponseTransactionId(transactionId.toString());
		dto.setResponseCode("00");
		dto.setResponseDescription("Success Create Product");

		activityLogSender.sendLog(log);
		
		productDataSyncSender.send(objectMapper.writeValueAsString(dto));

		return dto;
	}

	/*
	 * Method update (Product object) berfungsi untuk melakukan perubahan terhadap
	 * sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin diubah
	 * 
	 * @return object hasil update, exception jika gagal
	 */

	@Transactional
	public ProductDto update(Product product, String preActivityData, ActionUser user)
			throws NullPointerException, JsonProcessingException {

		ActivityLogDto log = new ActivityLogDto();
		UUID transactionId = UUID.randomUUID();
		ObjectMapper objectMapper = new ObjectMapper();

		product.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));

		if (user != null) {
			product.setModifiedBy(user.getUsername());
			LogUtil.initializeActivityLog(log, user);
		}

		productDao.saveAndFlush(product);

		ProductDto dto = product.convertDto();

		LogUtil.populateActivityLog(log, preActivityData, objectMapper.writeValueAsString(dto), transactionId,
				"UPDATE Product");

		activityLogSender.sendLog(log);

		dto.setResponseTransactionId(transactionId.toString());
		dto.setResponseCode("00");
		dto.setResponseDescription("Success Update Product");
		
		
		productDataUpdateSyncSender.send(objectMapper.writeValueAsString(dto));

		return dto;
	}

	/*
	 * Method delete (Object pkey) berfungsi untuk melakukan penghapusan terhadap
	 * sebuah object yang terdapat didalam database
	 * 
	 * @param pkey adalah sebuah object yang merepresentasikan primary key dari
	 * tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun
	 * composite ID
	 * 
	 * @return no return value karena objeknya sendiri sudah dihapus - just for
	 * consistency. Again, exception if failure occured WARNING ! Invalid value for
	 * the returned object, better not use it again in any place
	 */

	@Transactional
	public ProductDto trash(String pkey, ActionUser user) throws NullPointerException, JsonProcessingException {

		ActivityLogDto log = new ActivityLogDto();
		UUID transactionId = UUID.randomUUID();
		ObjectMapper objectMapper = new ObjectMapper();

		Product product = get(pkey);

		ProductDto existingDto = product.convertDto();
		
		String preActivityData = objectMapper.writeValueAsString(product.convertDto());

		productDao.delete(product);

		LogUtil.populateActivityLog(log, preActivityData, "-", transactionId, "TRASH Product");

		if (user != null) {
			log.setRequestTransactionId(user.getRequestTransactionId() == null ? "-" : user.getRequestTransactionId());
		}

		activityLogSender.sendLog(log);

		ProductDto dto = new ProductDto();

		dto.setResponseTransactionId(transactionId.toString());
		dto.setResponseCode("00");
		dto.setResponseDescription("Success Permanent Delete Product");
		
		productDataDeleteSyncSender.send(objectMapper.writeValueAsString(existingDto));

		return dto;
	}

	/*
	 * Method delete (Product object) berfungsi untuk melakukan penghapusan terhadap
	 * sebuah object yang terdapat didalam database
	 * 
	 * @param object adalah sebuah object yang ingin dihapus, isi dari object
	 * tersebut cukup dengan mengisi field-field primary key
	 * 
	 * @return updated object, exception if failed
	 */

	@Transactional
	public ProductDto delete(String pkey, ActionUser user) throws NullPointerException, JsonProcessingException {
		ActivityLogDto log = new ActivityLogDto();
		UUID transactionId = UUID.randomUUID();
		ObjectMapper objectMapper = new ObjectMapper();

		Product product = get(pkey);
		product.setDeletedTime(new java.sql.Timestamp(System.currentTimeMillis()));
		product.setDeletedStatus(1);

		if (user != null) {
			product.setDeletedBy(user.getUsername());
			LogUtil.initializeActivityLog(log, user);
		}

		String preActivityData = objectMapper.writeValueAsString(product.convertDto());

		productDao.saveAndFlush(product);

		ProductDto dto = product.convertDto();

		LogUtil.populateActivityLog(log, preActivityData, "-", transactionId, "DELETE Product");

		dto.setResponseTransactionId(transactionId.toString());
		dto.setResponseCode("00");
		dto.setResponseDescription("Success Delete Product");

		activityLogSender.sendLog(log);
		
		productDataDeleteSyncSender.send(objectMapper.writeValueAsString(dto));

		return dto;
	}

	/*
	 * Method get (Object pkey) berfungsi untuk melakukan retrieval terhadap sebuah
	 * object yang terdapat didalam database
	 * 
	 * @param pkey adalah sebuah object yang merepresentasikan primary key dari
	 * tabel yang bersangkutan. Object tersebut dapat dalam bentuk single ID maupun
	 * composite ID
	 * 
	 * @return Object yang dihasilkan dari proses retrieval, apabila object tidak
	 * ditemukan maka method akan mengembalikan nilai "NULL"
	 */

	@Transactional
	public Product get(String pkey) throws NullPointerException {
		Product object = null;

		object = productDao.getProduct(pkey);

		if (object != null) {
			Hibernate.initialize(object);
		}
		return object;
	}

// class+ 

// class- 
}
