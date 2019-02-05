package com.qbe.cotizador.dao.producto.vehiculo;

import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qbe.cotizador.entitymanagerfactory.EntityManagerFactoryDAO;
import com.qbe.cotizador.model.Marca;
import com.qbe.cotizador.model.Modelo;

public class ModeloDAO extends EntityManagerFactoryDAO<Modelo>{
	
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
	
	public ModeloDAO() {
        super(Modelo.class);
    }
	
	public List<Modelo> buscarTodos(){   
		return getEntityManager().createNamedQuery("Modelo.buscarTodos").getResultList();
	}
	
	public Modelo buscarPorId(String id){
		Modelo modelo = null;
		List<Modelo> query = getEntityManager().createNamedQuery("Modelo.buscarPorId").setParameter("id", id).getResultList();
		if(!query.isEmpty())
			modelo =  query.get(0);		
		return modelo;
	}
	
	public List<Modelo> buscarActivos(){
		return getEntityManager().createNamedQuery("Modelo.buscarActivos").setParameter("valorActivo", true).getResultList();
	}
	
	public Modelo buscarPorNombre(String nombre){
		Modelo modelo = new Modelo();
		List<Modelo> query = getEntityManager().createNamedQuery("Modelo.buscarPorNombre").setParameter("nombre", nombre).getResultList();
		if(!query.isEmpty())
			modelo =  query.get(0);
		return modelo;
	}
	
	public Modelo buscarPorMarcaYNombre(Marca marca, String nombre){
		Modelo modelo = new Modelo();
		List<Modelo> query = getEntityManager().createNamedQuery("Modelo.buscarPorMarcaYNombre").setParameter("marca", marca).setParameter("nombre", nombre.trim().toUpperCase()).getResultList();
		if(!query.isEmpty())
			modelo =  query.get(0);
		return modelo;
	}
	
	public Modelo buscarPorCodigoEnsurance(String mod_ensurance){
		Modelo modelo = new Modelo();
		List<Modelo> query = getEntityManager().createNamedQuery("Modelo.buscarPorCodigoEnsurance").setParameter("mod_ensurance", mod_ensurance).getResultList();
		if(!query.isEmpty())
			modelo =  query.get(0);
		return modelo;
	}
	
	public List<Modelo> buscarPorMarca(String marca){
		MarcaDAO marcaDAO = new MarcaDAO();
		return getEntityManager().createNamedQuery("Modelo.buscarPorMarca").setParameter("marca", marcaDAO.buscarPorId(marca)).getResultList();
	}
	
	
	public Modelo buscarPorNombreSimilar(String marca, String nombre){
		String retorno="";
		String query=" SELECT IFNULL(("+
				" SELECT id"+
				" FROM MODELO"+
				" WHERE nombre = '"+nombre+"' LIMIT 1"+
				" ), IFNULL(("+
				" SELECT id"+
				" FROM MODELO"+
				" WHERE nombre LIKE CONCAT ("+
				" '%'"+
				" ,'"+nombre+"'"+
				" ,'%'"+
				" )"+
				" AND marca_id = ("+
				" SELECT id"+
				" FROM MARCA"+
				" WHERE nombre = '"+marca+"'"+
				" ) LIMIT 1"+
				" ), IFNULL(("+
				" SELECT id"+
				" FROM MODELO"+
				" WHERE nombre LIKE CONCAT ("+
				" '%'"+
				" ,SUBSTRING_INDEX(SUBSTRING_INDEX('"+nombre+"', ' ', 1), ' ', - 1)"+
				" ,'%'"+
				" )"+
				" AND marca_id = ("+
				" SELECT id"+
				" FROM MARCA"+
				" WHERE nombre = '"+marca+"'"+
				" ) LIMIT 1"+
				" ), IFNULL(("+
				" SELECT id"+
				" FROM MODELO"+
				" WHERE nombre LIKE CONCAT ("+
				" '%'"+
				" ,CONCAT ("+
				" SUBSTRING_INDEX(SUBSTRING_INDEX('"+nombre+"', ' ', 1), ' ', - 1)"+
				" ,' '"+
				" ,SUBSTRING_INDEX(SUBSTRING_INDEX('"+nombre+"', ' ', 2), ' ', - 1)"+
				" )"+
				" ,'%'"+
				" )"+
				" AND marca_id = ("+
				" SELECT id"+
				" FROM MARCA"+
				" WHERE nombre = '"+marca+"'"+
				" ) LIMIT 1"+
				" ), ("+
				" SELECT id"+
				" FROM MODELO"+
				" WHERE nombre LIKE CONCAT ("+
				" '%'"+
				" ,CONCAT ("+
				" SUBSTRING_INDEX(SUBSTRING_INDEX('"+nombre+"', ' ', 1), ' ', - 1)"+
				" ,' '"+
				" ,SUBSTRING_INDEX(SUBSTRING_INDEX('"+nombre+"', ' ', 2), ' ', - 1)"+
				" ,' '"+
				" ,SUBSTRING_INDEX(SUBSTRING_INDEX('"+nombre+"', ' ', 3), ' ', - 1)"+
				" )"+
				" ,'%'"+
				" )"+
				" AND marca_id = ("+
				" SELECT id"+
				" FROM MARCA"+
				" WHERE nombre = '"+marca+"'"+
				" ) LIMIT 1"+
				" ))))) AS NUEVO_MODELO"+
				" FROM dual;";
		
		Query resultado = getEntityManager().createNativeQuery(query);
		List<Long> resul = resultado.getResultList();
		if(resul!=null&&resul.size()>0)
		if(resul.get(0)!=null)
			retorno =  resul.get(0).toString();

		
		Modelo modelRetorno=buscarPorId(retorno);
		
		if(modelRetorno==null)
			modelRetorno=new Modelo();
		
		return modelRetorno;
	}
	
}
