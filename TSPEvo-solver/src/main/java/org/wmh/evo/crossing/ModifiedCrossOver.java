package org.wmh.evo.crossing;

import org.wmh.evo.core.Chromosome;
import org.wmh.evo.core.Gene;

import java.util.*;

//TODO: refactor!
public class ModifiedCrossOver<G extends Gene<?, G>> implements CrossOverStrategy<G> {
    private static final Random random = new Random();

    @Override
    public Chromosome<G> cross(Chromosome<G> chromosome1, Chromosome<G> chromosome2) {
        final int size = chromosome1.getGenes().size();
        final int cutpoint = random.nextInt(size);
        final List<G> genes = new ArrayList<>(chromosome1.getGenes());

        Map<G, Boolean> map = new HashMap<>();

        for (int i = 0; i < cutpoint; i++) {
            map.put(chromosome1.getGenes().get(i), true);
        }

        for (int i = cutpoint, j = 0; (i < size && j < size); j++) {
            if (map.get(chromosome2.getGenes().get(j)) == null) {
                genes.set(i++, chromosome2.getGenes().get(j));
                map.put(chromosome2.getGenes().get(j), true);
            }
        }

        return chromosome1.newInstance(genes);
    }
}
