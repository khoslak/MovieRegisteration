package ca.sheridancollege.khoslak.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ca.sheridancollege.khoslak.beans.Movie;
import ca.sheridancollege.khoslak.beans.MovieReview;
import ca.sheridancollege.khoslak.repositories.MovieList;
import ca.sheridancollege.khoslak.repositories.MovieReviewList;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class MovieController {
	
	private MovieList movieList;
	private MovieReviewList movieReviewList;
	
	
	

	
	@GetMapping("/")
	public String index(Model model)
	{
		List<Movie> myMovies=movieList.findAll();
		model.addAttribute("movieList",myMovies);
		model.addAttribute("searchOption","");
		model.addAttribute("searchText","");
		return "index";
	}
	
	@PostMapping("/addMovieReview/{id}")
	public String addMovieReview(Model model,@RequestParam String mreview,@RequestParam String name, @PathVariable Long id)
	{
		  Movie movie=movieList.findById(id).get();
		  MovieReview newReview=MovieReview.builder().movieReview(mreview).movie(movie).name(name).build();
		  movieReviewList.save(newReview);
		  List <MovieReview> movieReviews=movieReviewList.findByMovie_Id(id);
		model.addAttribute("movie",movie);
			model.addAttribute("movieReviewList",movieReviews);
				return "viewMovieReview";
	}
	
	@GetMapping("/viewMovieReview/{id}")
	public String viewMovieReview(Model model,@PathVariable Long id)
	{
		 Movie movie=movieList.findById(id).get();
		 List <MovieReview> movieReviews=movieReviewList.findByMovie_Id(id);
		 model.addAttribute("movie",movie);
		 model.addAttribute("movieReviewList",movieReviews);
		 
		return "viewMovieReview";
	}
	
	@GetMapping("/sortMovies/{mySort}//")
	public String simpleSort(Model model,@PathVariable String mySort)
	{
		if(mySort.equals("rating")) {
			model.addAttribute("movieList",movieList.findAllByOrderByRatingDesc());
		}
		else if(mySort.equals("yearReleased")) {
			model.addAttribute("movieList",movieList.findAllByOrderByYearReleasedDesc());
		}
		else if(mySort.equals("genre")) {
			model.addAttribute("movieList",movieList.findAllByOrderByGenre());
		}
		return "index";
	}
	@GetMapping("/sortMovies/{mySort}/{searchText}/{searchOption}")
	public String sortMovies(Model model,@PathVariable String mySort,@PathVariable String searchText,@PathVariable String searchOption)
	{
		
	if(mySort.equals("rating")) {
		
		if(searchOption.equals("genre"))
		{
			model.addAttribute("movieList",movieList.findByGenreOrderByRatingDesc(searchText));
		}
		else if(searchOption.equals("title"))
		{
			model.addAttribute("movieList",movieList.findByTitleOrderByRatingDesc(searchText));
		}
		else if(searchOption.equals("director"))
		{
			model.addAttribute("movieList",movieList.findByDirectorOrderByRatingDesc(searchText));
			
		}
		else if(searchOption.equals("yearReleased"))
		{
			model.addAttribute("movieList",movieList.findByYearReleasedOrderByRatingDesc(Integer.parseInt(searchText)));
		}
		else 
		{
			model.addAttribute("movieList",movieList.findAllByOrderByRatingDesc());
		}
			
			
	}
		else if(mySort.equals("yearReleased")) {
			
			if(searchOption.equals("genre"))
			{
				model.addAttribute("movieList",movieList.findByGenreOrderByYearReleasedDesc(searchText));
			}
			else if(searchOption.equals("title"))
			{
				model.addAttribute("movieList",movieList.findByTitleOrderByYearReleasedDesc(searchText));
			}
			else if(searchOption.equals("director"))
			{
				model.addAttribute("movieList",movieList.findByDirectorOrderByYearReleasedDesc(searchText));
				
			}
			else if(searchOption.equals("yearReleased"))
			{
				model.addAttribute("movieList",movieList.findByYearReleasedOrderByYearReleasedDesc(Integer.parseInt(searchText)));
			}
			else 
			{
				model.addAttribute("movieList",movieList.findAllByOrderByYearReleasedDesc());
			}
			
		}		
		else if(mySort.equals("genre")) {
			
			if(searchOption.equals("genre"))
			{
				model.addAttribute("movieList",movieList.findByGenreOrderByGenreDesc(searchText));
			}
			else if(searchOption.equals("title"))
			{
				model.addAttribute("movieList",movieList.findByTitleOrderByGenreDesc(searchText));
			}
			else if(searchOption.equals("director"))
			{
				model.addAttribute("movieList",movieList.findByDirectorOrderByGenreDesc(searchText));
				
			}
			else if(searchOption.equals("yearReleased"))
			{
				model.addAttribute("movieList",movieList.findByYearReleasedOrderByGenreDesc(Integer.parseInt(searchText)));
			}
			
			else
			{
				model.addAttribute("movieList",movieList.findAllByOrderByGenre());
			}
			
	
		}
			
		return "index";
	}
	
	@GetMapping("/searchMovies")
	public String searchMovies(Model model,@RequestParam String searchOption,@RequestParam String searchText)
	{
		String mySearch=searchOption;
		model.addAttribute("searchOption",mySearch);
		
		String mySearchText=searchText;
		model.addAttribute("searchText",mySearchText);
		
		if(searchOption.equals("title")) {
				
			model.addAttribute("movieList",movieList.findByTitleOrderByRatingDesc(searchText));
	
		}
		else if(searchOption.equals("director")) {
			
			model.addAttribute("movieList",movieList.findByDirectorOrderByRatingDesc(searchText));
		}
		else if(searchOption.equals("yearReleased")) {
		
			model.addAttribute("movieList",movieList.findByYearReleasedOrderByRatingDesc(Integer.parseInt(searchText)));
		}
	
		else if(searchOption.equals("genre")) {
		
			model.addAttribute("movieList",movieList.findByGenreOrderByRatingDesc(searchText));
		}
		
		return "index";
	}

}
