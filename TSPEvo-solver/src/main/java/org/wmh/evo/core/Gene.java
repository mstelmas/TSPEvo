package org.wmh.evo.core;

import org.wmh.evo.core.factories.CopyFactory;

public interface Gene<T, G extends Gene<T, G>> extends CopyFactory<G> {
    T getValue();
    G newInstance(final T value);
}
