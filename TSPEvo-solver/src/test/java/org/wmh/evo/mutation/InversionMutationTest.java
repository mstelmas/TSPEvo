package org.wmh.evo.mutation;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.wmh.evo.EvoUtils;
import org.wmh.evo.core.domain.Chromosome;
import org.wmh.evo.core.domain.Gene;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InversionMutationTest {
    private static final int INVERSION_LOWER_BOUND = 1;
    private static final int INVERSION_UPPER_BOUND = 4;
    private static final Pair<Integer, Integer> INVERSION_BOUND_PAIR = Pair.of(INVERSION_UPPER_BOUND, INVERSION_LOWER_BOUND);

    @Mock
    private EvoUtils evoUtils;

    @InjectMocks
    private InversionMutation<?> inversionMutation;

    @Mock
    private Gene gene1;

    @Mock
    private Gene gene2;

    @Mock
    private Gene gene3;

    @Mock
    private Gene gene4;

    @Mock
    private Gene gene5;

    @Mock
    private Chromosome chromosome1;

    @Mock
    private Chromosome chromosome2;

    @Before
    public void setUp() throws Exception {
        when(chromosome1.getGenes()).thenReturn(ImmutableList.of(gene1, gene2, gene3, gene4, gene5));
        when(chromosome1.newInstance(anyList())).thenAnswer(invocationOnMock -> {
            when(chromosome2.getGenes()).thenReturn(invocationOnMock.getArgument(0));
            return chromosome2;
        });

        when(evoUtils.generatePairOfUniqueIndices(anyInt(), anyInt())).thenReturn(INVERSION_BOUND_PAIR);
        when(evoUtils.extractMax(INVERSION_BOUND_PAIR)).thenReturn(INVERSION_UPPER_BOUND);
        when(evoUtils.extractMin(INVERSION_BOUND_PAIR)).thenReturn(INVERSION_LOWER_BOUND);
    }

    @Test
    public void shouldInversionMutate() throws Exception {
        when(evoUtils.generatePairOfUniqueIndices(anyInt(), anyInt())).thenReturn(INVERSION_BOUND_PAIR);

        final Chromosome mutatedChromosome = inversionMutation.mutate(chromosome1);

        assertThat(mutatedChromosome.getGenes()).containsSequence(gene1, gene5, gene4, gene3, gene2);
    }
}