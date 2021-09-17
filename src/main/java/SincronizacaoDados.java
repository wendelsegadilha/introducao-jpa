import dominio.Veiculo;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;

public class SincronizacaoDados {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEtityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo = manager.find(Veiculo.class, 1L);

        System.out.println("Valor atual: " + veiculo.getValor());
        veiculo.setValor(veiculo.getValor().add(new BigDecimal(500)));

        manager.flush();//Força a sincrozinação dos dados antes do commit ser realizado

        System.out.println("Novo valor: " + veiculo.getValor());

        tx.commit();
        manager.close();
        JpaUtil.close();
    }
}
