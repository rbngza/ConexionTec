package itesm.mx.conexiontec;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MenuFragment extends ListFragment {
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

        listView =(ListView)view.findViewById(R.id.lista);
        listView.setAdapter((ListAdapter) new MenuListAdapter(getActivity(),menuList));

        return view;
    }



}
