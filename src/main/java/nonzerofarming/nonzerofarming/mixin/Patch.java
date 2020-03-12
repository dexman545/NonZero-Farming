package nonzerofarming.nonzerofarming.mixin;

import net.minecraft.block.*;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.IWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({BambooBlock.class, CactusBlock.class, ChorusPlantBlock.class, ChorusFlowerBlock.class, AbstractPlantStemBlock.class, SugarCaneBlock.class})
abstract class Patch extends Block implements Fertilizable {

    public Patch(Settings settings) {
        super(settings);
    }

    @Inject(method = "getStateForNeighborUpdate(Lnet/minecraft/block/BlockState;Lnet/minecraft/util/math/Direction;Lnet/minecraft/block/BlockState;Lnet/minecraft/world/IWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;",
    at = @At("HEAD"), cancellable = true
    )
    private void fixTick(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        }
    }
}
