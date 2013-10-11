package org.icabanas.jee.api.integracion.dao;

import java.util.List;


/**
 * Procesador de operaciones de consulta de entidades contra un repositorio de datos.
 * 
 * <br/><br/>
 * <b>Responsabilidad</b>:   
 * <br/>
 * <br/>
 * <ul>
 * <li>Realizar operaciones de consulta de una entidad contra un repositorio de datos (consultas paginadas, sin paginar, que devuelven un único registro).</li> 
 * </ul>
 *
 * @author f009994r
 *
 * @param <Entidad>
 */
public interface IProcesadorConsultas<Entidad> {
	
	/**
	 * Prepara al procesador de consultas para realizar una nueva consulta.
	 *  
	 * Este método se debe ejecutar después de cada consulta para poder realizar una nueva.
	 *  
	 */
	void nuevaConsulta();	

	/**
	 * Método que ejecuta una consulta.
	 * 
	 * El procesador de consultas queda preparado para la siguiente consulta.
	 * 
	 * @return
	 * 		Lista de entidades
	 */
	List<Entidad> ejecutarConsultaListado();
	
	/**
	 * Método que ejecuta una consulta paginada.
	 * 
	 * El procesador de consultas queda preparado para la siguiente consulta.
	 * 
	 * @param paginaActual
	 * @param numeroRegistrosPorPagina
	 * @return
	 * 		Lista de entidades
	 */
	List<Entidad> ejecutarConsultaListado(int paginaActual,
			int numeroRegistrosPorPagina);

	/**
	 * Método que ejecuta una consulta que devuelve un único registro.
	 * 
	 * El procesador de consultas queda preparado para la siguiente consulta.
	 * 
	 * @return
	 * 		La entidad devuelta por la consulta
	 */
	Entidad ejecutarConsultaUnicoRegistro();
	
	/**
	 * Método que cuenta los registros de una consulta.
	 * 
	 * El procesador de consultas queda preparado para la siguiente consulta.
	 * 
	 * @return
	 */
	int contarRegistros();
	
//	/**
//	 * Método que cuenta los registros de una consulta.
//	 * 
//	 * El método se debe utilizar para contar el número de registros en una consulta paginada, 
//	 * ya que el procesador de consultas no se inicializará.
//	 * 
//	 * @return
//	 */
//	int contarRegistrosPaginacion();

	/**
	 * Método que establece la clase de persistencia.
	 * 
	 * @param persistentClass
	 */
	void setPersistentClass(Class<Entidad> persistentClass);
	
	/**
	 * Método que aplica a la consulta la ordenación ascendente sobre un campo.
	 * 
	 * @param campo
	 * 		Campo de ordenación.
	 */
	void ordenarPorAsc(String campo);
	
	/**
	 * Método que aplica a la consulta la ordenación descendente sobre un campo.
	 * 
	 * @param campo
	 * 		Campo de ordenación.
	 */
	void ordenarPorDesc(String campo);
	
	/**
	 * Método que aplica a la consulta la condición igual que.
	 * 
	 * @param campo
	 * 		El campo de la entidad sobre la que aplica la condición.
	 * @param valor
	 * 		Valor de comparación
	 */
	void andIgualQue(String campo, Object valor);

	/**
	 * Método que aplica a la consulta la condición like
	 * 
	 * @param campo
	 * 		El campo de la entidad sobre la que aplica la condición.
	 * @param valor
	 * 		Valor de comparación
	 */
	void andLike(String campo, Object valor);
			

}
