import dominio.Veiculo;
import dominio.VeiculoId;
import util.JpaUtil;

import javax.persistence.EntityManager;

public class BuscandoVeiculo1 {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEtityManager();

        VeiculoId codigo = new VeiculoId("ABC-456","Bacabal");
        Veiculo veiculo = manager.find(Veiculo.class, codigo);
        
        System.out.println("Placa: " + veiculo.getCodigo().getPlaca());
        System.out.println("Cidade: " + veiculo.getCodigo().getCidade());
        System.out.println("Fabricante: " + veiculo.getFabricante());
        System.out.println("Modelo: " + veiculo.getModelo());

        manager.close();
        JpaUtil.close();
    }
}
