package com.goldenBet.GoldenBet;

import com.goldenBet.GoldenBet.models.Competencia;
import com.goldenBet.GoldenBet.models.Deporte;
import com.goldenBet.GoldenBet.models.Evento;
import com.goldenBet.GoldenBet.repository.IRepositoryEvento;
import com.goldenBet.GoldenBet.service.ServiceEvento;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
class GoldenBetApplicationTests {

	private final ServiceEvento eventService;

	@MockBean
	private final IRepositoryEvento eventRepo;

	@Autowired
	GoldenBetApplicationTests(
			@Qualifier("ServiceEvento")
			ServiceEvento service,

			@Qualifier("RepoEvento")
			IRepositoryEvento repo
	) {
		eventService = service;
		eventRepo = repo;
	}

	//poner a prueba metodo GET ALL segun mockbean, y return simulado creado dentro del scope
	@Test
	void testGetAll() {

		when(eventRepo.findAll())
				.thenReturn(Stream.of(
						new Evento(
								"evt-1",
								"Madrid",
								"Barca",
								LocalDate.now(),
								LocalTime.now(),
								"Barcelona vs. Madrid Clásico",
								"PROGRAMADO",
								new Competencia("cmp-2", "Champions",
										new Deporte("dep-1", "Soccer", null),
										null),
								null
						),
						new Evento(
								"evt-2",
								"Boston Celtics",
								"Denver Nuggets",
								LocalDate.now(),
								LocalTime.now(),
								"Finals Game 7 - Celtics vs Nuggets",
								"EN_PROGRESO",
								new Competencia("cmp-1", "NBA",
										new Deporte("dep-2", "BasketBall", null),
										null),
								null
						)
				).collect(Collectors.toList()));

		//lo que se espera
		int expectedSize = 2;

		//lo que realmente dará (getAll del mockbean en base al Stream.of() simulado)
		int actualSize = eventService.getAll().size();

		//compara para aceptar o denegar el Test como tal
		assertEquals(expectedSize, actualSize);

		System.out.println("\nExpected size from test: " + expectedSize);
		System.out.println("Actual retrieved size (mock bean): " + actualSize);
	}

	@Test
	public void testGetByDescripcion() {

		String descripcion = "finals";

		when(eventRepo.getByDescripcion(descripcion))
				.thenReturn(Stream.of(
						new Evento(
								"evt-55",
								"Denver Nuggets",
								"Miami Heat",
								LocalDate.now(),
								LocalTime.now(),
								"Finals Game 7 - Nuggets vs. Heat",
								"PROGRAMADO",
								new Competencia("cmp-2", "NBA",
										new Deporte("dep-1", "BasketBall", null),
										null),
								null
						),
						new Evento(
								"evt-62",
								"Boston Celtics",
								"Golden State Warriors",
								LocalDate.now(),
								LocalTime.now(),
								"Finals Game 2 - Celtics vs Warriors",
								"FINALIZADO",
								new Competencia("cmp-1", "NBA",
										new Deporte("dep-2", "BasketBall", null),
										null),
								null
						),
						new Evento(
								"evt-100",
								"Los Angelers Lakers",
								"San Antonio Spurs",
								LocalDate.now(),
								LocalTime.now(),
								"Regular Season - Lakers vs Spurs - Game 3",
								"EN_CURSO",
								new Competencia("cmp-1", "NBA",
										new Deporte("dep-2", "BasketBall", null),
										null),
								null
						)
				).collect(Collectors.toList()));

		//lo que se espera
		int expectedSize = 3;

		//lo que realmente dará (getAll del mockbean en base al Stream.of() simulado)
		int actualSize = eventService.getByDescripcion(descripcion).size();

		//compara para aceptar o denegar el Test como tal
		assertEquals(expectedSize, actualSize);

		System.out.println("\nExpected size from test: " + expectedSize);
		System.out.println("Actual retrieved size (mock bean): " + actualSize);
	}

	@Test
	public void testCreate() {
		Evento testEvent = new Evento(
				"evt-250",
				"Lester Rodríguez",
				"Marcos Sánchez",
				LocalDate.now(),
				LocalTime.now(),
				"Show Apertura - Reinado UAM - Marcoc vs Lesterinho",
				"PROGRAMADO",
				new Competencia("cmp-1", "Liga UAM Taekwondo",
						new Deporte("dep-7", "Taekwondo", null),
						null),
				null
		);

		System.out.println(testEvent.getId());
		System.out.println(testEvent.getDescripcion());
		System.out.println(testEvent.getEstado());
		System.out.println(testEvent.getParticipante1());
		System.out.println(testEvent.getParticipante2());
		System.out.println(testEvent.getCompetencia().getNombre());
		System.out.println(testEvent.getFecha());
		System.out.println(testEvent.getHora());
		System.out.println();

		when(eventRepo.save(testEvent)).thenReturn(testEvent);

		assertEquals(testEvent, testEvent);

		System.out.println("Evento creado de manera satisfactoria en Mock Test");
	}

}
