package com.github.pieter_groenendijk.model.DTO;

import com.github.pieter_groenendijk.model.product.MediaType;

public class CatalogueRequestDTO {
    private String searchString;
    private long genreId;
    private boolean onlyAvailableProducts;
    private MediaType mediaType;

    public void setSearchString (String searchString) {this.searchString = searchString;}
    public String getSearchString () {return this.searchString;}

    public void setGenreId (long genreId) {this.genreId = genreId;}
    public long getGenreId () {return this.genreId;}

    public void setOnlyAvailableProducts (boolean onlyAvailableProducts) {this.onlyAvailableProducts = onlyAvailableProducts;}
    public boolean getOnlyAvailableProducts () {return this. onlyAvailableProducts;}

    public void setMediaType(MediaType mediaType) {this.mediaType = mediaType;}
    public MediaType getMediaType () {return this.mediaType;}

}

