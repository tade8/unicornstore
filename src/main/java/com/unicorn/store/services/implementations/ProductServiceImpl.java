package com.unicorn.store.services.implementations;

import com.unicorn.store.enums.*;
import com.unicorn.store.exceptions.GenericException;
import com.unicorn.store.models.Product;
import com.unicorn.store.repository.ProductRepository;
import com.unicorn.store.requests.ProductRequest;
import com.unicorn.store.services.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private static final String FILE_PATH = "C:/Users/user/Pictures";

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String addProductToProductsList(ProductRequest productRequest, MultipartFile file) throws IOException {
        BigDecimal price = getBigDecimal(productRequest);
        String filePath = FILE_PATH + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        Product product = new Product(
                productRequest.getProductName(),
                productRequest.getDescription(),
                price,
                productRequest.getProductCategory(),
                UserRole.ADMIN, filePath
        );

        if (product.getProductName().isEmpty()
                || product.getDescription().isEmpty()
                || product.getPrice() == null
                || product.getProductCategory() == null
                || product.getFilePath() == null
        ) {
            throw new GenericException("This field cannot be empty");
        }
        productRepository.save(product);
        return "Product added";
    }

    private static BigDecimal getBigDecimal(ProductRequest productRequest) {
        try {
            return BigDecimal.valueOf(Long.parseLong(productRequest.getPrice()));
        }
        catch (GenericException exception) {
            throw new NumberFormatException("The value provided is not a string");
        }
    }

    @Override
    public List<Product> viewAllProducts() {
        try {
            return productRepository.findAll();
        }
        catch (RuntimeException e) {
            throw new GenericException("No products found");
        }
    }

    @Override
    public List<Product> viewProductsByCategory(ProductCategory productCategory) {
        try {
            return productRepository.findAllByProductCategory(productCategory);
        }
        catch (RuntimeException exception) {
            throw new GenericException("There are no products in this category");
        }
    }

    @Override
    public String removeProductFromProductsList(String id) {
        Product product = productRepository.findProductById(id)
                .orElseThrow(()-> new GenericException("This product doesnt exist"));
        productRepository.deleteById(product.getId());
        return "Product has been deleted";
    }

}
