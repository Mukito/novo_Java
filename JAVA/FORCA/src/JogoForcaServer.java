import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class JogoForcaServer {

    private static final int PORT = xxxx;
    private static final String CONTEXT_PATH = "/api/palavra";

    private static final Map<String, List<String>> palavrasPorCategoria = new HashMap<>();
    private static final Random random = new Random();

    static {
        palavrasPorCategoria.put("Tecnologia", Arrays.asList("COMPUTADOR", "PROGRAMACAO", "ALGORITMO", "DESENVOLVEDOR", "INTELIGENCIA"));
        palavrasPorCategoria.put("Animais", Arrays.asList("ELEFANTE", "GIRAFA", "TIGRE", "CACHORRO", "GATO"));
        palavrasPorCategoria.put("Paises", Arrays.asList("BRASIL", "ARGENTINA", "CANADA", "JAPAO", "ALEMANHA"));
        palavrasPorCategoria.put("Frutas", Arrays.asList("ABACAXI", "MORANGO", "BANANA", "LARANJA", "MELANCIA"));
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(XXXX), 0);
        server.createContext(CONTEXT_PATH, new PalavraHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
        System.out.println("Servidor Java da Forca iniciado na porta " + XXXX);
        System.out.println("Acesse http://Endereco para testar a API.");
    }

    static class PalavraHandler implements com.sun.net.httpserver.HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Access-Control-Allow-Origin", "*"); // Permitir CORS para o frontend
            headers.add("Access-Control-Allow-Methods", "GET, OPTIONS");
            headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");

            if ("OPTIONS".equalsIgnoreCase(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(204, -1);
                return;
            }

            String categoria = getRandomCategory();
            String palavra = getRandomWord(categoria);

            // Retorna a palavra e a categoria em formato JSON
            String jsonResponse = String.format("{\"palavra\": \"%s\", \"categoria\": \"%s\"}", palavra, categoria);

            exchange.sendResponseHeaders(200, jsonResponse.length());
            OutputStream os = exchange.getResponseBody();
            os.write(jsonResponse.getBytes());
            os.close();
        }

        private String getRandomCategory() {
            List<String> categorias = new ArrayList<>(palavrasPorCategoria.keySet());
            return categorias.get(random.nextInt(categorias.size()));
        }

        private String getRandomWord(String category) {
            List<String> palavras = palavrasPorCategoria.get(category);
            return palavras.get(random.nextInt(palavras.size()));
        }
    }
}
