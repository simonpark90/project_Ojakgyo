package com.ojakgyo.service;

import java.util.List;

import com.ojakgyo.model.Artwork;

public interface ArtworkService {
	
	List<Artwork> findArtworkByTitle(String title);
	
	List<Artwork> FindAllArtworks();

}
	