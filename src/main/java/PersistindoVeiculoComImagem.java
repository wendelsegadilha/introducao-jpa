import dominio.TipoCombustivel;
import dominio.Veiculo;
import util.JpaUtil;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;

public class PersistindoVeiculoComImagem {
    public static void main(String[] args) throws IOException {

        //Ler os bytes do arquivo de imagem
        Path path = FileSystems.getDefault().getPath("arquivos/honda-civic-2020.jpeg");
        byte[] foto = Files.readAllBytes(path);

        EntityManager manager = JpaUtil.getEtityManager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        StringBuilder especificacoes = new StringBuilder();
        especificacoes.append("Carro em excelente estado.\n");
        especificacoes.append("Completo, menos ar.\n");
        especificacoes.append("Primeiro dono, com manual de instrução ");
        especificacoes.append("e todas as revisões feitas.\n");
        especificacoes.append("IPVA pago, aceita financiamento.");

        Veiculo veiculo = new Veiculo();
        veiculo.setFabricante("Honda");
        veiculo.setModelo("Civic");
        veiculo.setAnoFabricacao(2020);
        veiculo.setAnoModelo(2020);
        veiculo.setValor(new BigDecimal(90500));
        veiculo.setTipoCombustivel(TipoCombustivel.BICOMBUSTIVEL);
        veiculo.setDataCadastro(LocalDate.now());
        veiculo.setEspecificacoes(especificacoes.toString()); //arquivo de texto longo
        veiculo.setFoto(foto); //arquivo binário longo

        manager.persist(veiculo);
        tx.commit();
        manager.detach(veiculo);

        //buscando veiculo e exibindo imagem
        Veiculo veiculo2 = manager.find(Veiculo.class, veiculo.getCodigo());

        if (veiculo2.getFoto() != null) {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(veiculo2.getFoto()));
            JOptionPane.showMessageDialog(null, new JLabel(new ImageIcon(image)));
        } else {
            System.out.println("Veículo não possui foto.");
        }

        manager.close();
        JpaUtil.close();
    }
}
