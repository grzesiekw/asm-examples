package com.gw.asm.examples.generator;

import static org.fest.assertions.Assertions.*;

import java.lang.reflect.*;

import org.junit.*;
import org.objectweb.asm.*;

public class FieldsGetAndSetMethodsGeneratorTest {

	@Test
	public void shouldGenerateGetAndSetMethods() throws Exception {
		final ClassReader cr = new ClassReader("com.gw.asm.examples.generator.Simple");

		final ClassWriter cw = new ClassWriter(cr, 0);
		cr.accept(new FieldsGetAndSetMethodsGenerator(cw), 0);

		final int newFieldValue = 100;
		final Object object = createClassInstance(cw);

		final Method setAMethod = object.getClass().getMethod("setA", int.class);
		setAMethod.invoke(object, newFieldValue);

		final Method getAMethod = object.getClass().getMethod("getA");
		final Object aFieldValue = getAMethod.invoke(object);

		assertThat(aFieldValue).isEqualTo(100);
	}

	private Object createClassInstance(final ClassWriter cw) throws Exception {
		final SimpleClassLoader scl = new SimpleClassLoader(getClass().getClassLoader());
		final Class<?> clazz = scl.load(cw.toByteArray());

		final Object object = clazz.newInstance();
		return object;
	}

	class SimpleClassLoader extends ClassLoader {

		public SimpleClassLoader(ClassLoader parent) {
			super(parent);
		}

		public Class<?> load(byte[] classDefinition) {
			return defineClass("com.gw.asm.examples.generator.Simple", classDefinition, 0, classDefinition.length);
		}

	}

}