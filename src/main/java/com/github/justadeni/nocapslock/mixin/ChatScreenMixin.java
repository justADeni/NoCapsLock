package com.github.justadeni.nocapslock.mixin;

import net.minecraft.client.gui.widget.TextFieldWidget;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TextFieldWidget.class)
public abstract class ChatScreenMixin {

    @Shadow public abstract void write(String text);

    @Inject(method = "charTyped", at = @At("HEAD"), cancellable = true)
    private void onCharTyped(char chr, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        this.write(String.valueOf(((modifiers & GLFW.GLFW_MOD_SHIFT) == 0) ? Character.toLowerCase(chr) : Character.toUpperCase(chr)));
        cir.setReturnValue(true);
    }

}
