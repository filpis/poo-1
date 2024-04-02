import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
class GestaoContatos {


    private Map<String, Contato> familia;
    private Map<String, Contato> amigos;
    private Map<String, Contato> profissional;

    public GestaoContatos() {
        familia = new TreeMap<>();
        amigos = new TreeMap<>();
        profissional = new TreeMap<>();
    }

    public void adicionaContato(Contato contato, String tipo) {
        Map<String, Contato> mapa;
        switch (tipo.toLowerCase()) {
            case "familia":
                mapa = familia;
                break;
            case "amigos":
                mapa = amigos;
                break;
            case "profissional":
                mapa = profissional;
                break;
            default:
                throw new IllegalArgumentException("Tipo de contato inválido.");
        }
        mapa.put(contato. getNome(), contato);
    }

    public void eliminaContato(String nome, String tipo) {
        Map<String, Contato> mapa;
        switch (tipo.toLowerCase()) {
            case "familia":
                mapa = familia;
                break;
            case "amigos":
                mapa = amigos;
                break;
            case "profissional":
                mapa = profissional;
                break;
            default:
                throw new IllegalArgumentException("Tipo de contato inválido.");
        }
        if (!mapa.containsKey(nome)) {
            throw new IllegalArgumentException("Contato não encontrado.");
        }
        mapa.remove(nome);
    }

    public void listaContatos(String tipo) {
        Map<String, Contato> mapa;
        switch (tipo.toLowerCase()) {
            case "familia":
                mapa = familia;
                break;
            case "amigos":
                mapa = amigos;
                break;
            case "profissional":
                mapa = profissional;
                break;
            default:
                throw new IllegalArgumentException("Tipo de contato inválido.");
        }
        System.out.println("Contatos do tipo " + tipo + ":");
        for (Contato contato : mapa.values()) {
            System.out.println(contato);
        }
    }

    public Contato maisVelho(String tipo) {
        Map<String, Contato> mapa;
        switch (tipo.toLowerCase()) {
            case "familia":
                mapa = familia;
                break;
            case "amigos":
                mapa = amigos;
                break;
            case "profissional":
                mapa = profissional;
                break;
            default:
                throw new IllegalArgumentException("Tipo de contato inválido.");
        }
        if (mapa.isEmpty()) {
            return null;
        }
        Contato maisVelho = null;
        for (Contato contato : mapa.values()) {
            if (maisVelho == null || contato.getIdade() > maisVelho.getIdade()) {
                maisVelho = contato;
            }
        }
        return maisVelho;
    }

    public Contato maisNovo(String tipo) {
        Map<String, Contato> mapa;
        switch (tipo.toLowerCase()) {
            case "familia":
                mapa = familia;
                break;
            case "amigos":
                mapa = amigos;
                break;
            case "profissional":
                mapa = profissional;
                break;
            default:
                throw new IllegalArgumentException("Tipo de contato inválido.");
        }
        if (mapa.isEmpty()) {
            return null;
        }
        Contato maisNovo = null;
        for (Contato contato : mapa.values()) {
            if (maisNovo == null || contato.getIdade() < maisNovo.getIdade()) {
                maisNovo = contato;
            }
        }
        return maisNovo;
    }
}
