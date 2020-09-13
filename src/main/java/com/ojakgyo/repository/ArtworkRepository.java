package com.ojakgyo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ojakgyo.model.entity.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {
	public List<Artwork> findArtworkByTitleContaining(String title);

	@Query(value = "SELECT * FROM Artwork WHERE artwork_title LIKE %?1% OR artwork_content LIKE %?1%", nativeQuery = true)

	public List<Artwork> findBySearchKeyWord(@Param("searchKeyWord") String searchKeyWord);

	@Query(value = "select * from Artwork a left outer join Member m on a.member_id = m.member_id where m.member_id = :memberId", nativeQuery = true)
	public List<Artwork> findArtworkByMemberId(@Param("memberId") Long id);

	@Query(value = "select * from Artwork A, Member B where A.member_id = B.member_id", nativeQuery = true)
	public Artwork findEmail(Long id);

	@Query(value = "select * from Artwork a inner join Image i on a.artwork_id = i.artwork_id where i.image_name = :imageName", nativeQuery = true)
	public Artwork findArtworkByImageName(@Param("imageName") String imageName);

	@Query(value = "select * from Artwork a inner join Member m on a.member_id =m.member_id where a.artwork_id =:id", nativeQuery = true)
	public Artwork findEmailByArtwrokId(Long id);

	@Query(value = "select * from Artwork a inner join Member m on a.member_id = m.member_id where m.member_email =:email", nativeQuery = true)
	public List<Artwork> findByEmail(String email);
}
