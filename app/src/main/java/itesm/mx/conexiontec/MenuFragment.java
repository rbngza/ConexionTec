package itesm.mx.conexiontec;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuFragment extends Fragment {
    View view;
    ListView listView;

    public ArrayList<MainMenu> getDataForList(){
        MainMenu mainMenu;
        ArrayList<MainMenu> listMenu = new ArrayList<MainMenu>();
        mainMenu = new MainMenu("Quick exam");
        listMenu.add(mainMenu);
        mainMenu = new MainMenu("Glosario");
        listMenu.add(mainMenu);

        return listMenu;
    }

    ArrayList<MainMenu> menuList = getDataForList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_list, container, false);

        listView =(ListView)view.findViewById(R.id.list);
        listView.setAdapter((ListAdapter) new MenuListAdapter(getActivity(),menuList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long arg3) {
                if (position == 0){
                    QuickExamFragment fragment = new QuickExamFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                } else if (position == 1){

                }
            }
        });

        return view;
    }



}
