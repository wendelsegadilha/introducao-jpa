import dominio.Veiculo;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;

public class ObjetoDesanexado2 {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEtityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo1 = manager.find(Veiculo.class, 1L);

        tx.commit();
        manager.close();

        //Essa alteração não será sincronizada com o banco
        veiculo1.setValor(new BigDecimal(112_000));

        manager = JpaUtil.getEtityManager();
        tx = manager.getTransaction();
        tx.begin();

        //reanexando o objeto ao novo EntityManager
        Veiculo veiculo2 = manager.merge(veiculo1);

        System.out.println("Mesmo objeto?" + (veiculo1 == veiculo2));
        tx.commit();
        manager.close();

        JpaUtil.close();
    }
}
