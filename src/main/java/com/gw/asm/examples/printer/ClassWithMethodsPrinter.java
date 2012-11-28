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
		sb.append("Class: ").append(name).append("\n");

		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		sb.append("Method: ").append(name).append(desc).append("\n");
		return super.visitMethod(access, name, desc, signature, exceptions);
	}

	public String getClassDescription() {
		return sb.toString();
	}
}