package myanmarnightlife.lower.team1.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import myanmarnightlife.lower.team1.MyanmarNightLifeApp;
import myanmarnightlife.lower.team1.R;
import myanmarnightlife.lower.team1.activities.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuggestFragment extends Fragment {

    String[] type = {"Beer Shop","Karaoke","Night Club","Bar","Massage","Restaurant"};

    public static final int IMAGE_GALLERY_REQUEST = 20;

    @BindView(R.id.btn_choose_shop_image)
    Button btnChooseShopImage;

    @BindView(R.id.img_shop)
    ImageView imgShop;

    @BindView(R.id.btn_send_mail)
    Button btnSendEmail;

    @BindView(R.id.et_shop_name)
    EditText etShopName;

    @BindView(R.id.et_shop_phone)
    EditText etShopPhone;

    @BindView(R.id.et_shop_open_time)
    EditText etShopOpenTime;

    @BindView(R.id.et_address)
    EditText etAddress;

    @BindView(R.id.sp_shop_type)
    Spinner spShopType;

    public SuggestFragment() {
        // Required empty public constructor
    }

    Uri imageUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggest, container, false);
        ButterKnife.bind(this,view);

        ((MainActivity) getActivity()).getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MyanmarNightLifeApp.getContext(), R.layout.spinner_item, type);
        spShopType.setAdapter(arrayAdapter);

        btnChooseShopImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageGalleryClicked(view);
            }
        });

        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
                shareIntent.putExtra(Intent.EXTRA_TEXT,"Shop Name : " + etShopName.getText().toString()+"\n"+"Shop Phone : "+etShopPhone.getText().toString()+"\n"+"Shop Open Hour : "+etShopOpenTime.getText().toString()+"\n"+"Shop Address : "+etAddress.getText().toString()+"\n"+"Shop Type : "+spShopType.getSelectedItem().toString());
                shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                shareIntent.setType("image/*");
                shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                startActivity(Intent.createChooser(shareIntent, "Share..."));

            }
        });

        return view;
    }

    public void openImageGalleryClicked(View view){

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        String pictureDirectoryPath = pictureDirectory.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data, "image/*");

        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            // if we are here, everything processed successfully.
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                // if we are here, we are hearing back from the image gallery.

                // the address of the image on the SD Card.
                imageUri = data.getData();

                // declare a stream to read the image data from the SD Card.
                InputStream inputStream;

                // we are getting an input stream, based on the URI of the image.
                try {
                    inputStream = getActivity().getContentResolver().openInputStream(imageUri);

                    // get a bitmap from the stream.
                    Bitmap image = BitmapFactory.decodeStream(inputStream);


                    // show the image to the user
                    imgShop.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    // show a message to the user indictating that the image is unavailable.
                    Toast.makeText(MyanmarNightLifeApp.getContext(), "Unable to open image", Toast.LENGTH_LONG).show();
                }

            }
        }

    }
}
