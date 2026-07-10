package com.rainforestkongra.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

import com.rainforestkongra.RainforestKongraMod;
import com.rainforestkongra.entity.KongraEntity;

public class KongraRenderer extends MobEntityRenderer<KongraEntity, PlayerEntityModel<KongraEntity>> {
    private static final Identifier TEXTURE =
        new Identifier(RainforestKongraMod.MOD_ID, "textures/entity/kongra.png");

    public KongraRenderer(EntityRendererFactory.Context context) {
        super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER), false), 1.4f);
        this.shadowRadius = 1.2f;
    }

    @Override
    public Identifier getTexture(KongraEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(KongraEntity entity, net.minecraft.client.util.math.MatrixStack matrices, float amount) {
        matrices.scale(2.2f, 2.2f, 2.2f);
        super.scale(entity, matrices, amount);
    }
}