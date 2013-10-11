package org.icabanas.jee.api.integracion.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * <br/><br/>
 * <b>Responsabilidad</b> : Implementación de {@link IPaginador}  
 * <br/>
 * <br/>
 * <ul>
 * <li></li> 
 * </ul>
 *
 * @author f009994r
 *
 */
public class Pagina<T> implements IPaginador<T> {

	
	private static final int NUMERO_REGISTROS_POR_PAGINA_POR_DEFECTO = 5;
	
	private int pagina = 1;
	private int numeroRegistrosPorPagina;
	private int numeroTotalRegistros;
	private List<T> datos = new ArrayList<T>();

	private IFiltro filtro;

	public Pagina() {
		this.numeroRegistrosPorPagina = NUMERO_REGISTROS_POR_PAGINA_POR_DEFECTO;
	}

	public Pagina(int paginaActual, int numRegPP) {
		this.pagina = paginaActual;
		this.numeroRegistrosPorPagina = numRegPP;
	}

	public Pagina(int paginaActual, int numRegPP, IFiltro filtro) {
		this(paginaActual,numRegPP);
		this.filtro = filtro;
	}

	@Override
	public int getPagina() {
		return pagina;
	}

	@Override
	public int getNumeroRegistrosPorPagina() {
		return numeroRegistrosPorPagina;
	}

	@Override
	public int getNumeroTotalPaginas() {
		if (numeroRegistrosPorPagina > 0)
			return (int) Math.ceil(numeroTotalRegistros/numeroRegistrosPorPagina) + (numeroTotalRegistros % numeroRegistrosPorPagina == 0 ? 0 : 1);
		
		return 1;
	}

	@Override
	public int getNumeroTotalRegistros() {
		return numeroTotalRegistros;
	}

	@Override
	public List<T> getDatos() {
		return datos ;
	}

	public void setDatos(List<T> datos) {
		this.datos = datos;
	}

	public void setNumeroTotalRegistros(int numeroTotalRegistros) {
		this.numeroTotalRegistros = numeroTotalRegistros;
	}

	@Override
	public int getPrimerRegistro() {
		return (pagina - 1) * numeroRegistrosPorPagina;
	}

	@Override
	public void setFiltro(IFiltro filtro) {
		this.filtro = filtro;
	}

	@Override
	public void setPagina(int pagina) {
		this.pagina = pagina;
	}

	@Override
	public void setNumeroTotalRegistrosPorPagina(int numeroRegistrosPorPagina) {
		this.numeroRegistrosPorPagina = numeroRegistrosPorPagina;
	}

	@Override
	public IFiltro getFiltro() {
		return this.filtro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filtro == null) ? 0 : filtro.hashCode());
		result = prime * result + numeroRegistrosPorPagina;
		result = prime * result + pagina;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagina other = (Pagina) obj;
		if (filtro == null) {
			if (other.filtro != null)
				return false;
		} else if (!filtro.equals(other.filtro))
			return false;
		if (numeroRegistrosPorPagina != other.numeroRegistrosPorPagina)
			return false;
		if (pagina != other.pagina)
			return false;
		return true;
	}

	
	
}
