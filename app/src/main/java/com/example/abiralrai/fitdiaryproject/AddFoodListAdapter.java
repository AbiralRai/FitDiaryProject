package com.example.abiralrai.fitdiaryproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

//public class AddFoodListAdapter extends ArrayAdapter<String> {
//
//    public AddFoodListAdapter(@NonNull Context context, String[] foods) {
//        super(context, R.layout.foodlist_row , foods);
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//        View view = inflater.inflate(R.layout.foodlist_row, parent, false);
//
//        String singleFoodItem = getItem(position);
//        TextView food_name = (TextView) view.findViewById(R.id.food_name);
//        TextView calories = (TextView) view.findViewById(R.id.calories);
//        TextView fat = (TextView) view.findViewById(R.id.fat);
//        TextView carbs = (TextView) view.findViewById(R.id.carbs);
//        TextView protein = (TextView) view.findViewById(R.id.protein);
//
//        food_name.setText(singleFoodItem);
//        calories.setText(singleFoodItem);
//        fat.setText(singleFoodItem);
//        carbs.setText(singleFoodItem);
//        protein.setText(singleFoodItem);
//
//        return view;
//    }
//}


    public class AddFoodListAdapter extends BaseAdapter {


        private Context mContext;
        private List<AddFood> mAddFoodList;

        //Constructor


        public AddFoodListAdapter(Context mContext, List<AddFood> mAddFoodList) {
            this.mContext = mContext;
            this.mAddFoodList = mAddFoodList;
        }

        @Override
        public int getCount() {
            return mAddFoodList.size();
        }

        @Override
        public Object getItem(int position) {
            return mAddFoodList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(mContext,R.layout.foodlist_row, null);
            TextView food_name = (TextView) v.findViewById(R.id.food_name);
            TextView calories = (TextView) v.findViewById(R.id.calories);
            TextView fat = (TextView) v.findViewById(R.id.fat);
            TextView carbs = (TextView) v.findViewById(R.id.carbs);
            TextView protein = (TextView) v.findViewById(R.id.protein);

            food_name.setText(mAddFoodList.get(position).getFood_name());
            calories.setText(mAddFoodList.get(position).getCalories());
            fat.setText(mAddFoodList.get(position).getFat());
            carbs.setText(mAddFoodList.get(position).getCarbs());
            protein.setText(mAddFoodList.get(position).getProtein());


            return v;
        }
    }
