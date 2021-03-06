package drmeepster.appraisal.quack.item;

import drmeepster.appraisal.manager.item.ItemAppraisalManager;
import drmeepster.appraisal.quack.Appraisable;

/**
 * Mixin interface for <code>ItemMixin</code>.
 */
public interface AppraisalItem extends Appraisable{

	@Override
	public ItemAppraisalManager<?> getAppraisalManager();
}
