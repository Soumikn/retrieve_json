package bibhas.com.retrieve_json;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context mCtx;
    private List<Contact> contactList;

    public MyAdapter(Context mCtx, List<Contact> contactList) {
        this.mCtx = mCtx;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Contact contact = contactList.get(position);

        holder.name.setText(contact.getName());
        holder.age.setText(contact.getAge());
        holder.mob.setText(contact.getMobile());
        holder.home.setText(contact.getHome());
        holder.home_no.setText(contact.getHome_no());

    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView age;
        TextView mob;
        TextView home;
        TextView home_no;


        public MyViewHolder(final View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_name);
            age = itemView.findViewById(R.id.txt_age);
            mob = itemView.findViewById(R.id.txt_mob);
            home=itemView.findViewById(R.id.txt_home);
            home_no=itemView.findViewById(R.id.txt_home_no);


            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {

                    String name1 = name.getText().toString().trim();
                    Intent intent = new Intent(mCtx, SecondActivity.class);
                    intent.putExtra("name", name1);


                    mCtx.startActivity(intent);
                }
            });
        }
    }
}
