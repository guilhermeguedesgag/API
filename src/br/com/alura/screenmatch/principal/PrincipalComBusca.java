package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        Scanner leitura = new Scanner(System.in);
        System.out.println("Digite o filme para a busca, digite:");

        String busca;
        busca = leitura.nextLine();



        //para ficar dinâmico, você terá que colocar o t
        // ao  invés de i "http://www.omdbapi.com/?i"
        // colocar o t "http://www.omdbapi.com/?t
        String endereco = ("http://www.omdbapi.com/?t="+busca+"&apikey=767e22b0");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .build();

        // 1º modo do Response
//        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
//                .thenApply(HttpResponse::body)
//                .thenAccept(System.out::println)
//                .join();

        //2º modo do Response
            HttpResponse<String> response = client
             .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        System.out.println(json);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();
//        Titulo meuTitulo = gson.fromJson(json, Titulo.class);


        TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
        System.out.println(meuTituloOmdb);
        Titulo meuTitulo = new Titulo(meuTituloOmdb);
        System.out.println("Título já convertido");
        System.out.println(meuTitulo);



    }
}
