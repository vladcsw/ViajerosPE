package com.viajeros.pe.ui.search;

import android.content.Context;
import android.net.Uri;

import com.google.ar.core.AugmentedImage;
import com.google.ar.core.Pose;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;

import java.util.concurrent.CompletableFuture;

public class MyARNode extends AnchorNode {

    private AugmentedImage image;
    private static CompletableFuture<ModelRenderable> modelRenderableCompletableFuture;

    public MyARNode(Context context, String MODEL_URL){
        if(modelRenderableCompletableFuture == null){
            modelRenderableCompletableFuture = ModelRenderable.builder()
                    .setSource(
                            context,
                            RenderableSource.builder().setSource(
                                    context,
                                    Uri.parse(MODEL_URL),
                                    RenderableSource.SourceType.GLB)
                                    .setScale(0.4f)
                                    .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                                    .build()
                            )
                    .setRegistryId(MODEL_URL)
                    .build();
        }
    }

    public void setImage(AugmentedImage image){
        this.image = image;
        if(!modelRenderableCompletableFuture.isDone()){
            CompletableFuture.allOf(modelRenderableCompletableFuture)
                    .thenAccept((Void aVoid) ->{
                        setImage(image);
                    }).exceptionally(throwable -> {
                        return null;
                    });
        }
        setAnchor(image.createAnchor((image.getCenterPose())));

        Node node = new Node();
        Pose pose = Pose.makeTranslation(0.0f, 0.0f, 0.08f);

        node.setParent(this);
        node.setLocalPosition(new Vector3(pose.tx(), pose.ty(), pose.tz()));
        node.setLocalRotation(new Quaternion(pose.qx(), pose.qy(), pose.qz(), pose.qw()));
        node.setRenderable(modelRenderableCompletableFuture.getNow(null));

    }

    public AugmentedImage getImage() {
        return image;
    }
}
