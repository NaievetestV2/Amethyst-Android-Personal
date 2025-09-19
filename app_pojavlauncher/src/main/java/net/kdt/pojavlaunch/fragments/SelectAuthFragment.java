package net.kdt.pojavlaunch.fragments;

import static net.kdt.pojavlaunch.Tools.hasNoOnlineProfileDialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.kdt.pojavlaunch.R;
import net.kdt.pojavlaunch.Tools;

public class SelectAuthFragment extends Fragment {
    public static final String TAG = "AUTH_SELECT_FRAGMENT";

    public SelectAuthFragment(){
        super(R.layout.fragment_select_auth_method);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Button mMicrosoftButton = view.findViewById(R.id.button_microsoft_authentication);
        Button mLocalButton = view.findViewById(R.id.button_local_authentication);
        Button mCustomButton = view.findViewById(R.id.button_custom_authentication);

        mMicrosoftButton.setOnClickListener(v -> Tools.swapFragment(requireActivity(), MicrosoftLoginFragment.class, MicrosoftLoginFragment.TAG, null));
        mLocalButton.setOnClickListener(v -> Tools.swapFragment(requireActivity(), LocalLoginFragment.class, LocalLoginFragment.TAG, null));
        mCustomButton.setOnClickListener(v -> {
        // Your custom dialog logic goes here
        View dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.dialog_custom_username, null);
        final EditText usernameEditText = dialogView.findViewById(R.id.usernameEditText);
    
        new AlertDialog.Builder(requireContext())
            .setTitle("Enter Username")
            .setView(dialogView)
            .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                String username = usernameEditText.getText().toString().trim();
                if (!username.isEmpty()) {
                    // This will start the Microsoft login but with your custom modifications
                    // The key is to pass the username to the next step
                    Tools.swapFragment(requireActivity(), MicrosoftLoginFragment.class, MicrosoftLoginFragment.TAG, null);
                    // We'll need to modify the MicrosoftLoginFragment to pass this username to the background task
                } else {
                    Toast.makeText(requireContext(), "Username cannot be empty", Toast.LENGTH_SHORT).show();
                }
            })
            .setNegativeButton(android.R.string.cancel, null)
            .show();
    });
    }
}
