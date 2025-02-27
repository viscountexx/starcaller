package folk.sisby.starcaller.mixin.client;

import folk.sisby.starcaller.Starcaller;
import folk.sisby.starcaller.client.StarcallerClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(InGameHud.class)
public abstract class MixinInGameHud {
    @ModifyArg(method = "renderCrosshair", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawTexture(Lnet/minecraft/util/Identifier;IIIIII)V"), index = 0)
    public Identifier useThrowCrosshair(Identifier original) {
        if (MinecraftClient.getInstance().player.getMainHandStack().isOf(Starcaller.SPEAR) || MinecraftClient.getInstance().player.getOffHandStack().isOf(Starcaller.SPEAR)) {
            return StarcallerClient.CROSSHAIR_TEXTURE;
        }
        return original;
    }
}
