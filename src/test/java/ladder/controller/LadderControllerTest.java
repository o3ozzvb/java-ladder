package ladder.controller;

import ladder.domain.Ladder;
import ladder.domain.Name;
import ladder.domain.Names;
import ladder.domain.Results;
import ladder.utils.LadderUtils;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LadderControllerTest {

    @Test
    public void 실행결과_개수가_참여자수와_동일한지() {
        LadderController controller = new LadderController();
        assertThatThrownBy(() -> controller.checkValidationOfResultsNumber(4, 5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("실행 결과의 갯수는 참여하는 사람의 수와 동일해야 합니다.");
    }

    @Test
    public void 실행결과_테스트_1명() {
        Ladder ladder = LadderUtils.createLadder();
        Names names = Names.of("pobi,honux,crong,jk");
        Results results = Results.of("꽝,3000,꽝,5000");

        LadderController controller = new LadderController(names, ladder, results);

        assertThat(controller.getExecutionResult("pobi")).isEqualTo("꽝");
        assertThat(controller.getExecutionResult("honux")).isEqualTo("5000");
        assertThat(controller.getExecutionResult("crong")).isEqualTo("꽝");
        assertThat(controller.getExecutionResult("jk")).isEqualTo("3000");
    }

    @Test
    public void 실행결과_테스트_전체() {
        Ladder ladder = LadderUtils.createLadder();
        Names names = Names.of("pobi,honux,crong,jk");
        Results results = Results.of("꽝,3000,꽝,5000");

        LadderController controller = new LadderController(names, ladder, results);
        Map<Name, String> ladderResult = controller.getExecutionResultAll();
        assertThat(ladderResult.get(new Name("pobi"))).isEqualTo("꽝");
        assertThat(ladderResult.get(new Name("honux"))).isEqualTo("5000");
        assertThat(ladderResult.get(new Name("crong"))).isEqualTo("꽝");
        assertThat(ladderResult.get(new Name("jk"))).isEqualTo("3000");
    }

    @Test
    public void 실행결과_테스트_전체2() {
        Ladder ladder = LadderUtils.createLadder2();
        Names names = Names.of("pobi,honux,crong,jk");
        Results results = Results.of("꽝,3000,꽝,5000");

        LadderController controller = new LadderController(names, ladder, results);
        Map<Name, String> ladderResult = controller.getExecutionResultAll();
        assertThat(ladderResult.get(new Name("pobi"))).isEqualTo("꽝");
        assertThat(ladderResult.get(new Name("honux"))).isEqualTo("3000");
        assertThat(ladderResult.get(new Name("crong"))).isEqualTo("꽝");
        assertThat(ladderResult.get(new Name("jk"))).isEqualTo("5000");
    }
}