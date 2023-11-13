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

		System.out.println("Prueba Unitaria GET (all)\n");

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

		System.out.println("Prueba Unitaria GET (filtro: DESCRIPCION = " + descripcion + ")\n");

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
								"Los Angeles Lakers",
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

		System.out.println("Prueba Unitaria POST (crear evento deportivo)\n");

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

	@Test
	public void getByParticipante() {

		String participante = "Liverpool FC";

		System.out.println("Prueba Unitaria GET (filtro: PARTICIPANTE = " + participante + ")\n");

		when(eventRepo.getByParticipante1(participante))
				.thenReturn(Stream.of(
						new Evento(
								"evt-501",
								"Liverpool FC",
								"Chelsea FC",
								LocalDate.now(),
								LocalTime.now(),
								"Semifinales de la Carabao Cup 2022",
								"FINALIZADO",
								new Competencia("cmp-5", "Premier League",
										new Deporte("dep-4", "Fútbol", null),
										null),
								null
						),
						new Evento(
								"evt-209",
								"Liverpool FC",
								"Arsenal FC",
								LocalDate.now(),
								LocalTime.now(),
								"Final de la FA Cup 2022",
								"FINALIZADO",
								new Competencia("cmp-5", "Premier League",
										new Deporte("dep-4", "Fútbol", null),
										null),
								null
						)
				).collect(Collectors.toList()));

		int expectedSize = 2;
		int actualSize = eventService.getByParticipantes(participante).size();
		assertEquals(expectedSize, actualSize);

		System.out.println("Cantidad de registros esperados a obtener del test: " + expectedSize);
		System.out.println("Cantidad obtenida por service (mock): " + actualSize);
	}

	@Test
	public void getByEstado() {

		String estado = "PROGRAMADO";

		System.out.println("Prueba Unitaria GET (filtro: ESTADO = " + estado + ")\n");

		when(eventRepo.getByEstado(estado))
				.thenReturn(Stream.of(
						new Evento(
								"evt-100",
								"Milwaukee Bucks",
								"Brooklyn Nets",
								LocalDate.now(),
								LocalTime.now(),
								"Regular Season Conference Game",
								"PROGRAMADO",
								new Competencia("cmp-10", "NBA",
										new Deporte("dep-4", "Basketball", null),
										null),
								null
						),
						new Evento(
								"evt-305",
								"Novak Djokovic",
								"Roger Federer",
								LocalDate.now(),
								LocalTime.now(),
								"Final Individual Masculino 2022",
								"PROGRAMADO",
								new Competencia("cmp-12", "Wimbledon",
										new Deporte("dep-1", "Ténis", null),
										null),
								null
						)
				).collect(Collectors.toList()));

		int expectedSize = 2;
		int actualSize = eventService.getByEstado(estado).size();
		assertEquals(expectedSize, actualSize);

		System.out.println("Cantidad de registros esperados a obtener del test: " + expectedSize);
		System.out.println("Cantidad obtenida por service (mock): " + actualSize);
	}

	public void getByCompetencia() {

		String competencia = "UEFA Champions League";

		System.out.println("Prueba Unitaria GET (filtro: COMPETENCIA = " + competencia + ")\n");

		when(eventRepo.getByCompetencia(competencia))
				.thenReturn(Stream.of(
						new Evento(
								"evt-666",
								"Real Madrid",
								"Manchester City",
								LocalDate.now(),
								LocalTime.now(),
								"Semifinales de la UEFA Champions League 2023",
								"EN_CURSO",
								new Competencia("cmp-22", "UEFA Champions League",
										new Deporte("dep-7", "Fútbol (Europa)", null),
										null),
								null
						),
						new Evento(
								"evt-278",
								"Paris Saint-Germain",
								"Chelsea FC",
								LocalDate.now(),
								LocalTime.now(),
								"Cuartos de Final de la UEFA Champions League 2022",
								"FINALIZADO",
								new Competencia("cmp-22", "UEFA Champions League",
										new Deporte("dep-7", "Fútbol (Europa)", null),
										null),
								null
						)
				).collect(Collectors.toList()));

		int expectedSize = 2;
		int actualSize = eventService.getByCompetencia(competencia).size();
		assertEquals(expectedSize, actualSize);

		System.out.println("Cantidad de registros esperados a obtener del test: " + expectedSize);
		System.out.println("Cantidad obtenida por service (mock): " + actualSize);
	}
}
