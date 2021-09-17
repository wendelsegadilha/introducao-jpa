import dominio.Veiculo;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;

public class ObjetoDesanexado1 {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEtityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo = manager.find(Veiculo.class, 1L);

        tx.commit();
        manager.close();

        //Essa alteração não será sincronizada com o banco
        veiculo.setValor(new BigDecimal(112_000));

        JpaUtil.close();
    }
}
