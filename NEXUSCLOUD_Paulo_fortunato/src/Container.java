// Objects fornece comparação e hash seguros para os identificadores. RA: 25142855-2
import java.util.Objects;

// Representa Container; sua identidade lógica depende exclusivamente de id. RA: 25142855-2
public class Container {
    // Os atributos privados só são acessados pelos métodos da classe. RA: 25142855-2
    // A identidade é imutável para preservar a consistência de equals e hashCode nas coleções. RA: 25142855-2
    private final String id;
    private String nomeApp;
    private double ramNecessaria;

    // Inicializa os atributos recebidos, conforme o construtor do UML. RA: 25142855-2
    public Container(String id, String nomeApp, double ramNecessaria) {
        this.id = id;
        this.nomeApp = nomeApp;
        this.ramNecessaria = ramNecessaria;
    }

    // Retorna id sem expor diretamente o atributo privado. RA: 25142855-2
    public String getId() {
        return id;
    }

    // Retorna nomeApp sem expor diretamente o atributo privado. RA: 25142855-2
    public String getNomeApp() {
        return nomeApp;
    }

    // Retorna ramNecessaria sem expor diretamente o atributo privado. RA: 25142855-2
    public double getRamNecessaria() {
        return ramNecessaria;
    }

    // Compara referência, tipo e finalmente id; outros atributos não definem igualdade. RA: 25142855-2
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Container outro = (Container) obj;
        return Objects.equals(id, outro.id);
    }

    // Usa a mesma chave de equals para garantir hashes iguais para objetos iguais. RA: 25142855-2
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
