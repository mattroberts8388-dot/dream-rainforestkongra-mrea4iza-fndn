package com.rainforestkongra.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.util.Identifier;

import com.rainforestkongra.RainforestKongraMod;
import com.rainforestkongra.entity.JungleJaguarEntity;

public class JungleJaguarRenderer extends MobEntityRenderer<JungleJaguarEntity, OcelotEntityModel<JungleJaguarEntity>> {
    private static final Identifier TEXTURE =
        new Identifier(RainforestKongraMod.MOD_ID, "textures/entity/jungle_jaguar.png");

    public JungleJaguarRenderer(EntityRendererFactory.Context context) {
        super(context, new OcelotEntityModel<>(context.getPart(EntityModelLayers.OCELOT)), 0.5f);
        this.shadowRadius = 0.5f;
    }

    @Override
    public Identifier getTexture(JungleJaguarEntity entity) {
        return TEXTURE;
    }
}