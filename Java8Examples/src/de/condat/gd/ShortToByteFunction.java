package de.condat.gd;

@FunctionalInterface
public interface ShortToByteFunction {
	Byte applyAsByte(Short s);
}