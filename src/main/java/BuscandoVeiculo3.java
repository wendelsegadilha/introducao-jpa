import dominio.Veiculo;
import util.JpaUtil;

import javax.persistence.EntityManager;

public class BuscandoVeiculo3 {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEtityManager();
        Veiculo veiculo = manager.getReference(Veiculo.class, 1L);
        System.out.println("Buscou o veículo. Será que já executou o SELECT ?");
        System.out.println("Veículo de código " + veiculo.getCodigo() + " é um " + veiculo.getModelo());
        manager.close();
        JpaUtil.close();
    }
}
