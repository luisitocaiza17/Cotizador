package com.qbe.cotizador.dao.producto.vehiculo.novacredit;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

import com.qbe.cotizador.dao.cotizacion.EstadoDAO;
import com.qbe.cotizador.dao.entidad.EntidadDAO;
import com.qbe.cotizador.dao.entidad.UsuarioDAO;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Cotizacion;
import com.qbe.cotizador.model.Entidad;
import com.qbe.cotizador.model.Estado;
import com.qbe.cotizador.model.PuntoVenta;
import com.qbe.cotizador.model.TipoObjeto;
import com.qbe.cotizador.model.Usuario;
import com.qbe.cotizador.model.VhNovacreditCotizacion;
import com.qbe.cotizador.transaction.producto.novacredit.VhNovacreditCotizacionTransaction;

public class VhNovacreditCotizacionDAO extends EntityManagerFactoryDAO<VhNovacreditCotizacion> {

	@PersistenceContext(name = "CotizadorWebPC", unitName = "CotizadorWebPU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		if (em == null) {
			Context initCtx = null;
			try {
				initCtx = new InitialContext();
				em = (javax.persistence.EntityManager) initCtx.lookup("java:comp/env/CotizadorWebPC");
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		return em;
	}

	public VhNovacreditCotizacionDAO() {
		super(VhNovacreditCotizacion.class);
	}

	public List<VhNovacreditCotizacion> buscarTodos() {
		return getEntityManager().createNamedQuery("VhNovacreditCotizacion.buscarTodos").getResultList();
	}

	public VhNovacreditCotizacion buscarPorId(String id) {
		VhNovacreditCotizacion novaCotizacion = new VhNovacreditCotizacion();
		List<VhNovacreditCotizacion> query = getEntityManager().createNamedQuery("VhNovacreditCotizacion.buscarPorId")
				.setParameter("id", id).getResultList();
		if (!query.isEmpty())
			novaCotizacion = query.get(0);
		return novaCotizacion;
	}

	public List<VhNovacreditCotizacion> buscarPorObjetoId(String objetoVehiculoId) {
		VhNovacreditCotizacion novaCotizacion = new VhNovacreditCotizacion();
		List<VhNovacreditCotizacion> query = getEntityManager().createNamedQuery("VhNovacreditCotizacion.buscarPorObjetoId")
				.setParameter("objetoId", objetoVehiculoId).getResultList();
		return query;
	}

	// Cotizaciones No Emitidas x Fecha
	public List<VhNovacreditCotizacion> buscarPorParametros(String fecha1, String fecha2, String numeroEndoso,
			String identificacion) {

		String f1 = fecha1 + " 00:00:00";
		String f2 = fecha2 + " 23:59:99";
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		java.sql.Timestamp timestamp1 = null;
		java.sql.Timestamp timestamp2 = null;
		String clienteId = "";

		// Validacion para cuando se ingrese la fecha en la búsqueda
		if (fecha1.length() > 0 && fecha1.length() > 0) {
			java.util.Date parsedDate = null;
			try {
				parsedDate = dateFormat.parse(f1);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			timestamp1 = new java.sql.Timestamp(parsedDate.getTime());

			try {
				parsedDate = dateFormat.parse(f2);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			timestamp2 = new java.sql.Timestamp(parsedDate.getTime());
		}
		TypedQuery<VhNovacreditCotizacion> query = null;
		List<VhNovacreditCotizacion> results = new ArrayList<VhNovacreditCotizacion>();

		EntidadDAO eDAO = new EntidadDAO();
		Entidad e = eDAO.buscarEntidadPorIdentificacion(identificacion);

		String stringQuery = "SELECT c FROM VhNovacreditCotizacion c where 1=1 ";
		String valoresWhereQuery = "";

		// Agregamos la parte del where las opciones de busqueda
		if (numeroEndoso.length() > 0)
			valoresWhereQuery += " AND c.numeroEndoso=:numeroEndoso";
		if (fecha1.length() > 0 && fecha2.length() > 0)
			valoresWhereQuery += " AND c.fecha BETWEEN :startDate AND :endDate";
		if (e.getId() != null && !identificacion.equals(""))
			valoresWhereQuery += " AND c.entidad =:entidad";

		stringQuery = stringQuery + valoresWhereQuery + " ORDER BY c.fecha ASC";

		query = getEntityManager().createQuery(stringQuery, VhNovacreditCotizacion.class);

		// Agregamos los parametros del buscador
		if (fecha1.length() > 0 && fecha2.length() > 0)
			query.setParameter("startDate", fecha1).setParameter("endDate", fecha2);
		if (numeroEndoso.length() > 0)
			query.setParameter("numeroEndoso", numeroEndoso);
		if (e.getId() != null && !identificacion.equals(""))
			query.setParameter("entidad", e);

		results = query.getResultList();
		return results;
	}

}
