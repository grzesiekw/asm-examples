package com.gw.asm.examples.profiler;

import static org.objectweb.asm.ClassWriter.*;

import java.io.*;
import java.lang.reflect.*;

import org.junit.*;
import org.objectweb.asm.*;

public class MethodsProfilerTest {

	@Test
	public void shouldProfileMethods() throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException,
			InvocationTargetException {
		final ClassReader cr = new ClassReader("com.gw.asm.examples.profiler.Simple");

		final ClassWriter cv = new ClassWriter(cr, COMPUTE_FRAMES | COMPUTE_MAXS);
		final MethodsProfiler mp = new MethodsProfiler(cv);

		cr.accept(mp, 0);

		final Class<?> classDefinition = new SimpleClassLoader(getClass().getClassLoader()).load(cv.toByteArray());

		final Object object = classDefinition.newInstance();
		final Method method = classDefinition.getMethod("method");

		method.invoke(object);
	}

	class SimpleClassLoader extends ClassLoader {

		public SimpleClassLoader(ClassLoader parent) {
			super(parent);
		}

		public Class<?> load(byte[] classDefinition) {
			return defineClass("com.gw.asm.examples.profiler.Simple", classDefinition, 0, classDefinition.length);
		}

	}
}
