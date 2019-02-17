package ru.job4j.calculate;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test.
*
* @author Vasiliy Gasevskiy (gasevskyv@gmail.com)
* @version $Id$
* @since 0.1
*/
public class CalculateTest {
/**
* Test echo.
*/
	@Test
	public void whenTakeNameThenThreeEchoPlusName() {
		String input = "Vasiliy Gasevskiy";
		String expect = "Echo, echo, echo : Vasiliy Gasevskiy"; 
		Calculate calc = new Calculate();
		String result = calc.echo(input);
		assertThat(result, is(expect));
	}
 
}