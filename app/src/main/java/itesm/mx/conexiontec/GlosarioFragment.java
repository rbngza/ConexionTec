package itesm.mx.conexiontec;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GlosarioFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1, Glosario.Nombre));
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        DefinicionFragment articleFragment = new DefinicionFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(DefinicionFragment.ARG_POSITION, position);
        articleFragment.setArguments(bundle);
        android.app.FragmentTransaction transaction = getFragmentManager().beginTransaction();
        //no funciona sin android.app
        transaction.replace(R.id.frame_container, articleFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
