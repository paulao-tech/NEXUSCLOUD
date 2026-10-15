// Objects fornece comparação e hash seguros para os identificadores. RA: 25142855-2
import java.util.Objects;

// Representa Cliente; sua identidade lógica depende exclusivamente de cpf. RA: 25142855-2
public class cliente {
    // Os atributos privados só são acessados pelos métodos da classe. RA: 25142855-2
    // A identidade é imutável para preservar a consistência de equals e hashCode nas coleções. RA: 25142855-2
    private final String cpf;
    private String nome;

    // Inicializa os atributos recebidos, conforme o construtor do UML. RA: 25142855-2
    public cliente(String cpf, String nome) {
        this.cpf = cpf;
        this.nome = nome;
    }

    // Retorna cpf sem expor diretamente o atributo privado. RA: 25142855-2
    public String getCpf() {
        return cpf;
    }

    // Retorna nome sem expor diretamente o atributo privado. RA: 25142855-2
    public String getNome() {
        return nome;
    }

    // Compara referência, tipo e finalmente cpf; outros atributos não definem igualdade. RA: 25142855-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        cliente outro = (cliente) obj;
        return Objects.equals(cpf, outro.cpf);
    }

    // Usa a mesma chave de equals para garantir hashes iguais para objetos iguais. RA: 25142855-2
    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
