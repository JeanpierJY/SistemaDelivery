package integracionAPIs_Adapter;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import config_Singleton.LoggerSistema;

public class ApiPeruAdapter implements IValidacionDni, IValidacionVehicular {
    private final String token = "f59ed71f34fab8364daaa1c5da2a183357a03beef398e43bcecfc6038c79";
    private final HttpClient cliente = HttpClient.newHttpClient();

    @Override
    public String consultarNombrePorDNI(String dni) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.json.pe/api/dni"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"dni\":\"" + dni + "\"}"))
                .build();
            
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                
                if (json.get("success").getAsBoolean()) {
                    return json.getAsJsonObject("data").get("nombres").getAsString();
                } else {
                    LoggerSistema.getInstancia().error("La API rechazo el DNI: " + dni);
                    return null;
                }
            }
            return null;
        } catch (Exception e) {
            LoggerSistema.getInstancia().error("Error de red DNI: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean validarLicenciaMTC(String dni) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.json.pe/api/licencia"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"dni\":\"" + dni + "\"}"))
                .build();
                
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                return json.get("success").getAsBoolean();
            }
            return false;
        } catch (Exception e) {
            LoggerSistema.getInstancia().error("Error de red Licencia: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean validarSoatActivo(String placa) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.json.pe/api/soat"))
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString("{\"placa\":\"" + placa + "\"}"))
                .build();
                
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
                return json.get("success").getAsBoolean();
            }
            return false;
        } catch (Exception e) {
            LoggerSistema.getInstancia().error("Error de red SOAT: " + e.getMessage());
            return false;
        }
    }
}