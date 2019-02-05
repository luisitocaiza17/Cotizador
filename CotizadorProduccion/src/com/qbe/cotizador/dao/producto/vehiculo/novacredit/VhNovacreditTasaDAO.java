package com.qbe.cotizador.dao.producto.vehiculo.novacredit;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.VhNovacreditTasa;
import com.qbe.cotizador.model.VhNovacreditTipoCobertura;
import com.qbe.cotizador.model.VhNovacreditTipoTasa;

public class VhNovacreditTasaDAO extends EntityManagerFactoryDAO<VhNovacreditTasa> {

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

	public VhNovacreditTasaDAO() {
		super(VhNovacreditTasa.class);
	}

	public List<VhNovacreditTasa> buscarTodos() {
		return getEntityManager().createNamedQuery("VhNovacreditTasa.buscarTodos").getResultList();
	}

	public VhNovacreditTasa buscarPorId(String id) {
		VhNovacreditTasa novaTasa = new VhNovacreditTasa();
		List<VhNovacreditTasa> query = getEntityManager().createNamedQuery("VhNovacreditTasa.buscarPorId").setParameter("id", id)
				.getResultList();
		if (!query.isEmpty())
			novaTasa = query.get(0);
		return novaTasa;
	}

	public VhNovacreditTasa buscarPorTipoCoberturaTipoTasa(VhNovacreditTipoCobertura vhNovaTipoCobertura,
			VhNovacreditTipoTasa vhNovaTipoTasa) {
		VhNovacreditTasa novaTasa = new VhNovacreditTasa();
		List<VhNovacreditTasa> query = getEntityManager().createNamedQuery("VhNovacreditTasa.buscarPorTipoCoberturaTipoTasa")
				.setParameter("vhNovacreditTipoCobertura", vhNovaTipoCobertura).setParameter("vhNovacreditTipoTasa", vhNovaTipoTasa)
				.getResultList();
		if (!query.isEmpty())
			novaTasa = query.get(0);
		return novaTasa;
	}

}
