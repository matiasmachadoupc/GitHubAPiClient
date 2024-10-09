

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String username = "octocat"; // Cambia esto por el usuario deseado
        GitHubService gitHubService = createService();

        // Obtener repositorios públicos del usuario
        try {
            List<Repository> repositories = gitHubService.listRepositories(username).execute().body();
            if (repositories != null) {
                for (Repository repository : repositories) {
                    System.out.println("Repository Name: " + repository.getName());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static GitHubService createService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        return retrofit.create(GitHubService.class);
    }

    interface GitHubService {
        @GET("users/{user}/repos")
        Call<List<Repository>> listRepositories(@Path("user") String user);
    }

    // Clase para almacenar datos de repositorios
    public static class Repository {
        private String name;

        public String getName() {
            return name;
        }
    }
}
