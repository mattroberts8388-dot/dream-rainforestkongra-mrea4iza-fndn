package com.rainforestkongra.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.FrogEntityModel;
import net.minecraft.util.Identifier;

import com.rainforestkongra.RainforestKongraMod;
import com.rainforestkongra.entity.PoisonFrogEntity;

public class PoisonFrogRenderer extends MobEntityRenderer<PoisonFrogEntity, FrogEntityModel<PoisonFrogEntity>> {
    private static final Identifier TEXTURE =
        new Identifier(RainforestKongraMod.MOD_ID, "textures/entity/poison_frog.png");

    public PoisonFrogRenderer(EntityRendererFactory.Context context) {
        super(context, new FrogEntityModel<>(context.getPart(EntityModelLayers.FROG)), 0.3f);
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTexture(PoisonFrogEntity entity) {
        return TEXTURE;
    }
}