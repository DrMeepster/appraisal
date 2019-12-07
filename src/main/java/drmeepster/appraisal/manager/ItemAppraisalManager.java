package drmeepster.appraisal.manager;

import drmeepster.appraisal.context.ItemAppraisalContext;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemAppraisalManager<T extends Item> extends AbstractAppraisalManager<T, ItemAppraisalContext>{

	public static final String KEY_TYPE = "appraisal.item";
	
	public ItemAppraisalManager(T item){
		super(item, Registry.ITEM, KEY_TYPE);
	}
}
