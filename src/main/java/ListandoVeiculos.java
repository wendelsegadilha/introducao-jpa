import dominio.Veiculo;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class ListandoVeiculos {

    public static void main(String[] args) {

        EntityManager manager = JpaUtil.getEtityManager();
        Query query = manager.createQuery("select v from Veiculo v");
        List<Veiculo> veiculos = query.getResultList();

        for (Veiculo veiculo : veiculos) {
            System.out.println(veiculo.getCodigo() + " - "
                    + veiculo.getFabricante() + " "
                    + veiculo.getModelo() + ", ano "
                    + veiculo.getAnoFabricacao() + "/"
                    + veiculo.getAnoModelo() + " por "
                    + "R$" + veiculo.getValor());
        }

        System.out.println("Usando Lambda para percorrer a lista de veículos");

        veiculos.stream().forEach(v -> System.out.println(v.getCodigo() + " - "
                + v.getFabricante() + " "
                + v.getModelo() + ", ano "
                + v.getAnoFabricacao() + "/"
                + v.getAnoModelo() + " por "
                + "R$" + v.getValor()));


        manager.close();
        JpaUtil.close();
    }
}
