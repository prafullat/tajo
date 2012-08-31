package tajo.datum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * 
 * @author Hyunsik Choi
 *
 */
public class TestLongDatum {

	@Test
	public final void testType() {
		Datum d = DatumFactory.createLong(1l);
		assertEquals(d.type(), DatumType.LONG);
	}

	@Test
	public final void testAsInt() {
		Datum d = DatumFactory.createLong(5l);
		assertEquals(5,d.asInt());
	}

	@Test
	public final void testAsLong() {
		Datum d = DatumFactory.createLong(5l);
		assertEquals(5l,d.asLong());		
	}

	@Test
	public final void testAsFloat() {
		Datum d = DatumFactory.createLong(5l);
		assertTrue(5.0f == d.asFloat());
	}

	@Test
	public final void testAsDouble() {
		Datum d = DatumFactory.createLong(5l);
		assertTrue(5.0d == d.asDouble());
	}

	@Test
	public final void testAsChars() {
		Datum d = DatumFactory.createLong(5l);
		assertEquals("5", d.asChars());
	}
	
	@Test
  public final void testSize() {
    Datum d = DatumFactory.createLong(5l);
    assertEquals(8, d.size());
  }
}