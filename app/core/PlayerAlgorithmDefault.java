package core;

import java.util.List;

import com.ekino.animation.devoxx.RestPlayerAlgorithm;
import com.ekino.animation.devoxx.model.World;
import com.ekino.animation.devoxx.model.actions.Action;
import com.ekino.animation.devoxx.model.actions.ActionList;
import com.ekino.animation.devoxx.model.actions.AttackAction;
import com.ekino.animation.devoxx.model.actions.Direction;
import com.ekino.animation.devoxx.model.actions.Move;
import com.ekino.animation.devoxx.model.actions.MoveAction;
import com.ekino.animation.devoxx.model.army.Ship;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public class PlayerAlgorithmDefault implements RestPlayerAlgorithm {

	/**
	 * Compléter par votre pseudo
	 * 
	 * @return votre pseudo
	 */
	@Override
	public String ping() {
		return "YourPseudo";
	}

	/**
	 * {@inheritDoc RestPlayerAlgorithm}
	 */
	@Override
	public ActionList turn(World world) {
		return ActionList.valueOf(ImmutableList.<Action>of());
	}
}
