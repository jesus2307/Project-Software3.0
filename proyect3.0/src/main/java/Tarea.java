public class Tarea {
    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    public void completar() {
        this.completada = true;
    }

    public boolean estaCompletada() {
        return completada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion + (completada ? " (completada)" : " (pendiente)");
    }
}
