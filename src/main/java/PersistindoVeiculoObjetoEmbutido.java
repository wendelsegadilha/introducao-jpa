import dominio.Propietario;
import dominio.TipoCombustivel;
import dominio.Veiculo;
import util.JpaUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PersistindoVeiculoObjetoEmbutido {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEtityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Propietario propietario = new Propietario();
        propietario.setNome("Wendel Segadilha");
        propietario.setTelefone("(98) 98125-6478");

        Veiculo veiculo = new Veiculo();
        veiculo.setFabricante("Honda");
        veiculo.setModelo("Civic");
        veiculo.setAnoFabricacao(2020);
        veiculo.setAnoModelo(2020);
        veiculo.setValor(new BigDecimal(90500));
        veiculo.setTipoCombustivel(TipoCombustivel.BICOMBUSTIVEL);
        veiculo.setDataCadastro(LocalDate.now());
        veiculo.setPropietario(propietario);

        manager.persist(veiculo);
        tx.commit();
        manager.detach(veiculo);

        //buscando veiculo com objeto proprietario embutido
        Veiculo veiculo2 = manager.find(Veiculo.class, veiculo.getCodigo());
        System.out.println("Fabricante: " + veiculo2.getFabricante());
        System.out.println("Modelo: " + veiculo2.getModelo());
        System.out.println("Propietario: " + veiculo2.getPropietario().getNome());

        manager.close();
        JpaUtil.close();
    }
}
