import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Empregado implements Comparable<Empregado> {
    private String cpf;
    private int idade;
    private double salario;

    public Empregado(String cpf, int idade, double salario) {
        this.cpf = cpf;
        this.idade = idade;
        this.salario = salario;
    }

    public String getCpf() {
        return cpf;
    }

    public int getIdade() {
        return idade;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public int compareTo(Empregado outro) {
        return Integer.compare(this.idade, outro.idade);
    }

    @Override
    public String toString() {
        return "CPF: " + cpf + ", Idade: " + idade + ", Salário: " + salario;
    }
}
