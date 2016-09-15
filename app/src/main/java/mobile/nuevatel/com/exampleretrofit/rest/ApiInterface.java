package mobile.nuevatel.com.exampleretrofit.rest;

import mobile.nuevatel.com.exampleretrofit.model.MoviesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by callisaya on 9/15/16.
 */
public interface ApiInterface{
    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("/movie/{id}")
    Call<MoviesResponse> getMoviesDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
