package com.rainforestkongra;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

import com.rainforestkongra.client.KongraRenderer;
import com.rainforestkongra.client.JungleJaguarRenderer;
import com.rainforestkongra.client.PoisonFrogRenderer;
import com.rainforestkongra.client.RainforestToucanRenderer;

public class RainforestKongraClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(RainforestKongraMod.KONGRA, KongraRenderer::new);
        EntityRendererRegistry.register(RainforestKongraMod.JUNGLE_JAGUAR, JungleJaguarRenderer::new);
        EntityRendererRegistry.register(RainforestKongraMod.POISON_FROG, PoisonFrogRenderer::new);
        EntityRendererRegistry.register(RainforestKongraMod.RAINFOREST_TOUCAN, RainforestToucanRenderer::new);
    }
}