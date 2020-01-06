package drmeepster.appraisal.command;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

import java.util.ArrayList;
import java.util.List;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import drmeepster.appraisal.context.ItemAppraisalContext;
import drmeepster.appraisal.quack.item.AppraisalItem;
import net.minecraft.command.arguments.ItemStackArgumentType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemSubCommand extends AbstractSubCommand{

	public static final Identifier ID = new Identifier("minecraft", "item");
	
	public ItemSubCommand(){
		super(ID);
	}
	
	private static int executeItemAbstract(CommandContext<ServerCommandSource> ctx, int count)
		throws CommandSyntaxException{
		
		ItemStack stack = ItemStackArgumentType.getItemStackArgument(ctx, "item").createStack(count, false);
		
		List<Text> texts = ((AppraisalItem) stack.getItem()).getAppraisalManager().getAppraisal(
			new ItemAppraisalContext.Builder().setStack(stack).build()
		);
		
		return SubCommand.appraise(ctx, texts);
	}

	@Override
	protected List<ArgumentBuilder<ServerCommandSource, ?>> generateBranches(){
		ArrayList<ArgumentBuilder<ServerCommandSource, ?>> out = new ArrayList<>(1);
		
		out.add(literal("abstract")
			.then(argument("item", ItemStackArgumentType.itemStack())
				.executes(ctx -> executeItemAbstract(ctx, 1))
				.then(argument("count", IntegerArgumentType.integer())
					.executes(ctx -> 
						executeItemAbstract(ctx, IntegerArgumentType.getInteger(ctx, "count"))
					)
				)
			)
		);
		
		return out;
	}

}
