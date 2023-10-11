import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.uv.hello.conexion;
import org.uv.hello.Empleado;


@Named(value = "beanIndex")
@SessionScoped
public class BeanIndex implements Serializable {

    private int clave;
    private String nombre;
    private String direccion;
    private String telefono;
    private List<Empleado> empleados;// Una lista para almacenar los empleados

    // Constructor para inicializar la lista de empleados
    public BeanIndex() {
        empleados = new ArrayList<>();
        cargarEmpleados(); // Cargar los empleados al inicio
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    // Método para cargar los empleados desde la base de datos

    public void cargarEmpleados() {
    
        try {
            // Reemplaza con tu lógica de conexión y consulta SQL
            conexion cx = conexion.getConexion();
            String sql = "SELECT * FROM empleado";
            PreparedStatement pstmt = cx.con.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();

            empleados.clear(); // Limpiar la lista antes de cargar los datos

            while (resultSet.next()) {
                Empleado empleado = new Empleado();
                empleado.setClave(resultSet.getInt("clave"));
                empleado.setNombre(resultSet.getString("nombre"));
                empleado.setDireccion(resultSet.getString("direccion"));
                empleado.setTelefono(resultSet.getString("telefeno"));
                empleados.add(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    // Método para agregar un nuevo empleado
    public void crearEmpleado() {
        try {
            // Reemplaza con tu lógica de conexión y consulta SQL
            conexion cx = conexion.getConexion();
            String sql = "INSERT INTO empleado (clave, nombre, direccion, telefeno) VALUES (?, ?, ?, ?)";
            
            PreparedStatement pstmt = cx.con.prepareStatement(sql);
            pstmt.setInt(1, clave);
            pstmt.setString(2, nombre);
            pstmt.setString(3, direccion);
            pstmt.setString(4, telefono);
            pstmt.executeUpdate();
            addMessage(FacesMessage.SEVERITY_INFO, "Atención", "Se guardó");
            cargarEmpleados(); // Recargar la lista de empleados después de la inserción
            limpiarCampos();
                    addMessage(FacesMessage.SEVERITY_INFO, "Info Message", "Se guardo");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un empleado existente
    public void actualizarEmpleado(Empleado empleado) {
        try {
            // Reemplaza con tu lógica de conexión y consulta SQL
            conexion cx = conexion.getConexion();
            String sql = "UPDATE empleado SET nombre = ?, direccion = ?, telefeno = ? WHERE clave = ?";
            PreparedStatement pstmt = cx.con.prepareStatement(sql);
            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getDireccion());
            pstmt.setString(3, empleado.getTelefono());
            pstmt.setInt(4, empleado.getClave());
            pstmt.executeUpdate();

            addMessage(FacesMessage.SEVERITY_INFO, "Atención...", "Se actualizó");
            cargarEmpleados(); // Recargar la lista de empleados después de la actualización
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un empleado existente
    public void eliminarEmpleado(Empleado empleado) {
        try {
            // Reemplaza con tu lógica de conexión y consulta SQL
            conexion cx = conexion.getConexion();
            String sql = "DELETE FROM empleado WHERE clave = ?";
            PreparedStatement pstmt = cx.con.prepareStatement(sql);
            pstmt.setInt(1, empleado.getClave());
            pstmt.executeUpdate();

            addMessage(FacesMessage.SEVERITY_INFO, "Atención...", "Se eliminó");
            cargarEmpleados(); // Recargar la lista de empleados después de la eliminación
            limpiarCampos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para limpiar los campos
    private void limpiarCampos() {
        clave = 0;
        nombre = "";
        direccion = "";
        telefono = "";
    }

}
