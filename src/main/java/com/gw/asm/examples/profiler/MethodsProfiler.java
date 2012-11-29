package com.gw.asm.examples.profiler;

import org.objectweb.asm.*;

public class MethodsProfiler extends ClassVisitor {

	public MethodsProfiler(ClassVisitor cv) {
		super(Opcodes.ASM4, cv);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		final MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
		final MethodProfiler mp = new MethodProfiler(mv);
		return mp;
	}

}