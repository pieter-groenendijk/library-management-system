package com.github.pieter_groenendijk.repositories.product;

import com.github.pieter_groenendijk.domain.entities.product.MediaType;
import com.github.pieter_groenendijk.domain.entities.product.ProductCopy;
import com.github.pieter_groenendijk.domain.entities.product.ProductTemplate;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    ProductTemplate store(ProductTemplate product);

    Optional<ProductTemplate> deleteProductById(long productId);

    Optional<ProductTemplate> retrieveProductById(long productId);

    ProductTemplate updateProduct(ProductTemplate product);

    Optional<ProductCopy> retrieveProductCopyById(long productCopyId);

    ProductCopy updateProductCopy(ProductCopy productCopy);

    List<ProductCopy> retrieveCatalogue(String searchString, long genreId, boolean onlyAvailableProducts, MediaType mediaType);
}
