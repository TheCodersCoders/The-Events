package ananda.if4b.tulis_aja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import ananda.if4b.tulis_aja.databinding.ActivityUpdatePostBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePostActivity extends AppCompatActivity {
    private ActivityUpdatePostBinding binding;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Update Events");

        binding = ActivityUpdatePostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        post = getIntent().getParcelableExtra("EXTRA DATA");
        String id = post.getId();

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = binding.etContent.getText().toString();
                String judul = binding.etJudul.getText().toString();
                String foto = binding.etFoto.getText().toString();
                String lokasi = binding.etLokasi.getText().toString();

                boolean bolehUpdatePost = true;

                if (TextUtils.isEmpty(content)) {
                    bolehUpdatePost = false;
                    binding.etContent.setError("Konten tidak boleh kosong!");
                    binding.etJudul.setError("Konten tidak boleh kosong!");
                    binding.etFoto.setError("Konten tidak boleh kosong!");
                    binding.etLokasi.setError("Konten tidak boleh kosong!");

                }

                if (bolehUpdatePost) {
                    updatePost(id, content,foto, judul,lokasi);
                }
            }
        });
    }

    private void updatePost(String id, String content, String foto, String judul, String lokasi) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utility.getRetrofit().create(APIService.class);
        Call<ValueNoData> call = api.updatePost(id, content, foto,judul,lokasi);
        call.enqueue(new Callback<ValueNoData>() {
            @Override
            public void onResponse(Call<ValueNoData> call, Response<ValueNoData> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        Toast.makeText(UpdatePostActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(UpdatePostActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UpdatePostActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueNoData> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(UpdatePostActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}