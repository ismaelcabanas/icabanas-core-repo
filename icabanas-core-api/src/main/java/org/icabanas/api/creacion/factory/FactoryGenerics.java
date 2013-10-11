package org.icabanas.api.creacion.factory;

public class FactoryGenerics<T> {

	  // T type MUST have a default constructor
	  private final Class<T> type;

	  public FactoryGenerics(Class<T> type) {

	    this.type = type;
	  }

	  /**
	   * Use the factory to get the next instance.
	   */
	  public T getInstance() {

	    try {
	      // assume type is a public class
	      return type.newInstance();
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }

	  /**
	   * Create the factory. Note that V can be T, but to demonstrate that
	   * generic method are not generic classes, I've called it V and not T.
	   * In using this method V becomes T.
	   */
	  public static <V> FactoryGenerics<V> getInstance(Class<V> type) {

	    return new FactoryGenerics<V>(type);
	  }
}