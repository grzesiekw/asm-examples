package com.gw.asm.examples.profiler;

import static org.objectweb.asm.Opcodes.*;

import org.objectweb.asm.*;

public class MethodProfiler extends MethodVisitor {

	public MethodProfiler(MethodVisitor mv) {
		super(ASM4, mv);
	}

	@Override
	public void visitCode() {
		super.visitCode();
		mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
		mv.visitVarInsn(LSTORE, 1);
	}

	@Override
	public void visitInsn(int opcode) {
		if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
			mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
			mv.visitMethodInsn(INVOKESTATIC, "java/lang/System", "nanoTime", "()J");
			mv.visitVarInsn(LLOAD, 1);
			mv.visitInsn(LSUB);
			mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(J)V");
		}
		mv.visitInsn(opcode);
	}

}