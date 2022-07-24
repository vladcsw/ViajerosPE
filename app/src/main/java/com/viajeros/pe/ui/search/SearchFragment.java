package com.viajeros.pe.ui.search;

import android.Manifest;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableApkTooOldException;
import com.google.ar.core.exceptions.UnavailableArcoreNotInstalledException;
import com.google.ar.core.exceptions.UnavailableDeviceNotCompatibleException;
import com.google.ar.core.exceptions.UnavailableSdkTooOldException;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.viajeros.pe.R;
import com.viajeros.pe.databinding.FragmentDashboardBinding;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class SearchFragment extends Fragment {


    private ArSceneView arView;
    private Session session;
    private boolean shouldConfigureSession = false;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel dashboardViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Context context = getActivity();

        // View
        arView = (ArSceneView) rootView.findViewById(R.id.arView);
        // Solicitar permisos
        Dexter.withContext(context)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        setupSession(context);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(context, "Se necesitan los permisos para usar la cámara", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();
        initSceneView(context);


        return rootView;
    }

    private void initSceneView(Context context) {
        arView.getScene().addOnUpdateListener(this::onUpdate);
    }

    private void onUpdate(FrameTime frameTime) {
        Frame frame = arView.getArFrame();
        Collection<AugmentedImage> updateAugmentedImg = frame.getUpdatedTrackables(AugmentedImage.class);
        for(AugmentedImage image:updateAugmentedImg){
            if(image.getTrackingState() == TrackingState.TRACKING){
                if(image.getName().equals("7inuKbrmhGRmKFm618gf.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/7inuKbrmhGRmKFm618gf.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
                else if(image.getName().equals("B0nrff25IkXk5rk609mm.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/B0nrff25IkXk5rk609mm.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
                else if(image.getName().equals("Fuk8QywCImTuJE2Sb5Ov.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/Fuk8QywCImTuJE2Sb5Ov.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
                else if(image.getName().equals("MUo7eMRS2xOKTpfoc5KZ.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/MUo7eMRS2xOKTpfoc5KZ.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
                else if(image.getName().equals("OvvsDdy17RcF0P1eJiHb.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/OvvsDdy17RcF0P1eJiHb.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
                else if(image.getName().equals("YIh5e6LR1l0gKa345LFw.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/YIh5e6LR1l0gKa345LFw.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
                else if(image.getName().equals("cjQVu7xuSuub1Dj0q72o.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/cjQVu7xuSuub1Dj0q72o.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
                else if(image.getName().equals("hiyr56OAzCByvBTKJxEM.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/hiyr56OAzCByvBTKJxEM.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
                else if(image.getName().equals("vQrN7qFRZLTH3atAP21d.jpeg")){
                    Context context = getActivity();
                    MyARNode node = new MyARNode(context, "https://raw.githubusercontent.com/CarlosMestas/glb_repository/main/db2/vQrN7qFRZLTH3atAP21d.glb");
                    node.setImage(image);
                    arView.getScene().addChild(node);
                }
            }
        }
    }

    private void setupSession(Context context){
        if(session == null){
            try{
                session = new Session(context);
            } catch (UnavailableDeviceNotCompatibleException | UnavailableSdkTooOldException | UnavailableArcoreNotInstalledException | UnavailableApkTooOldException e) {
                e.printStackTrace();
            }
            shouldConfigureSession = true;
        }
        if(shouldConfigureSession){
            configSession(context);
            shouldConfigureSession = false;
            arView.setupSession(session);
        }
        try {
            session.resume();
            arView.resume();
        } catch (CameraNotAvailableException e) {
            e.printStackTrace();
            session = null;
            return;
        }
    }

    private void configSession(Context context) {
        Config config = new Config(session);
        if(!buildDatabase(config, context)){
            Toast.makeText(context, "Error en la base de datos", Toast.LENGTH_SHORT).show();
        }
        config.setUpdateMode(Config.UpdateMode.LATEST_CAMERA_IMAGE);
        session.configure(config);
    }

    private boolean buildDatabase(Config config, Context context) {
        /*
        AugmentedImageDatabase augmentedImageDatabase;
        String[] names = new String[]{
                "7inuKbrmhGRmKFm618gf",
                "B0nrff25IkXk5rk609mm",
                "Fuk8QywCImTuJE2Sb5Ov",
                "MUo7eMRS2xOKTpfoc5KZ",
                "OvvsDdy17RcF0P1eJiHb",
                "YIh5e6LR1l0gKa345LFw",
                "cjQVu7xuSuub1Dj0q72o",
                "hiyr56OAzCByvBTKJxEM",
                "vQrN7qFRZLTH3atAP21d"};
        for(int i=0; i < 9; ++i){
            Bitmap bitmap = loadImage2(context, names[i]);
            if(bitmap == null)
                return false;
            augmentedImageDatabase = new AugmentedImageDatabase(session);
            augmentedImageDatabase.addImage(names[i].toString(), bitmap);
            config.setAugmentedImageDatabase(augmentedImageDatabase);
        }
        return true;
         */
        AugmentedImageDatabase augmentedImageDatabase;
        try{
            InputStream inputStream = context.getAssets().open("edmtdev.imgdb");
            augmentedImageDatabase = AugmentedImageDatabase.deserialize(session, inputStream);
            config.setAugmentedImageDatabase(augmentedImageDatabase);
            return true;
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }

    /*
        private boolean buildDatabase(Config config, Context context) {
        AugmentedImageDatabase augmentedImageDatabase;
        Bitmap bitmap = loadImage(context);
        if(bitmap == null)
            return false;
        augmentedImageDatabase = new AugmentedImageDatabase(session);
        augmentedImageDatabase.addImage("7inuKbrmhGRmKFm618gf", bitmap);
        config.setAugmentedImageDatabase(augmentedImageDatabase);
        return true;
    }
    * */
    private Bitmap loadImage(Context context) {
        try {
            InputStream is = context.getAssets().open("7inuKbrmhGRmKFm618gf.jpeg");
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Bitmap loadImage2(Context context, String jpeg) {
        try {
            InputStream is = context.getAssets().open("7inuKbrmhGRmKFm618gf.jpeg");
            return BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void onResume() {
        super.onResume();
        Context context = getActivity();
        Dexter.withContext(context)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        setupSession(context);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                        Toast.makeText(context, "Se necesitan los permisos para usar la cámara", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                    }
                }).check();
    }

    @Override
    public void onPause() {
        super.onPause();
        if(session != null){
            arView.pause();
            session.pause();
        }
    }

    /*
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    */
}

/*
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
* */