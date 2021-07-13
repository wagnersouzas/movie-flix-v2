package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.dto.UserReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReviewServices {

    private ReviewRepository repository;
    private MovieRepository movieRepository;

    private AuthService authService;

    @Autowired
    public ReviewServices(ReviewRepository repository,
                          MovieRepository movieRepository,
                          AuthService authService) {
        this.repository = repository;
        this.movieRepository = movieRepository;
        this.authService = authService;
    }

    @Transactional
    public ReviewDTO insert(ReviewDTO reviewDTO) {
        User user = authService.authenticated();
        Optional<Movie> movieOptional = movieRepository.findById(reviewDTO.getMovieId());
        Movie movie = new Movie();

        if(!movieOptional.isPresent()){
            throw new ResourceNotFoundException("Movie is not present!");
        } else {
            movie = movieOptional.get();
        }

        Review review = new Review(null, reviewDTO.getText());
        review.setUser(user);

        repository.save(review);

        movie.getReviews().add(review);

        movieRepository.save(movie);

        reviewDTO.setId(review.getId());
        reviewDTO.setUser(new UserReviewDTO(user));

        return reviewDTO;
    }
}
