package com.company.inventory.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.company.inventory.model.Product;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.services.IProductService;
import com.company.inventory.util.CategoryExcelExporter;
import com.company.inventory.util.ProductExcelExporter;
import com.company.inventory.util.Util;

import jakarta.servlet.http.HttpServletResponse;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {
	
	private IProductService productService;
	
	

	public ProductRestController(IProductService productService) {
		super();
		this.productService = productService;
	}


	/**
	 * 
	 * @param picture
	 * @param name
	 * @param price
	 * @param account
	 * @param categoryId
	 * @return
	 * @throws IOException
	 */

	@PostMapping("/products")
	public ResponseEntity<ProductResponseRest> save(
				@RequestParam("picture") MultipartFile picture,
				@RequestParam("name") String name,
				@RequestParam("price") int price,
				@RequestParam("account") int account,
				@RequestParam("categoryId") Long categoryId) throws IOException
	{
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setAccount(account);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		
		
		ResponseEntity<ProductResponseRest> response = productService.save(product, categoryId);
		return response;
		
	}
	
	
	/**
	 * ??GET PRODUCT BY ID
	 * @param id
	 * @return
	 */
	@GetMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id){
		ResponseEntity<ProductResponseRest> response = productService.searchById(id);
		return response;
		
	}
	
	
	/**
	 * SEARH LIKE  BY NAME
	 * @param name
	 * @return
	 */
	@GetMapping("/products/filter/{name}")
	public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name){
		ResponseEntity<ProductResponseRest> response = productService.searchByName(name);
		return response;
		
	}
	
	
	/**
	 * DELETE BY ID PRODUCT
	 * @param id
	 * @return
	 */
	@DeleteMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> searchByName(@PathVariable Long id){
		ResponseEntity<ProductResponseRest> response = productService.deleteById(id);
		return response;
	}
	
	/**
	 * ALL PRODUCTS
	 * @return
	 */
	@GetMapping("/products")
	public ResponseEntity<ProductResponseRest> searchAll(){
		ResponseEntity<ProductResponseRest> response = productService.searchAll();
		return response;
	}
	
	/**
	 * UPDATE PRODUCT BY ID
	 * @param picture
	 * @param name
	 * @param price
	 * @param account
	 * @param categoryId
	 * @param productId
	 * @return
	 * @throws IOException
	 */
	@PutMapping("/products/{id}")
	public ResponseEntity<ProductResponseRest> update(
				@RequestParam("picture") MultipartFile picture,
				@RequestParam("name") String name,
				@RequestParam("price") int price,
				@RequestParam("account") int account,
				@RequestParam("categoryId") Long categoryId,
				@PathVariable Long id) throws IOException
	{
		
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setAccount(account);
		product.setPicture(Util.compressZLib(picture.getBytes()));
		
		
		
		ResponseEntity<ProductResponseRest> response = productService.update(product, categoryId, id);
		return response;
		
	}
	
	
	/**
	 * excel to products
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/products/export/excel")
	public void exportProductExcel(HttpServletResponse response) throws IOException {
		
		response.setContentType("application/octet-stream");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=result_products";
		response.setHeader(headerKey, headerValue);
		
		
		ResponseEntity<ProductResponseRest> responseProducts = productService.searchAll();
		
		ProductExcelExporter excelExporter = new ProductExcelExporter(
				responseProducts.getBody().getProductResponse().getProducts()
				);
		
		
		excelExporter.export(response);
		
	}
	
}
