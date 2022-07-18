package com.viajeros.pe.ui.search;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.viajeros.pe.R;
import com.viajeros.pe.databinding.FragmentDashboardBinding;

public class SearchFragment extends Fragment {

    private FragmentDashboardBinding binding;

    private ArFragment fragment;
    private ModelRenderable modelRenderable;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel dashboardViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Context context = getActivity();
        fragment = (ArFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
        setUpModel(context);
        setUpPlane(context);

        return root;
    }

    private void setUpPlane(Context context) {
        fragment.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(fragment.getArSceneView().getScene());
            createModel(anchorNode, context);
        }));

    }

    private void createModel(AnchorNode anchorNode, Context context) {
        TransformableNode node = new TransformableNode(fragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();
    }

    private void setUpModel(Context context) {
        String MODEL_URL = "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/extra.glb";
        ModelRenderable.builder()
                .setSource(
                        context,
                        RenderableSource.builder().setSource(
                                        context,
                                Uri.parse(MODEL_URL),
                                RenderableSource.SourceType.GLB)
                                .setScale(0.9f)
                                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                                .build())
                .setRegistryId(MODEL_URL)
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(context, "Can't load the model", Toast.LENGTH_LONG).show();
                    Log.d("Test", "Can't load the model");
                    return null;
                });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}