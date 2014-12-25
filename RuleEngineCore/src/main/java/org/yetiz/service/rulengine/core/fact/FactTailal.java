package org.yetiz.service.rulengine.core.fact;

import org.yetiz.service.rulengine.core.Intention;
import org.yetiz.service.rulengine.core.pattern.Pattern;

/**
 * Created by yeti on 2014/12/15.
 */
public interface FactTailal {


	<R extends Fact> R doTail(Pattern pattern);

	<R extends Fact> R setTail(Intention intention, Fact fact);

	<R extends Fact> R getTail(Intention intention);
}
