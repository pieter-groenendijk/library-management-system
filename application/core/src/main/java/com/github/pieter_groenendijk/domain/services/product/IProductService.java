package com.github.pieter_groenendijk.domain.services.product;

import com.github.pieter_groenendijk.dto.CatalogueDTO;
import com.github.pieter_groenendijk.domain.entities.product.ProductCopy;
import com.github.pieter_groenendijk.domain.entities.product.ProductTemplate;
import com.github.pieter_groenendijk.domain.entities.product.Genre;
import java.util.List;
import java.util.Optional;

public interface IProductService {
    ProductTemplate store(ProductTemplate product);
    Optional<ProductTemplate> deleteProductById(long productId);

    Optional <ProductTemplate> retrieveProductById(long productId); // TODO: The fact that you categorized the interface says that it should ideally be split up (SRP, ISP).
    ProductTemplate updateProduct(ProductTemplate product);

    ProductCopy updateProductCopy(ProductCopy productCopy);

    ProductCopy retrieveProductByCopyId(long productCopyId);

    List<ProductCopy> retrieveCatalogue(CatalogueDTO catalogueDTO);

    //Genre
    Genre retrieveGenreById(long id);
    void store(Genre genre);
    void update(long id, Genre genre);
    List<Genre> retrieveGenreList();

}
