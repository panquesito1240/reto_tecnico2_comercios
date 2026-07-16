package com.reto_tecnico2.api_comercios.config;

import com.reto_tecnico2.api_comercios.models.Comercio;
import com.reto_tecnico2.api_comercios.models.Usuario;
import com.reto_tecnico2.api_comercios.repository.ComercioRepository;
import com.reto_tecnico2.api_comercios.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final ComercioRepository comercioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        // 1. Crear un usuario de prueba si no existe
        if (!usuarioRepository.existsByUsername("admin")) {
            Usuario admin = Usuario.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .email("admin@test.com")
                    .build();
            usuarioRepository.save(admin);
            System.out.println("Usuario de prueba creado: admin / admin123");
        }

        // 2. Crear comercios de prueba con sedes reales
        if (comercioRepository.count() == 0) {
            List<Comercio> comercios = new ArrayList<>();

            // Ripley
            Comercio ripley = Comercio.builder()
                    .nombre("Ripley")
                    .razonSocial("Tiendas Ripley S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            ripley.getSedes().add(crearSede("Miraflores", "Calle Schell 202", ripley));
            ripley.getSedes().add(crearSede("San Isidro", "Las Begonias 543", ripley));
            ripley.getSedes().add(crearSede("Santiago de Surco", "Jockey Plaza", ripley));
            comercios.add(ripley);

            // Saga Falabella
            Comercio saga = Comercio.builder()
                    .nombre("Saga Falabella")
                    .razonSocial("Saga Falabella S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            saga.getSedes().add(crearSede("San Isidro", "Paseo de la República 3220", saga));
            saga.getSedes().add(crearSede("Miraflores", "Av. José Pardo 130", saga));
            saga.getSedes().add(crearSede("Independencia", "Plaza Norte", saga));
            comercios.add(saga);

            // Plaza Vea
            Comercio plazaVea = Comercio.builder()
                    .nombre("Plaza Vea")
                    .razonSocial("Supermercados Peruanos S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            plazaVea.getSedes().add(crearSede("Miraflores", "Av. Arequipa 4651", plazaVea));
            plazaVea.getSedes().add(crearSede("San Borja", "La Rambla", plazaVea));
            comercios.add(plazaVea);

            // Tottus
            Comercio tottus = Comercio.builder()
                    .nombre("Tottus")
                    .razonSocial("Hipermercados Tottus S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            tottus.getSedes().add(crearSede("San Isidro", "Las Begonias 785", tottus));
            tottus.getSedes().add(crearSede("Surquillo", "Av. Angamos Este 1805", tottus));
            comercios.add(tottus);

            // Algunos genéricos para llenar la paginación
            for (int i = 5; i <= 15; i++) {
                Comercio gen = Comercio.builder()
                        .nombre("Comercio Genérico " + i)
                        .razonSocial("Genérica " + i + " S.A.C.")
                        .estado(true)
                        .sedes(new ArrayList<>())
                        .build();
                gen.getSedes().add(crearSede("Lince", "Av. Arenales " + (1000 + i), gen));
                comercios.add(gen);
            }

            comercioRepository.saveAll(comercios);
            System.out.println("Comercios reales y genéricos insertados exitosamente.");
        }
    }

    private com.reto_tecnico2.api_comercios.models.Sede crearSede(String distrito, String direccion, Comercio comercio) {
        return com.reto_tecnico2.api_comercios.models.Sede.builder()
                .distrito(distrito)
                .direccion(direccion)
                .telefono("01-444-" + (int)(Math.random() * 9000 + 1000))
                .correo("contacto@" + comercio.getNombre().replace(" ", "").toLowerCase() + ".com")
                .estado(true)
                .comercio(comercio)
                .build();
    }
}
