package pe.com.cosito.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.com.cosito.beans.Categoria;
import pe.com.cosito.beans.Ciudad;
import pe.com.cosito.beans.DetallePedido;
import pe.com.cosito.beans.Laboratorio;
import pe.com.cosito.beans.Pedido;
import pe.com.cosito.beans.Presentacion;
import pe.com.cosito.beans.PresentacionProducto;
import pe.com.cosito.beans.Productos;
import pe.com.cosito.beans.ProductosR;
import pe.com.cosito.beans.Sede;
import pe.com.cosito.beans.Usuario;
import pe.com.cosito.service.CategoriaService;
import pe.com.cosito.service.CiudadService;
import pe.com.cosito.service.EjemploService;
import pe.com.cosito.service.LaboratorioService;
import pe.com.cosito.service.PresentacionProductoService;
import pe.com.cosito.service.PresentacionService;
import pe.com.cosito.service.ProductosService;
import pe.com.cosito.service.SedeService;
import pe.com.cosito.service.UsuarioService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private EjemploService ejemploService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private ProductosService productosService;
	@Autowired
	private CategoriaService categoriaService;
	@Autowired
	private LaboratorioService laboratorioService;
	@Autowired
	private PresentacionService presentacionService;
	@Autowired
	private CiudadService ciudadService;
	@Autowired
	private SedeService sedeService;
	@Autowired
	private PresentacionProductoService presentacionProductoService;

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request)
			throws Exception {
		List<ProductosR> productos = getProductos();
		List<Categoria> categorias = categoriaService.getAll();
		model.addAttribute("categoria", categorias);
		model.addAttribute("sizeLista", controlProductos(productos.size()));
		model.addAttribute("Lista", productos);
		try {
			HttpSession hs = request.getSession();
			String id = hs.getAttribute("id").toString();
			Usuario usu = usuarioService.findById(id);
			model.addAttribute("usuario", usu);
		} catch (Exception ex) {
		}
		return "productos";
	}

	List<DetallePedido> ltaDetallePedidos = new ArrayList<DetallePedido>();

	@RequestMapping(value = "/agregaCarrito", method = { RequestMethod.GET,
			RequestMethod.POST })
	@ResponseBody
	public String agregaCarrito(@RequestParam("idProducto") int idProducto,
			@RequestParam("idPresentacion") int idPresentacion,
			@RequestParam("cantidad") int cantidad, HttpServletRequest request) {

		HttpSession hs = request.getSession();
		String rpta = "Se agrego el producto con exito,<br/><p>Gracias</p>";
		try {
			Calendar fecha = Calendar.getInstance();

			PresentacionProducto prepro = presentacionProductoService
					.getPrecio(idPresentacion, idProducto);

			Date mifecha = null;
			fecha.add(Calendar.DATE, 1);
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			String formatted = format1.format(fecha.getTime());

			mifecha = format1.parse(formatted);

			Pedido pedido = new Pedido();
			pedido.setFechaPedido(mifecha);
			String horaPedido = fecha.get(Calendar.HOUR_OF_DAY) + ":"
					+ fecha.get(Calendar.MINUTE);
			pedido.setHoraPedido(horaPedido);
			pedido.setEstado(0);
			if (hs.getAttribute("id") != null) {
				String idCadena = hs.getAttribute("id").toString();
				int id = Integer.parseInt(idCadena);
				pedido.setIdCliente(id);
			}
			DetallePedido detallePedido = new DetallePedido();
			detallePedido.setCantidad(cantidad);
			detallePedido.setIdPresentacion(idPresentacion);
			detallePedido.setIdProducto(idProducto);
			detallePedido.setPrecio(prepro.getPrecio());

			ltaDetallePedidos.add(detallePedido);

			hs.setAttribute("pedidos", pedido);
			hs.setAttribute("detalles", ltaDetallePedidos);

		} catch (Exception exc) {
			rpta = "Obtuvimos un Error en el sistema, Intentelo mas tarde!<br/>Gracias";
			logger.info("El error es el siguiente: " + exc.getMessage());
			exc.printStackTrace();
		}
		return rpta;
	}

	@RequestMapping(value = "/mostrarPresentaciones", method = {
			RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public List<Presentacion> mostrarPresentaciones(
			@RequestParam("idProducto") String idProducto) {
		try {
			logger.info("Ingreso a mostrar presentaciones con codigo "
					+ idProducto);
			int data = Integer.parseInt(idProducto);
			return presentacionService.getPresentacionxProducto(data);
		} catch (Exception ex) {
			return null;
		}
	}

	@RequestMapping(value = "/productos", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String productos(Model model, HttpServletRequest request)
			throws Exception {
		HttpSession hs = request.getSession();
		if (hs.getAttribute("usuario") != null) {
			String usuario = hs.getAttribute("usuario").toString();
			String tipoUsuario = hs.getAttribute("tipoUsuario").toString();

			String id = hs.getAttribute("id").toString();
			Usuario usu = usuarioService.findById(id);
			model.addAttribute("user", usu);

			model.addAttribute("usuario", usuario);
			model.addAttribute("tipoUsuario", tipoUsuario);
		}
		List<ProductosR> productos = getProductos();
		List<Categoria> categorias = categoriaService.getAll();
		model.addAttribute("categoria", categorias);
		model.addAttribute("sizeLista", controlProductos(productos.size()));
		model.addAttribute("Lista", productos);
		return "productos";
	}

	public double controlProductos(int num) {
		logger.info("Entro en el control de Productos con: " + num + " Datos");
		double rpta = Math.ceil((double) num / (double) 20);
		return rpta;
	}

	public List<ProductosR> getProductos() throws Exception {
		List<Productos> productos = new ArrayList<Productos>();
		productos = productosService.getAll();

		List<ProductosR> productosR = new ArrayList<ProductosR>();
		List<Categoria> categorias = categoriaService.getAll();
		List<Laboratorio> laboratorios = laboratorioService.getAll();
		int contador = 0;
		for (Productos p : productos) {
			ProductosR produc = new ProductosR();
			produc.setId(p.getId());
			produc.setNombre(p.getNombre());

			for (Categoria ca : categorias) {
				if (ca.getId() == p.getIdCategoria()) {
					produc.setIdCategoria(ca);
				}
			}
			for (Laboratorio l : laboratorios) {
				if (l.getId() == p.getIdLaboratorio()) {
					produc.setIdLaboratorio(l);
				}
			}
			productosR.add(produc);

			contador++;
			if (contador >= 20) {
				break;
			}
		}
		return productosR;
	}

	@RequestMapping(value = "/validaLogin", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public String login(@RequestParam("usuario") String usuario,
			@RequestParam("password") String password,
			HttpServletRequest request, Model model) {
		String tipoUsuario = "Administrador";
		try {
			logger.info("Inicio de sesion usando Usuario: " + usuario
					+ " y Password: " + password);
			List<Usuario> usuarios = usuarioService.validaLogin();

			for (Usuario usu : usuarios) {
				if (usu.getUsuario().equals(usuario)
						&& usu.getPassword().equals(password)) {
					if (usu.getIdTipo() == 2) {
						tipoUsuario = "Vendedor";
					} else {
						if (usu.getIdTipo() == 3) {
							tipoUsuario = "Cliente";
						}
					}
					HttpSession sesion = request.getSession();
					sesion.setAttribute("tipoUsuario", tipoUsuario);
					sesion.setAttribute("id", usu.getId());
					sesion.setAttribute("usuario", usu.getDescripcion() + " "
							+ usu.getApellido());
					return "verdadero";
				}
			}
			return "falso";
		} catch (Exception ex) {
			logger.info("Aqui al validar: " + ex.getMessage());
			return null;
		}
	}

	@RequestMapping(value = "/ajaxProductos", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<ProductosR> ajaxProductos() throws Exception {
		List<Productos> productos = new ArrayList<Productos>();
		productos = productosService.getAll();

		List<ProductosR> productosR = new ArrayList<ProductosR>();
		List<Categoria> categorias = categoriaService.getAll();
		List<Laboratorio> laboratorios = laboratorioService.getAll();

		for (Productos p : productos) {
			ProductosR produc = new ProductosR();
			produc.setId(p.getId());
			produc.setNombre(p.getNombre());

			for (Categoria ca : categorias) {
				if (ca.getId() == p.getIdCategoria()) {
					produc.setIdCategoria(ca);
				}
			}
			for (Laboratorio l : laboratorios) {
				if (l.getId() == p.getIdLaboratorio()) {
					produc.setIdLaboratorio(l);
				}
			}
			productosR.add(produc);
		}
		return productosR;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String loginPage(HttpServletRequest request, Model model,
			HttpServletResponse response) {
		HttpSession hs = request.getSession();
		try {
			logger.info("Respuesta desde registro: "
					+ hs.getAttribute("respuesta"));
			if (hs.getAttribute("respuesta") != null) {
				if (hs.getAttribute("color").toString().equals("green")) {
					String r = hs.getAttribute("respuesta").toString();
					String c = hs.getAttribute("color").toString();
					model.addAttribute("respuesta", r);
					model.addAttribute("color", c);

					hs.removeAttribute("respuesta");
					hs.removeAttribute("color");
				} else {
					response.sendRedirect("register");
				}
			}
		} catch (Exception ex) {
			logger.info("Error" + ex.getMessage());
		}
		return "home";
	}

	@RequestMapping(value = "/register", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String registerPage(Model model, HttpServletRequest request) {
		List<Ciudad> ciudades = null;
		List<Sede> sedes = null;
		try {
			ciudades = ciudadService.getAll();
			sedes = sedeService.getAll();
			HttpSession hs = request.getSession();
			logger.info("Respuesta desde registro: "
					+ hs.getAttribute("respuesta"));
			if (hs.getAttribute("respuesta") != null) {
				String r = hs.getAttribute("respuesta").toString();
				String c = hs.getAttribute("color").toString();
				model.addAttribute("respuesta", r);
				model.addAttribute("color", c);

				hs.removeAttribute("respuesta");
				hs.removeAttribute("color");
			}
		} catch (Exception ex) {
			logger.info("Error: " + ex.getMessage());
			ex.printStackTrace();
		}
		model.addAttribute("sedes", sedes);
		model.addAttribute("ciudades", ciudades);
		logger.info("Cantidad de ciudades: " + ciudades.size());
		logger.info("Cantidad de sedes: " + sedes.size());
		return "register";
	}

	@RequestMapping(value = "/carritoCompras", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String carritoCompras(HttpServletRequest request, Model model) {
		HttpSession hs = request.getSession();
		try {
			if (hs.getAttribute("pedidos") != null) {
				List<Productos> ltaProd = productosService.getAll();
				List<Presentacion> ltaPre = presentacionService.getAll();

				Pedido pedidos = (Pedido) hs.getAttribute("pedidos");
				List<DetallePedido> detalles = (List<DetallePedido>) hs
						.getAttribute("detalles");

				model.addAttribute("pedidos", pedidos);
				model.addAttribute("detalles", detalles);
				model.addAttribute("ltaProd", ltaProd);
				model.addAttribute("ltaPre", ltaPre);

			} else {
				model.addAttribute("carro",
						"<h3>Aun no ha agregado productos</h3>");
				model.addAttribute("color", "red");
			}
		} catch (Exception ex) {

		}
		return "carritoCompras";
	}

	@RequestMapping(value = "/cerrarSesion", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String cerrarSession(HttpServletRequest request, Model model)
			throws Exception {
		
		HttpSession hs = request.getSession();
		hs.removeAttribute("detalles");
		hs.removeAttribute("pedidos");
		hs.removeAttribute("tipoUsuario");
		hs.removeAttribute("id");
		hs.removeAttribute("usuario");
		
		List<ProductosR> productos = getProductos();
		List<Categoria> categorias = categoriaService.getAll();
		model.addAttribute("categoria", categorias);
		model.addAttribute("sizeLista", controlProductos(productos.size()));
		model.addAttribute("Lista", productos);
		return "productos";
	}
	@RequestMapping(value = "/cancelaPedido", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String cancelaPedido(HttpServletRequest request, Model model)
			throws Exception {
		
		HttpSession hs = request.getSession();
		hs.removeAttribute("detalles");
		hs.removeAttribute("pedidos");
		
		List<ProductosR> productos = getProductos();
		List<Categoria> categorias = categoriaService.getAll();
		model.addAttribute("categoria", categorias);
		model.addAttribute("sizeLista", controlProductos(productos.size()));
		model.addAttribute("Lista", productos);
		return "productos";
	}

	@RequestMapping(value = "/busqueda", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public List<ProductosR> busquedaProductosCategoria(
			@RequestParam("nombre") String nombre,
			@RequestParam("tipo") String tipo) throws Exception {
		logger.info("Nombre: " + nombre + " tipo: " + tipo);
		List<Productos> productos = new ArrayList<Productos>();
		;
		if (tipo.equals("producto")) {
			// productos = productosService.buscarPorProducto(nombre);
			productos = productosService.buscarPorProducto(nombre);
		}
		if (tipo.equals("categoria")) {
			productos = productosService.getAllByCategory(nombre);
		}
		List<ProductosR> productosR = new ArrayList<ProductosR>();
		List<Categoria> categorias = categoriaService.getAll();
		List<Laboratorio> laboratorios = laboratorioService.getAll();

		for (Productos p : productos) {
			ProductosR produc = new ProductosR();
			produc.setId(p.getId());
			produc.setNombre(p.getNombre());

			for (Categoria ca : categorias) {
				if (ca.getId() == p.getIdCategoria()) {
					produc.setIdCategoria(ca);
				}
			}
			for (Laboratorio l : laboratorios) {
				if (l.getId() == p.getIdLaboratorio()) {
					produc.setIdLaboratorio(l);
				}
			}
			productosR.add(produc);
		}

		logger.info("Esta devolviendo para la busqueda una lista de tamanio: "
				+ productos.size());
		return productosR;
	}

	@RequestMapping(value = "/registraUsuario", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public void agregarusuario(HttpServletRequest request,
			@RequestParam("usuario") String usuario,
			@RequestParam("password") String password,
			@RequestParam("nombre") String nombre,
			@RequestParam("apellidos") String apellidos,
			@RequestParam("fechaNac") Date fechaNac,
			@RequestParam("dni") String dni, @RequestParam("sede") int sede,
			@RequestParam("tipoUsuario") int tipoUsuario) {
		logger.info("Ingreso a registrar usuario con usuario: " + usuario);
		if (usuarioService.busquedaDni(dni)) {
			Usuario usu = new Usuario();
			usu.setUsuario(usuario);
			usu.setApellido(apellidos);
			usu.setPassword(password);
			usu.setDescripcion(nombre);
			usu.setFechaNac(fechaNac);
			usu.setDni(dni);
			usu.setIdSede(sede);
			usu.setIdTipo(tipoUsuario);
			int rpta = 0;
			try {
				rpta = usuarioService.guardar(usu);
				HttpSession h = request.getSession();
				String resp = "Hemos registrado correctamente sus datos";
				String color = "green";
				if (rpta == 0) {
					resp = "Obtuvimos un error al registrar sus datos";
					color = "red";
				}
				h.setAttribute("respuesta", resp);
				h.setAttribute("color", color);
				logger.info("La respuesta que se obtiene al registrar usuario es: "
						+ resp);
			} catch (Exception ex) {
				rpta = 0;
				logger.info("Error al agregar producto: " + ex.getMessage());
				ex.printStackTrace();
			}
		}
	}
}