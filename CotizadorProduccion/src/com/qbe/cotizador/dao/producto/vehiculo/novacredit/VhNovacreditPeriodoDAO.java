package com.qbe.cotizador.dao.producto.vehiculo.novacredit;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.VhNovacreditPeriodo;

public class VhNovacreditPeriodoDAO extends EntityManagerFactoryDAO<VhNovacreditPeriodo> {

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

	public VhNovacreditPeriodoDAO() {
		super(VhNovacreditPeriodo.class);
	}

	public List<VhNovacreditPeriodo> buscarTodos() {
		return getEntityManager().createNamedQuery("VhNovacreditPeriodo.buscarTodos").getResultList();
	}

	public VhNovacreditPeriodo buscarPorId(String id) {
		VhNovacreditPeriodo novaPeriodo = new VhNovacreditPeriodo();
		List<VhNovacreditPeriodo> query = getEntityManager().createNamedQuery("VhNovacreditPeriodo.buscarPorId")
				.setParameter("id", id).getResultList();
		if (!query.isEmpty())
			novaPeriodo = query.get(0);
		return novaPeriodo;
	}

	public VhNovacreditPeriodo buscarPorAnio(double anio) {
		VhNovacreditPeriodo novaPeriodo = new VhNovacreditPeriodo();
		List<VhNovacreditPeriodo> query = getEntityManager().createNamedQuery("VhNovacreditPeriodo.buscarPorAnio")
				.setParameter("anios", anio).getResultList();
		if (!query.isEmpty())
			novaPeriodo = query.get(0);
		return novaPeriodo;
	}

}
