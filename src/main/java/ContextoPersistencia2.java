import dominio.Veiculo;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class ContextoPersistencia2 {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEtityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Veiculo veiculo1 = manager.find(Veiculo.class, 2L);
        System.out.println("Buscou o veículo pela primeira vez...");

        System.out.println("Gerenciado? " + manager.contains(veiculo1));
        manager.detach(veiculo1);
        System.out.println("E agora? " + manager.contains(veiculo1));

        Veiculo veiculo2 = manager.find(Veiculo.class, 2L);
        System.out.println("Buscou o veículo pela segunda vez...");

        System.out.println("Mesmo veículo? " + (veiculo1 == veiculo2));

        manager.close();
        JpaUtil.close();
    }
}
