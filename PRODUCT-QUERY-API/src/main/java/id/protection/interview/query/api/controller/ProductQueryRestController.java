package id.protection.interview.query.api.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import id.protection.interview.api.dto.ResponseWrapper;
import id.protection.interview.api.util.Converter;
import id.protection.interview.query.api.datamodel.Product;
import id.protection.interview.query.api.service.ProductQueryService;

@RestController
public class ProductQueryRestController {

	@Autowired
	private ProductQueryService productQueryService;
	
	public ResponseEntity<ResponseWrapper> retrieveProduct(@RequestHeader(name="Authorization") String token, @RequestHeader(name="X-Tenant") String tenant,
			@RequestParam Map<String, String> queryParam) {
				
		Double priceStart = null;
		Double priceEnd = null;
		Double stockStart = null;
		Double stockEnd = null;
		
		List<String> categoryIDList = new ArrayList<>();
		List<String> categoryNameList = new ArrayList<>();
		List<String> skuList = new ArrayList<>();
		List<String> nameList = new ArrayList<>();
		
		for (Map.Entry<String, String> set : queryParam.entrySet()) {
			if (set.getKey().contains("sku")) {
				skuList.add(set.getValue());
			}
			else if (set.getKey().contains("category.name")) {
				categoryNameList.add(set.getValue());
			}
			else if (set.getKey().contains("category.id")) {
				categoryIDList.add(set.getValue());
			}
			else if (set.getKey().contains("name")) {
				nameList.add(set.getValue());
			}
			else if (set.getKey().equalsIgnoreCase("price.start")) {
				priceStart = Converter.parseToDouble(set.getValue()) ;
			}
			else if (set.getKey().equalsIgnoreCase("price.end")) {
				priceEnd = Converter.parseToDouble(set.getValue());
			}
			else if (set.getKey().equalsIgnoreCase("stock.start")) {
				stockStart = Converter.parseToDouble(set.getValue());
			}
			else if (set.getKey().equalsIgnoreCase("stock.end")) {
				stockEnd = Converter.parseToDouble(set.getValue());
			}
		}
		
		Collection<Product> productList = productQueryService.retrieveProductList(priceStart,priceEnd,stockStart,stockEnd);
		
		return null;
		
	}
}
