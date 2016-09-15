package mobile.nuevatel.com.exampleretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import mobile.nuevatel.com.exampleretrofit.adapter.MoviesAdapter;
import mobile.nuevatel.com.exampleretrofit.model.Movie;
import mobile.nuevatel.com.exampleretrofit.model.MoviesResponse;
import mobile.nuevatel.com.exampleretrofit.rest.ApiClient;
import mobile.nuevatel.com.exampleretrofit.rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private final static String API_KEY = "93688c2e9bb6a56b38f9549963a0c3c8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (API_KEY.isEmpty()){
            Toast.makeText(getApplicationContext(), "Please obtain your API Key first from ", Toast.LENGTH_LONG).show();
        }

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<MoviesResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                List<Movie> movies = response.body().getResults();
                Log.d(TAG, "Numbert of movies received:"+ movies.size());
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e(TAG,t.toString());
            }
        });
    }
}
