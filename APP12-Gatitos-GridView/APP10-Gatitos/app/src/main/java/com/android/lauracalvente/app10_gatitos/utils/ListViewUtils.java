package com.android.lauracalvente.app10_gatitos.utils;

import android.util.SparseBooleanArray;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListViewUtils {

    private ListViewUtils() { }

    /***
     * GetSelectedItems: crea una lista con los elementos del listView seleccionados o no seleccionados
     * @param lst listview del que vamos a obtener los elementos
     * @param uncheck indica que elementos queremos, los seleccionados o los no seleccionados
     * @return devuelve una lista de elementos seleccionados o no seleccionados
     */
    public static List<Object> getSelectedItems(ListView lst, boolean uncheck) {
        ArrayList<Object> selected = new ArrayList<>();
        SparseBooleanArray array = lst.getCheckedItemPositions();
        for (int i = 0; i < array.size(); i++) {
            if (array.valueAt(i)) {
                selected.add(lst.getItemAtPosition(array.keyAt(i)));
                if (uncheck) {
                    lst.setItemChecked(array.keyAt(i), false);
                }
            }
        }
        return selected;
    }

    /***
     * GetSelectedItems: crea una lista con los elementos del Gridview seleccionados o no seleccionados
     * @param lst Gridview del que vamos a obtener los elementos
     * @param uncheck indica que elementos queremos, los seleccionados o los no seleccionados
     * @return devuelve una lista de elementos seleccionados o no seleccionados
     */
    public static List<Object> getSelectedItems(GridView lst, boolean uncheck) {
        ArrayList<Object> selected = new ArrayList<>();
        SparseBooleanArray array = lst.getCheckedItemPositions();
        for (int i = 0; i < array.size(); i++) {
            if (array.valueAt(i)) {
                selected.add(lst.getItemAtPosition(array.keyAt(i)));
                if (uncheck) {
                    lst.setItemChecked(array.keyAt(i), false);
                }
            }
        }
        return selected;
    }

}
