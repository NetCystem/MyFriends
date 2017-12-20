package com.example.macbookair.demo;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by macbookair on 12/16/17.
 */

public class ListAdapter extends BaseAdapter {

    private Context context;
    private LayoutInflater layoutInflater;


    public ListAdapter(Context context) {
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                          int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    @Override
    public int getCount() {
        return Contacts.getNames().size();
    }

    @Override
    public Object getItem(int position) {
        return Contacts.getNames().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = layoutInflater.inflate(R.layout.customized_list_view, null);
        ImageView imageView = view.findViewById(R.id.imgView);
        imageView.setImageBitmap(decodeSampledBitmapFromResource(context.getResources(), Contacts.getImages().get(position), 100, 100));
        TextView txt = view.findViewById(R.id.textView);
        txt.setText(Contacts.getNames().get(position));
        TextView txtPhoneNumber = view.findViewById(R.id.txtPhoneNumber);
        txtPhoneNumber.setText(Contacts.getPhone_numbers().get(position));
        return view;
    }

    public void add(String name, String phone) {
        Contacts.addItem(name, phone);
        notifyDataSetChanged();
    }


    public void delete(int position) {

        if (position >= Contacts.getNames().size()) {

            while (position != Contacts.getNames().size() - 1)
                position--;
        }


        Contacts.getNames().remove(position);
        Contacts.getImages().remove(position);
        Contacts.getE_mails().remove(position);
        Contacts.getPhone_numbers().remove(position);
        notifyDataSetChanged();
    }

}
