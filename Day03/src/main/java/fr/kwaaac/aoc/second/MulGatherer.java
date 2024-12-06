package fr.kwaaac.aoc.second;

import java.util.function.Supplier;
import java.util.regex.MatchResult;
import java.util.stream.Gatherer;

/**
 * Un gatherer qui regroupe tous les "mul" qui sont accepté par des do()
 */
public class MulGatherer implements Gatherer<MatchResult, MulState, MatchResult> {

    @Override
    public Supplier<MulState> initializer() {
        return MulState::new;
    }

    /**
     * Traite si le match est une instuction, si oui, elle change l'état du gatherer
     * Sinon, si l'instruction est valide, alors elle est poussé dans le downstream
     *
     * @return true
     */
    @Override
    public Integrator<MulState, MatchResult, MatchResult> integrator() {
        return Integrator.of(
                (instruction, match, downstream) -> {
                    // On trouve une instruction
                    if (!match.group().contains("mul")) {
                        var newInstruction = match.group().equals("do()");
                        instruction.setInstruction(newInstruction);
                        return true;
                    }

                    // Si do() on ajoute au downstream le mul
                    if (instruction.instruction()) {
                        downstream.push(match);
                    }

                    return true;
                }
        );
    }
}
