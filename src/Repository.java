import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static GitHubService gitHubService;

    public static void main(String[] args) {
        // Inicializar el cliente Retrofit
        gitHubService = RetrofitClient.getClient().create(GitHubService.class);

        // Pedir el nombre de usuario por consola
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre de usuario de GitHub: ");
        String username = scanner.nextLine();

        // Obtener información del usuario
        fetchUserInfo(username);

        // Obtener los repositorios del usuario
        fetchUserRepositories(username);
    }

    private static void fetchUserInfo(String username) {
        Call<User> call = gitHubService.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    User user = response.body();
                    System.out.println("Información del usuario: " + user);
                } else {
                    System.out.println("No se pudo obtener la información del usuario.");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    private static void fetchUserRepositories(String username) {
        Call<List<Repository>> call = gitHubService.getUserRepositories(username);
        call.enqueue(new Callback<List<Repository>>() {
            @Override
            public void onResponse(Call<List<Repository>> call, Response<List<Repository>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Repository> repositories = response.body();
                    System.out.println("Repositorios públicos:");
                    for (Repository repo : repositories) {
                        System.out.println(repo.getName() + " - " + repo.getHtmlUrl());
                    }
                } else {
                    System.out.println("No se pudo obtener los repositorios.");
                }
            }

            @Override
            public void onFailure(Call<List<Repository>> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }
}
