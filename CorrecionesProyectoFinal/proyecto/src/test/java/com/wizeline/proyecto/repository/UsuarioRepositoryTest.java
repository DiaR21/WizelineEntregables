package com.wizeline.proyecto.repository;

import com.wizeline.proyecto.model.Usuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.stream.Collectors;

import static com.mongodb.internal.connection.tlschannel.util.Util.assertTrue;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioRepositoryTest {
    @DataMongoTest
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class RepositorioUsuario {
        @Autowired
        private UsuarioRepository usuarioRepositorys;

        @BeforeAll
        public void inicializar() {
            usuarioRepositorys.save(new Usuario("10","Diana","dr10@gmail.com",26,55));
            usuarioRepositorys.save(new Usuario("11","Lizbeth","dr11@gmail.com",26,55));
        }

        @AfterAll
        public void limpiar() {
            usuarioRepositorys.deleteAll();
        }

        @Test
        public void entregaDeDatos() {
            List<Usuario> datos = usuarioRepositorys.findAll();
            assertAll(
                    () -> assertEquals(2, datos.size()),
                    () -> assertTrue(
                            datos.stream()
                                    .map(Usuario:: getNombre)
                                    .collect(Collectors.toList())
                                    .containsAll(List.of("Diana", "Lizbeth")))
            );
        }

    }
