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
            ripley.getSedes().add(crearSede("San Miguel", "Plaza San Miguel", ripley));
            ripley.getSedes().add(crearSede("Independencia", "Plaza Norte", ripley));
            ripley.getSedes().add(crearSede("Ate", "Real Plaza Puruchuco", ripley));
            ripley.getSedes().add(crearSede("Santa Anita", "Mall Aventura Santa Anita", ripley));
            ripley.getSedes().add(crearSede("Jesus Maria", "Real Plaza Salaverry", ripley));
            ripley.getSedes().add(crearSede("Chorrillos", "Plaza Lima Sur", ripley));
            ripley.getSedes().add(crearSede("Breña", "La Rambla Brasil", ripley));
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
            saga.getSedes().add(crearSede("Santiago de Surco", "Jockey Plaza", saga));
            saga.getSedes().add(crearSede("San Miguel", "Plaza San Miguel", saga));
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
            plazaVea.getSedes().add(crearSede("Surco", "C.C. El Polo", plazaVea));
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

            // Sodimac
            Comercio sodimac = Comercio.builder()
                    .nombre("Sodimac")
                    .razonSocial("Tiendas del Mejoramiento del Hogar S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            sodimac.getSedes().add(crearSede("Javier Prado", "Av. Javier Prado Este 1059", sodimac));
            sodimac.getSedes().add(crearSede("San Miguel", "Av. La Marina 2500", sodimac));
            comercios.add(sodimac);

            // Metro
            Comercio metro = Comercio.builder()
                    .nombre("Metro")
                    .razonSocial("Cencosud Retail Peru S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            metro.getSedes().add(crearSede("Breña", "Av. Alfonso Ugarte 1120", metro));
            metro.getSedes().add(crearSede("San Juan de Lurigancho", "Av. Próceres de la Independencia 1632", metro));
            metro.getSedes().add(crearSede("Chorrillos", "Av. Huaylas 875", metro));
            comercios.add(metro);

            // Promart
            Comercio promart = Comercio.builder()
                    .nombre("Promart")
                    .razonSocial("Homecenters Peruanos S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            promart.getSedes().add(crearSede("Ate", "Av. Separadora Industrial", promart));
            promart.getSedes().add(crearSede("Pro", "Panamericana Norte Km 22", promart));
            comercios.add(promart);

            // Wong
            Comercio wong = Comercio.builder()
                    .nombre("Wong")
                    .razonSocial("Cencosud Retail Peru S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            wong.getSedes().add(crearSede("Miraflores", "Óvalo Gutiérrez", wong));
            wong.getSedes().add(crearSede("San Isidro", "Av. Dos de Mayo", wong));
            comercios.add(wong);

            // Makro
            Comercio makro = Comercio.builder()
                    .nombre("Makro")
                    .razonSocial("Supermercados Peruanos S.A.")
                    .estado(true)
                    .sedes(new ArrayList<>())
                    .build();
            makro.getSedes().add(crearSede("Surco", "Av. Jorge Chávez", makro));
            makro.getSedes().add(crearSede("Independencia", "Panamericana Norte", makro));
            comercios.add(makro);

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
