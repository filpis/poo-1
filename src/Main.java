import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        try {
            HashMap<String, Double> salariosBrutos = lerSalarios("salbruto.dat");

            HashMap<String, Double> descontos = lerDescontos("desc.dat");

            HashMap<String, Double> salariosLiquidos = calcularSalariosLiquidos(salariosBrutos, descontos);

            gravarSalariosLiquidos(salariosLiquidos, "salliq.txt");

            System.out.println("Salários líquidos calculados e gravados com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao processar os arquivos: " + e.getMessage());
        }
    }

    private static HashMap<String, Double> lerSalarios(String arquivo) throws IOException {
        HashMap<String, Double> salarios = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            String cpf = dados[0];
            double salarioBruto = Double.parseDouble(dados[1]);
            salarios.put(cpf, salarioBruto);
        }

        reader.close();
        return salarios;
    }

    private static HashMap<String, Double> lerDescontos(String arquivo) throws IOException {
        HashMap<String, Double> descontos = new HashMap<>();
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        String linha;

        while ((linha = reader.readLine()) != null) {
            String[] dados = linha.split(",");
            String cpf = dados[0];
            double desconto = Double.parseDouble(dados[1]);
            descontos.put(cpf, desconto);
        }

        reader.close();
        return descontos;
    }

    private static HashMap<String, Double> calcularSalariosLiquidos(HashMap<String, Double> salariosBrutos,
                                                                    HashMap<String, Double> descontos) {
        HashMap<String, Double> salariosLiquidos = new HashMap<>();

        for (String cpf : salariosBrutos.keySet()) {
            double salarioBruto = salariosBrutos.get(cpf);
            double desconto = descontos.getOrDefault(cpf, 0.0);
            double salarioLiquido = salarioBruto - desconto;
            salariosLiquidos.put(cpf, salarioLiquido);
        }

        return salariosLiquidos;
    }

    private static void gravarSalariosLiquidos(HashMap<String, Double> salariosLiquidos, String arquivo)
            throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(arquivo));

        for (String cpf : salariosLiquidos.keySet()) {
            double salarioLiquido = salariosLiquidos.get(cpf);
            writer.write(cpf + "," + salarioLiquido);
            writer.newLine();
        }

        writer.close();
    }
}
