package com.viajeros.pe.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.Task;
import com.viajeros.pe.databinding.FragmentProfileBinding;
import com.viajeros.pe.firebase.model.User;
import com.viajeros.pe.firebase.service.AuthService;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);

        showProfileData(profileViewModel, binding);

        Button btn_pwd = binding.savePwdProfile;
        btn_pwd.setOnClickListener(view -> {
            updatePwd(binding);
        });

        Button btn_profile = binding.saveProfile;
        btn_profile.setOnClickListener(view -> {
            updateProfile(profileViewModel, binding);
        });

        View root = binding.getRoot();

        return root;
    }

    private void showProfileData(ProfileViewModel profileViewModel, FragmentProfileBinding binding) {
        profileViewModel.getCurrentUserData().observe(this.getViewLifecycleOwner(), data -> {
            binding.nameProfile.setText(data.getName());
            binding.lastnameProfile.setText(data.getLastname());
            binding.emailProfile.setText(data.getEmail());
            binding.phoneProfile.setText(data.getPhone().toString());
        });
    }

    private void updateProfile(ProfileViewModel profileViewModel, FragmentProfileBinding binding) {
        User user = new User(
                binding.nameProfile.getText().toString(),
                binding.lastnameProfile.getText().toString(),
                binding.emailProfile.getText().toString(),
                Integer.parseInt(binding.phoneProfile.getText().toString()));
        user.setDocumentId(AuthService.firebaseGetCurrentUser().getUid());
        profileViewModel.updateUser(user);
    }

    private void updatePwd(FragmentProfileBinding binding) {
        Task<Void> auth = AuthService.firebaseAuthenticationWithCredential(binding.emailProfile.getText().toString(), binding.pass1Profile.getText().toString());
        auth.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                AuthService.firebaseGetCurrentUser().updatePassword(binding.pass2Profile.getText().toString());
                Toast.makeText(getContext(), "Cambio exitoso.", Toast.LENGTH_SHORT).show();
                binding.pass1Profile.setText("");
                binding.pass2Profile.setText("");
            } else {
                Toast.makeText(getContext(), "Ocurrió un error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}