package es.android.lauracalvente.examenprimertrimestre;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laura on 13/12/2016.
 */

public class PersonalAdapterLista extends ArrayAdapter<Personaje> {

    public ArrayList<Personaje> Personajes;

    public PersonalAdapterLista(Context context, ArrayList<Personaje> personajes){
        super(context, R.layout.personaje, personajes);
        this.Personajes = personajes;
    }

    private static class ViewHolder {
        TextView lblPersonaje;
        TextView lblActor;
        ImageView imgPortada;
        ImageView imgPopup;

        public ViewHolder(View itemView){
            lblPersonaje = (TextView) itemView.findViewById(R.id.lblPersonaje);
            lblActor = (TextView) itemView.findViewById(R.id.lblActor);
            imgPortada= (ImageView) itemView.findViewById(R.id.imgPortada);
            imgPopup= (ImageView) itemView.findViewById(R.id.imgPopup);
        }

        public TextView getLblPersonaje(){
            return lblPersonaje;
        }

        public TextView getLblActor(){
            return lblActor;
        }

        public ImageView getImgPortada(){
            return imgPortada;
        }

        public ImageView getImgPopup(){
            return imgPopup;
        }
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.personaje, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else
            holder = (ViewHolder) convertView.getTag();

        onBindViewHolder(holder, position);
        return convertView;
    }

    private void onBindViewHolder(ViewHolder holder, int position){
        String url = Personajes.get(position).getUrlPortada();
        holder.getLblPersonaje().setText(Personajes.get(position).getNombrePersonaje());
        holder.getLblActor().setText(Personajes.get(position).getNombreActor());
        if (url.equals(""))
            Picasso.with(getContext()).load(R.drawable.nodisponible).into(holder.getImgPortada());
        else
            Picasso.with(getContext()).load(url).into(holder.getImgPortada());

        holder.imgPopup.setOnClickListener(new imgClickListener(
                Personajes.get(position)));
    }

    public ArrayList<Personaje> getPersonajes(){
        return Personajes;
    }

    //Se hace click en un item del popup menu
    public class PopUpItemClickListener implements PopupMenu.OnMenuItemClickListener {

        Personaje personaje;

        public PopUpItemClickListener(Personaje personaje){
            this.personaje = personaje;
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.popupBuscar:
                    // Acción--> VER. Uri--> URL.
                    Intent intent = new Intent(Intent.ACTION_VIEW,
                            Uri.parse(personaje.getUrlPortada()));
                    if (estaDisponible(getContext(), intent)) {
                        getContext().startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "No se ha encontrado ningun navegador en su terminal",
                                Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.popupEliminar:
                    Personaje aux;
                    aux = personaje;
                    Coleccion.eliminarPersonaje(personaje);
                    getPersonajes().remove(personaje);
                    notifyDataSetChanged();
                    mostrarSnackbar(aux);
                    break;
            }
            return  true;
        }
    }

    // Retorna si hay alguna actividad que pueda recibir el intent.
    private boolean estaDisponible(Context ctx, Intent intent) {
        final PackageManager gestorPaquetes = ctx.getPackageManager();
        List<ResolveInfo> listaApps = gestorPaquetes.queryIntentActivities(
                intent, PackageManager.MATCH_DEFAULT_ONLY);
        return listaApps.size() > 0;
    }

    //Se hace click en la imagen que infla el popup menu
    private class imgClickListener implements View.OnClickListener{

        Personaje personaje;

        public imgClickListener(Personaje alumno){
            this.personaje = alumno;
        }

        @Override
        public void onClick(View v) {
            PopupMenu menu = new PopupMenu(getContext(), v);
            MenuInflater infladorMenu = menu.getMenuInflater();
            infladorMenu.inflate(R.menu.popup, menu.getMenu());

            menu.setOnMenuItemClickListener(new PopUpItemClickListener(personaje));
            menu.show();
        }
    }

    private void mostrarSnackbar(final Personaje personaje) {
        Snackbar.make(MainActivity.raiz, "Personaje eliminado", Snackbar.LENGTH_LONG)
                .setAction("Deshacer", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deshacer(personaje);
                    }
                })
                .show();
    }

    public void deshacer(Personaje personaje) {
        Coleccion.agregarPersonaje(personaje);
        notifyDataSetChanged();
    }
}
