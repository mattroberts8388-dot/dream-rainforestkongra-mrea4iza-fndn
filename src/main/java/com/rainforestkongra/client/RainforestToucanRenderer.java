package com.rainforestkongra.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.ParrotEntityModel;
import net.minecraft.util.Identifier;

import com.rainforestkongra.RainforestKongraMod;
import com.rainforestkongra.entity.RainforestToucanEntity;

public class RainforestToucanRenderer extends MobEntityRenderer<RainforestToucanEntity, ParrotEntityModel<RainforestToucanEntity>> {
    private static final Identifier TEXTURE =
        new Identifier(RainforestKongraMod.MOD_ID, "textures/entity/rainforest_toucan.png");

    public RainforestToucanRenderer(EntityRendererFactory.Context context) {
        super(context, new ParrotEntityModel<>(context.getPart(EntityModelLayers.PARROT)), 0.3f);
        this.shadowRadius = 0.3f;
    }

    @Override
    public Identifier getTexture(RainforestToucanEntity entity) {
        return TEXTURE;
    }
}