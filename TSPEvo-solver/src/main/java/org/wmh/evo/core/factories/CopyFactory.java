package org.wmh.evo.core.factories;

@FunctionalInterface
public interface CopyFactory<T> {
    T copy();
}
