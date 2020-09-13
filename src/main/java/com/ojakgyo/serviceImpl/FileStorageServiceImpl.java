package com.ojakgyo.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ojakgyo.config.FileStorageProperties;
import com.ojakgyo.exception.FileStorageException;
import com.ojakgyo.model.dto.ArtworkDto;
import com.ojakgyo.model.dto.ImageDto;
import com.ojakgyo.model.entity.Artwork;
import com.ojakgyo.model.entity.Image;
import com.ojakgyo.repository.ArtworkRepository;
import com.ojakgyo.repository.ImageRepository;
import com.ojakgyo.repository.MemberRepository;
import com.ojakgyo.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

	private final Path fileStorageLocation;

	private final MemberRepository memberRepository;

	private final ArtworkRepository artworkRepository;

	private final ImageRepository imageRepository;
	
	private final ModelMapper modelMapper;

	public FileStorageServiceImpl(FileStorageProperties fileStorageProperties, final MemberRepository memberRepository,
			final ArtworkRepository artworkRepository, final ImageRepository imageRepository, final ModelMapper modelMapper) {

		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
		this.memberRepository = memberRepository;
		this.artworkRepository = artworkRepository;
		this.imageRepository = imageRepository;
		this.modelMapper = modelMapper;

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new FileStorageException("Could no create the directory where~", ex);
		}

	}

	public String storeFile(MultipartFile file) {

		Random rnd = new Random();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			if (rnd.nextBoolean()) {
				buf.append((char) ((int) (rnd.nextInt(26)) + 97));
			} else {
				buf.append((rnd.nextInt(10)));
			}
		}
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		fileName = buf + fileName;

		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry" + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new FileStorageException("Could not store" + fileName + "Please try again!", ex);
		}
	}

	@Override
	public ImageDto uploadArtwork(String email, ArtworkDto artworkDto, ImageDto imageDto) {

		Artwork artwork = modelMapper.map(artworkDto, Artwork.class);
		artwork.setMember(memberRepository.findByEmail(email));
		
		Image image = modelMapper.map(imageDto, Image.class);
		image.setArtwork(artworkRepository.save(artwork));
		
		return modelMapper.map(imageRepository.save(image), ImageDto.class);
	}

}
