package ca.sheridancollege.khoslak.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.khoslak.beans.MovieReview;

public interface MovieReviewList extends JpaRepository<MovieReview, Long> {
	
	public List<MovieReview> findByMovie_Id(Long id);

}
