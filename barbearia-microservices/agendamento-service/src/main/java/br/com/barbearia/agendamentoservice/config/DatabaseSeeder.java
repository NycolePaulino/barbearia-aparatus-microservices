package br.com.barbearia.agendamentoservice.config;


import br.com.barbearia.agendamentoservice.domain.Barbershop;
import br.com.barbearia.agendamentoservice.domain.BarbershopService;
import br.com.barbearia.agendamentoservice.repository.BarbershopRepository;
import br.com.barbearia.agendamentoservice.repository.BarbershopServiceRepository;
import br.com.barbearia.agendamentoservice.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final BarbershopRepository barbershopRepository;
    private final BarbershopServiceRepository barbershopServiceRepository;
    private final BookingRepository bookingRepository;

    @Override
    public void run(String... args) throws Exception {
        // Limpando tudo para não duplicar dados a cada reinício
        barbershopRepository.deleteAll();
        barbershopServiceRepository.deleteAll();
        bookingRepository.deleteAll();

        System.out.println("Iniciando a população do banco de dados...");

        // Criando barbearias
        Barbershop b1 = new Barbershop(null, "Barbearia Corleone", "Rua Doutor Renato Paes de Barros, 390", "A Barbearia Corleone é inspirado nas antigas barbearias nova-iorquinas típicas de filmes da máfia das décadas 40, 50 e 60.", "https://www.baressp.com.br/bares/fotos2/barbearia-corleone-baressp-1-min.jpg", List.of("(11) 99999-9991"), null);
        Barbershop b2 = new Barbershop(null, "WM Vintage Clube", "Avenida Principal, 456", "As últimas tendências de corte.", "https://qlxfzjbgzjfecfxlhqwe.supabase.co/storage/v1/object/public/barber-photos/AnyConv.com__DSCF6796.webp", List.of("(21) 98888-8882"), null);
        Barbershop b3 = new Barbershop(null, "Barbearia Torres", "Praça Central, 789", "Especialistas em barba e navalha.", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSXDUbxbTN2z-jiCACdPyiYq0K4KA01w8f_hw&s", List.of("(31) 97777-7773"), null);
        Barbershop b4 = new Barbershop(null, "Black Wood", "Rua das Tesouras, 101", "Visual jovem e descolado.", "https://www.nemerfornaciari.com/wp-content/uploads/Screen-Shot-2021-05-03-at-16.29.13.png", List.of("(41) 96666-6664"), null);
        Barbershop b5 = new Barbershop(null, "Barba & Cia", "Beco do Corte, 202", "O melhor happy hour do homem.", "https://utfs.io/f/178da6b6-6f9a-424a-be9d-a2feb476eb36-16t.png", List.of("(51) 95555-5555"), null);

        // Salva as barbearias
        barbershopRepository.saveAll(List.of(b1, b2, b3, b4, b5));

        List<BarbershopService> servicos = new ArrayList<>();

        // SERVIÇOS - Barbearia Corleone (b1)
        servicos.add(criarServico("Corte Clássico", "Tesoura e máquina.", 4500, "https://utfs.io/f/0ddfbd26-a424-43a0-aaf3-c3f1dc6be6d1-1kgxo7.png", b1));
        servicos.add(criarServico("Barba Tradicional", "Toalha quente e navalha.", 3500, "https://utfs.io/f/e6bdffb6-24a9-455b-aba3-903c2c2b5bde-1jo6tu.png", b1));
        servicos.add(criarServico("Pézinho", "Acabamento na nuca.", 1500, "https://utfs.io/f/8a457cda-f768-411d-a737-cdb23ca6b9b5-b3pegf.png", b1));
        servicos.add(criarServico("Pigmentação", "Correção de falhas.", 3500, "https://plus.unsplash.com/premium_photo-1677444546739-21b8aad351d4", b1));
        servicos.add(criarServico("Corte na Tesoura", "Técnica manual.", 5000, "https://images.unsplash.com/photo-1503951914875-452162b0f3f1", b1));

        // SERVIÇOS - WM Vintage (b2)
        servicos.add(criarServico("Corte Degradê", "O corte do momento.", 6000, "https://utfs.io/f/0ddfbd26-a424-43a0-aaf3-c3f1dc6be6d1-1kgxo7.png", b2));
        servicos.add(criarServico("Barba Modelada", "Design moderno.", 4500, "https://utfs.io/f/e6bdffb6-24a9-455b-aba3-903c2c2b5bde-1jo6tu.png", b2));
        servicos.add(criarServico("Luzes", "Clareamento capilar.", 9000, "https://utfs.io/f/5788be0e-2307-4bb4-b603-d9dd237950a2-17l.png", b2));

        // SERVIÇOS - Barbearia Torres (b3)
        servicos.add(criarServico("Barba Real", "Tratamento completo.", 5500, "https://utfs.io/f/e6bdffb6-24a9-455b-aba3-903c2c2b5bde-1jo6tu.png", b3));
        servicos.add(criarServico("Corte Simples", "Apenas máquina.", 3000, "https://utfs.io/f/0ddfbd26-a424-43a0-aaf3-c3f1dc6be6d1-1kgxo7.png", b3));
        servicos.add(criarServico("Limpeza de Pele", "Remoção de cravos.", 7000, "https://images.unsplash.com/photo-1693755807658-17ce5331aacb", b3));

        // SERVIÇOS - Black Wood (b4)
        servicos.add(criarServico("Corte Rápido", "Sem hora marcada.", 2500, "https://utfs.io/f/0ddfbd26-a424-43a0-aaf3-c3f1dc6be6d1-1kgxo7.png", b4));
        servicos.add(criarServico("Sobrancelha", "Na navalha.", 1000, "https://utfs.io/f/2118f76e-89e4-43e6-87c9-8f157500c333-b0ps0b.png", b4));

        // SERVIÇOS - Barba & Cia (b5)
        servicos.add(criarServico("Combo Completo", "Cabelo, barba e sobrancelha.", 8500, "https://utfs.io/f/0ddfbd26-a424-43a0-aaf3-c3f1dc6be6d1-1kgxo7.png", b5));
        servicos.add(criarServico("Hidratação", "Produtos importados.", 4000, "https://utfs.io/f/c4919193-a675-4c47-9f21-ebd86d1c8e6a-4oen2a.png", b5));

        barbershopServiceRepository.saveAll(servicos);

        System.out.println("BANCO DE DADOS POPULADO COM SUCESSO!");
    }

    private BarbershopService criarServico(String nome, String descricao, Integer preco, String imagem, Barbershop loja) {
        return new BarbershopService(
                null,
                nome,
                descricao,
                imagem,
                loja.getId(),
                preco
        );
    }
}
