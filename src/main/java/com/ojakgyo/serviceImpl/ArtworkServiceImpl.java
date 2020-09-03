package com.ojakgyo.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ojakgyo.model.Artwork;
import com.ojakgyo.repository.ArtworkRepository;
import com.ojakgyo.service.ArtworkService;

@Service
public class ArtworkServiceImpl implements ArtworkService {

	private final ArtworkRepository repository;

	ArtworkServiceImpl(ArtworkRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Artwork> findArtworkByTitle(String title) {
		List<Artwork> list = repository.findArtworkByTitleContaining(title);
		if (list.size() != 0)
			return list;
		else {
			return null;
		}
	}

	@Override
	public List<Artwork> FindAllArtworks() {
		List<Artwork> list = repository.findAll();
		if (list.size() != 0)
			return list;
		else {
			return null; // null인 경우 exception 발생 at controller

		}
	}

}
