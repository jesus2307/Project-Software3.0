import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TareaTest {

    @Test
    public void testCompletar() {
        // Asegúrate de que aquí solo se pasa un argumento, por ejemplo, "Prueba"
        Tarea tarea = new Tarea("Prueba");
        assertFalse(tarea.estaCompletada());
        tarea.completar();
        assertTrue(tarea.estaCompletada());
    }
}
