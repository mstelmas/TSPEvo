package org.wmh.evo.crossing;

import org.apache.commons.lang3.tuple.Pair;
import org.wmh.evo.EvoUtils;
import org.wmh.evo.core.domain.Chromosome;
import org.wmh.evo.core.domain.Gene;

import java.util.ArrayList;
import java.util.List;

public class OrderCrossOver<G extends Gene<?, G>> implements CrossOverStrategy<G> {

    private EvoUtils evoUtils = new EvoUtils();

    @Override
    public Chromosome<G> cross(final Chromosome<G> chromosome1, final Chromosome<G> chromosome2) {
        final List<G> genes1 = new ArrayList<>(chromosome1.getGenes());
        final List<G> genes2 = new ArrayList<>(chromosome2.getGenes());

        final Pair<Integer, Integer> indicesBoundToSelect = evoUtils.generatePairOfUniqueIndices(0, genes1.size());
        final List<G> selectedSublistOfGenes = new ArrayList<>(genes1.subList(evoUtils.extractMin(indicesBoundToSelect), evoUtils.extractMax(indicesBoundToSelect) + 1));


        final List<G> mergedGenes = mergeGenesSublists(genes2, selectedSublistOfGenes, indicesBoundToSelect);

        assert mergedGenes.size() == genes1.size();
        assert mergedGenes.size() == genes2.size();

        return chromosome1.newInstance(mergedGenes);
    }

    private List<G> mergeGenesSublists(final List<G> genes, final List<G> selectedGenes, final Pair<Integer, Integer> indicesBoundToSelect) {

        final List<G> extractedGenes = extractGenesFromCutpoint(genes, evoUtils.extractMax(indicesBoundToSelect) + 1);

        for (G gene : extractedGenes) {
            if (!selectedGenes.contains(gene)) {
                selectedGenes.add(gene);
            }
        }

        assert selectedGenes.size() == genes.size();


        final int lastIndex = genes.size() - evoUtils.extractMin(indicesBoundToSelect);

        final ArrayList<G> gs = new ArrayList<>(selectedGenes.subList(lastIndex, genes.size()));
        gs.addAll(selectedGenes.subList(0, lastIndex));
        return gs;
    }

    private List<G> extractGenesFromCutpoint(final List<G> genes, final int cutpoint) {
        List<G> rightSubList = new ArrayList<>();
        if (cutpoint < genes.size()) {
            rightSubList.addAll(genes.subList(cutpoint, genes.size()));
        }

        final List<G> leftSubList = new ArrayList<G>(genes.subList(0, cutpoint));

        assert rightSubList.size() + leftSubList.size() == genes.size();
        rightSubList.addAll(leftSubList);

        assert rightSubList.size() == genes.size();
        return rightSubList;
    }
}
