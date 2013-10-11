package org.icabanas.jee.api.integracion.dao;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(value=PruebaTestMetodoPrivado.class)
public class PruebaTest {

	@Test
	public void test() throws Exception{
		PruebaTestMetodoPrivado test = PowerMockito.spy(new PruebaTestMetodoPrivado());
		PowerMockito.doReturn(5).when(test, "_prueba");
		
		int resultadoTest = test.prueba();
		
		PowerMockito.verifyPrivate(test, VerificationModeFactory.times(1)).invoke("_prueba");
		Assert.assertThat(resultadoTest, IsEqual.equalTo(5));
	}
}
