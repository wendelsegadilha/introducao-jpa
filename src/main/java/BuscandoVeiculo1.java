import dominio.Veiculo;
import util.JpaUtil;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;

public class BuscandoVeiculo1 {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEtityManager();

        Veiculo veiculo = manager.find(Veiculo.class, 1L);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataCadastro = formatter.format(veiculo.getDataCadastro());
        System.out.println("Veículo de código " + veiculo.getCodigo() + " é um " + veiculo.getModelo() + " data de cadastro: " + dataCadastro);
        manager.close();
        JpaUtil.close();
    }
}
