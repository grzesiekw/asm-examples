package com.gw.asm.examples.printer;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.*;

public class ClassWithMethodsPrinter extends ClassVisitor {

	private final StringBuilder sb = new StringBuilder();

	public ClassWithMethodsPrinter() {
		super(ASM4);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		sb.append("Class: " + name);

		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		sb.append("Method: " + name + desc);

		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	public String getClassDescription() {
		return sb.toString();
	}
}