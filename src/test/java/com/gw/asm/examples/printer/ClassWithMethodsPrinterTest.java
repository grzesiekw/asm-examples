package com.gw.asm.examples.printer;

import static org.fest.assertions.Assertions.*;

import java.io.*;

import org.junit.*;
import org.objectweb.asm.*;

public class ClassWithMethodsPrinterTest {

	@Test
	public void shouldPrintClassAndMethods() throws IOException {
		final ClassWithMethodsPrinter classWithMethodsPrinter = new ClassWithMethodsPrinter();
		final ClassReader cr = new ClassReader("com.gw.asm.examples.printer.Simple");

		cr.accept(classWithMethodsPrinter, 0);
		final String description = classWithMethodsPrinter.getClassDescription();

		assertThat(description).contains("com/gw/asm/examples/printer/Simple").contains("method");
	}

}