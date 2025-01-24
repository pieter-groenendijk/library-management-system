package com.github.pieter_groenendijk.presentation.controller;

import com.github.pieter_groenendijk.dto.catalogue.CatalogueDTO;
import com.github.pieter_groenendijk.domain.entities.product.ProductCopy;
import com.github.pieter_groenendijk.domain.services.product.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.hibernate.HibernateException;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    private IProductService SERVICE;

    private ProductController(
        IProductService service
    ) {
        this.SERVICE = service;
    }

    @Operation(summary = "Get all ProductCopy details by product", description = "Get product details by product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "No product found for the given productId")
    })
    @GetMapping("/{productCopyId}")
    public ResponseEntity<ProductCopy> retrieveProductByCopyId(@PathVariable("productCopyId") long productCopyId) {
        try {
            ProductCopy productCopy = SERVICE.retrieveProductByCopyId(productCopyId);
            return new ResponseEntity<>(productCopy, HttpStatus.OK);
        } catch (HibernateException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Get (filtered) catalogue", description = "Get a filtered overview of all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "At least 1 product found"),
            @ApiResponse(responseCode = "404", description = "No product found")
    })
    @PostMapping("/catalogue")
    public ResponseEntity<?> retrieveCatalogue(@RequestBody CatalogueDTO catalogueDTO) {
        List<ProductCopy> catalogue = SERVICE.retrieveCatalogue(catalogueDTO);
        if (catalogue.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.ok(catalogue);
        }
    }
}
