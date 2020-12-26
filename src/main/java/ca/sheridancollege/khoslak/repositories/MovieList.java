package ca.sheridancollege.khoslak.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.khoslak.beans.Movie;


public interface MovieList extends JpaRepository<Movie, Long> {
	
	public List<Movie> findAllByOrderByRatingDesc();
	
	public List<Movie> findAllByOrderByYearReleasedDesc();
	
	public List<Movie> findAllByOrderByGenre();
	
	
	public List<Movie> findByTitleOrderByRatingDesc(String title);
	
	public List<Movie> findByDirectorOrderByRatingDesc(String director);
	
	public List<Movie> findByGenreOrderByRatingDesc(String genre);
	
	public List<Movie> findByTitleContainingOrderByRatingDesc(String keyword);
	
	public List<Movie> findByYearReleasedOrderByRatingDesc(Integer yearReleased);
	
	
	
	
	public List<Movie> findByGenreOrderByYearReleasedDesc(String genre);
	
	public List<Movie> findByTitleOrderByYearReleasedDesc(String title);
	
	public List<Movie> findByDirectorOrderByYearReleasedDesc(String director);
	
	public List<Movie> findByYearReleasedOrderByYearReleasedDesc(Integer yearReleased);
	
	

	public List<Movie> findByGenreOrderByGenreDesc(String genre);
	
	public List<Movie> findByTitleOrderByGenreDesc(String title);
	
	public List<Movie> findByDirectorOrderByGenreDesc(String director);
	
	public List<Movie> findByYearReleasedOrderByGenreDesc(Integer yearReleased);
	
	
	

}
