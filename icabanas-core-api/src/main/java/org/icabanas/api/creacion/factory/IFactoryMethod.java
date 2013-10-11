package org.icabanas.api.creacion.factory;

/**
 * <b>Patrón de diseño Factory Method</b>: Define una interfaz para crear 
 * un objeto, permitiendo a las subclases decidir qué clase instanciar.
 * <br/><br/>
 * 
 * <b>Responsabilidad</b> Definición abstracta del "Creador": <br/>
 * <br/>
 * <ul>
 * <li>Declara el método de la factoría de objetos, el cual devuelve un 
 * objeto del tipo definido en el parámetro.
 * </li> 
 * </ul>
 * 
 * @author f009994r
 *
 * @param <T>
 * 		Tipo del objeto a crear.
 */
public interface IFactoryMethod<T> {

	public T crear();
}
