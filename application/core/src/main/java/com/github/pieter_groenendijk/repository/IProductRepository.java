package com.github.pieter_groenendijk.repository;

import com.github.pieter_groenendijk.model.product.ProductCopy;
import com.github.pieter_groenendijk.model.product.ProductTemplate;
import com.github.pieter_groenendijk.model.product.MediaType;
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
