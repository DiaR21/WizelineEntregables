package com.wizeline.proyecto;

import com.wizeline.proyecto.model.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProyectoApplicationTests {

	/**GET id */
	@Test
	public void traesUnUsuario() {
		Usuario usuario = new Usuario("3","Diana","dr@gmail.com",26,55);
		when(usuarioService.findById("3")).thenReturn(java.util.Optional.of(usuario));
		Usuario usuario7 =new Usuario("3");
		assertAll(() -> assertNotNull(usuario7), () -> assertEquals(HttpStatus.OK, usuario7)
		);
	}
	/**Post*/
	@Mock
	private List<String> imitaGuardar;
	@Test
	public void imitaUsuarioGuardado() {
		imitaGuardar.add("6");
		imitaGuardar.add("Diana");
		imitaGuardar.add("dr@gmail.com");
		imitaGuardar.add("26");
		imitaGuardar.add("55");
		assertNotEquals(null, save(imitaGuardar));
	}
	/**PUT*/
	@Mock
	private List<String> imitaActializa;
	@Test
	public void imitaUsuarioGuardado() {
		imitaActializa.add("6");
		imitaActializa.add("Lizbeth");
		imitaActializa.add("dr@gmail.com");
		imitaActializa.add("26");
		imitaActializa.add("55");
		assertNotEquals(null, updata(imitaGuardar));
	}

	/**Delete*/
	@ExtendWith(MockitoExtension.class)
	public class UsuarioController{
		@Mock
		private List<Usuario> imitaUsuarioEliminar;
		@InjectMocks
		public void eilinaUsuario(){
			Usuario usuario10 = new Usuario("10","Diana","dr@gmail.com",26,55);
			when(imitaUsuarioEliminar.deleteById("10")).thenReturn(null);
			assertEquals(null, imitaUsuarioEliminar)
		}
	}
}

}
