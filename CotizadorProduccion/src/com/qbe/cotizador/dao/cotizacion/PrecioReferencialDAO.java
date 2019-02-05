package com.qbe.cotizador.dao.cotizacion;

import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.qbe.cotizador.dao.producto.vehiculo.MarcaDAO;
import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.model.PrecioReferencial;

public class PrecioReferencialDAO extends EntityManagerFactoryDAO<PrecioReferencial>{

	@PersistenceContext(name="CotizadorWebPC", unitName = "CotizadorWebPU" )	
	private EntityManager em;
	
	@Override
	protected EntityManager getEntityManager() {
		if(em == null){
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
	
	public PrecioReferencialDAO() {
        super(PrecioReferencial.class);
    }
	
	public List<PrecioReferencial> buscarTodos(){
		return getEntityManager().createNamedQuery("PrecioReferencial.buscarTodos").getResultList();		
	}
	
	public PrecioReferencial buscarPorId(String id){
		PrecioReferencial precio = new PrecioReferencial();
		List<PrecioReferencial> query = getEntityManager().createNamedQuery("PrecioReferencial.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			precio =  query.get(0);
		return precio;
	}
	
	//metodo para buscar el precio de acuerdo a la marca, modelo y año del vehiculo
	public List<PrecioReferencial> buscarPrecio(String modeloc, String anio, String marca){
		MarcaDAO marcaDAO = new MarcaDAO();
		Marca marcac = new Marca();
		marcac = marcaDAO.buscarPorNombre(marca);
		String idmarca = marcac.getId();		
		String[] partes = modeloc.split(" ");
		String modelocc = partes[0].toString() +"%";
		
		List<PrecioReferencial> results = null;				
		TypedQuery<PrecioReferencial> query = getEntityManager().createQuery("SELECT pr FROM PrecioReferencial pr, Modelo mo, Marca ma "+ 
							"WHERE ma.id = mo.marca.id AND mo.id = pr.modelo.id AND mo.nombre LIKE :modelocc AND pr.ano = :anio AND ma.id = :idmarca", 
							PrecioReferencial.class).setParameter("modelocc", modelocc).setParameter("anio", anio).setParameter("idmarca", idmarca);
			results = query.getResultList(); 		
		return results;
	}
}
