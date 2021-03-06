package ejercicio2Ajax.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.EntityExistsException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ejercicio2Ajax.dao.BeanDaoConsultasImpl;

public class SugerirDato extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Fuente de datos.
	 */
	private String unidadPersistencia;
	
	/**
	 * Objeto encapsula toda la información a nivel de aplicación.
	 */
	private ServletContext sc;
	
	public SugerirDato(){
		super();
	}
	
	/**
	 * Inicializa el servlet, y le proporciona un objeto, ServletConfig con
	 * información de nivel de aplicación sobre el contexto de datos que rodea
	 * al servlet en el contenedor web.
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// Imprescindible llamar a super.init(config) para tener acceso a la
		// configuración del servlet a nivel de contenedor web.
		super.init(config);
		// En este punto se procedería a obtener las referencias a las fuentes
		// de datos de la aplicación.
		sc = config.getServletContext();
		setUPApp((String) sc.getInitParameter("unidadPersistencia"));
		sc.setAttribute("upbdfilm", getUPApp());
		sc.setAttribute("appOperativa", "true");
		if (getUPApp() == null){
			System.out.println("la unidad de persistencia es null.");
			sc.setAttribute("appOperativa", "false");
		}
	}
	
	/**
	 * Prepara el servlet para su eliminación.
	 */
	public void destroy() {
		// Elimina el datasource del ámbito de aplicación, liberando todos los
		// recursos que tuviera asignados.
		sc.removeAttribute("upbdfilm");
		sc.removeAttribute("appOperativa");
		// Elimina el ámbito de aplicación.
		sc = null;
	}
	
	/**
	 * Establece la fuente de datos para la aplicación.
	 */
	public void setUPApp(String unidadPersistencia) {
		this.unidadPersistencia = unidadPersistencia;
	}

	/**
	 * Obtiene la referencia a la fuente de datos de la aplicación.
	 */
	public String getUPApp() {
		return this.unidadPersistencia;
	}
	
	/**
	 * Procesamiento de la petición en modo GET. Se reenvía al método doPost()
	 * @param request Petición
	 * @param response Respuesta
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * Procesamiento de la petición en modo POST
	 * @param request Petición
	 * @param response Respuesta
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipoBusqueda");
		String dato=request.getParameter("dato");
		String sugerencia = null;
		System.out.println("Servlet SugerirDato");
		System.out.println("Dato a buscar: "+ dato);
		System.out.println("Tipo de búsqueda: "+ tipo);
		BeanDaoConsultasImpl daoConsultas = (BeanDaoConsultasImpl) sc.getAttribute("daoConsultas");
		if (daoConsultas == null){
			daoConsultas = new BeanDaoConsultasImpl(getUPApp());
		}
		response.setContentType("text/plain;charset=UTF-8");
        PrintWriter out = response.getWriter();
		try{
			daoConsultas.getConexion();
			switch (tipo) {
            case "director":
            	sugerencia = daoConsultas.obtenerNombresDirectores(dato);
                break;
            case "actor":
                sugerencia = daoConsultas.obtenerNombresActores(dato);
			}
			if(sugerencia!=null){
				out.println(sugerencia);
			}
			out.close();
		} catch (EntityExistsException e) {
			System.out.println("Excepción de existencia previa de la entidad.");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("Excepción de instancia no es tipo entidad.");
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Error desconocido en transacción.");
			e.printStackTrace();
		} finally {
			try {
				daoConsultas.close();
			} catch (Exception e) {
				System.out.println("Error al cerrar la conexión.");
				e.printStackTrace();
			}
		}
	}


}
