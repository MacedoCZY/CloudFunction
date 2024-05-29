import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws Exception {
        // Cria uma matriz de adjacência que representa o grafo com 10 elementos
        JSONArray grafo = new JSONArray();
        grafo.put(new JSONArray(new int[]{1, 2})); // Vértice 0 conecta a 1 e 2
        grafo.put(new JSONArray(new int[]{0, 3, 4})); // Vértice 1 conecta a 0, 3 e 4
        grafo.put(new JSONArray(new int[]{0, 5})); // Vértice 2 conecta a 0 e 5
        grafo.put(new JSONArray(new int[]{1, 6})); // Vértice 3 conecta a 1 e 6
        grafo.put(new JSONArray(new int[]{1, 7})); // Vértice 4 conecta a 1 e 7
        grafo.put(new JSONArray(new int[]{2, 8, 9})); // Vértice 5 conecta a 2, 8 e 9
        grafo.put(new JSONArray(new int[]{3})); // Vértice 6 conecta a 3
        grafo.put(new JSONArray(new int[]{4})); // Vértice 7 conecta a 4
        grafo.put(new JSONArray(new int[]{5})); // Vértice 8 conecta a 5
        grafo.put(new JSONArray(new int[]{5})); // Vértice 9 conecta a 5

        // Ajusta a origem para índice 0
        int origem = 0;

        // Cria um objeto JSON que representa os dados de entrada
        JSONObject dados = new JSONObject();
        dados.put("origem", origem); // Origem é um índice baseado em 0
        dados.put("grafo", grafo); // Grafo é uma matriz de adjacência

        // Imprime os dados enviados
        System.out.println("Dados enviados: " + dados.toString());

        // Cria uma URL para a função buscaEmLargura no Google Cloud
        URL url = new URL("https://southamerica-east1-hidden-marker-424618-j7.cloudfunctions.net/Busca_em_largura");

        // Cria uma conexão HTTP POST para a URL
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("POST");
        conexao.setRequestProperty("Content-Type", "application/json");
        conexao.setDoOutput(true);

        // Envia os dados para a função buscaEmLargura
        OutputStream saida = conexao.getOutputStream();
        saida.write(dados.toString().getBytes("UTF-8"));
        saida.flush();
        saida.close();

        // Lida com a resposta da função buscaEmLargura
        int status = conexao.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String linha;
            StringBuffer resposta = new StringBuffer();
            while ((linha = entrada.readLine()) != null) {
                resposta.append(linha);
            }
            entrada.close();

            // Imprime a resposta da função buscaEmLargura
            System.out.println("Resposta: " + resposta.toString());
        } else {
            BufferedReader erro = new BufferedReader(new InputStreamReader(conexao.getErrorStream()));
            String linha;
            StringBuffer respostaErro = new StringBuffer();
            while ((linha = erro.readLine()) != null) {
                respostaErro.append(linha);
            }
            erro.close();

            // Imprime a resposta de erro da função buscaEmLargura
            System.err.println("Erro: " + respostaErro.toString());
        }
    }
}

