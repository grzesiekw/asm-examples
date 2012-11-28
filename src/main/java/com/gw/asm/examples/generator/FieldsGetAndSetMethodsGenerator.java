package com.gw.asm.examples.generator;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.*;

public class FieldsGetAndSetMethodsGenerator extends ClassVisitor {

	private String className;

	public FieldsGetAndSetMethodsGenerator(ClassVisitor cv) {
		super(ASM4, cv);
	}

	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		this.className = name;
		super.visit(version, access, name, signature, superName, interfaces);
	}

	@Override
	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		// generate getter
		final String methodName = Character.toUpperCase(name.charAt(0)) + (name.length() > 1 ? name.substring(1) : "");

		final MethodVisitor getterMv = cv.visitMethod(ACC_PUBLIC, "get" + methodName, "()" + desc, null, null);
		getterMv.visitCode();
		getterMv.visitVarInsn(ALOAD, 0);
		getterMv.visitFieldInsn(GETFIELD, className, name, desc);
		getterMv.visitInsn(IRETURN);
		getterMv.visitMaxs(1, 1);
		getterMv.visitEnd();

		// generate setter
		final MethodVisitor setterMv = cv.visitMethod(ACC_PUBLIC, "set" + methodName, "(" + desc + ")V", null, null);
		setterMv.visitCode();
		setterMv.visitVarInsn(ALOAD, 0);
		setterMv.visitVarInsn(ILOAD, 1);
		setterMv.visitFieldInsn(PUTFIELD, className, name, desc);
		setterMv.visitInsn(RETURN);
		setterMv.visitMaxs(2, 2);
		setterMv.visitEnd();

		return super.visitField(access, name, desc, signature, value);
	}

}