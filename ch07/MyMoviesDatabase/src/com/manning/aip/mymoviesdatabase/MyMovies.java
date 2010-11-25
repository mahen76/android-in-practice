package com.manning.aip.mymoviesdatabase;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MyMovies extends ListActivity {

   public static final int PREFS = 0;
   public static final int ENTRY_FORM = 1;
   public static final int SEARCH_FORM = 2;
   public static final int CAT_MANAGER= 3;
   
   private MyMoviesApp app;
   private MovieAdapter adapter;

   public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      app = (MyMoviesApp) getApplication();

      ListView listView = getListView();

      Button backToTop = (Button) getLayoutInflater().inflate(R.layout.list_footer, null);
      backToTop.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(android.R.drawable.ic_menu_upload),
               null, null, null);
      listView.addFooterView(backToTop, null, true);

      this.adapter = new MovieAdapter(this, app.getImageCache());
      listView.setAdapter(this.adapter);
      listView.setItemsCanFocus(false);
   }

   public void backToTop(View view) {
      getListView().setSelection(0);
   }

   protected void onListItemClick(ListView l, View v, int position, long id) {
      this.adapter.toggleMovie(position);
      this.adapter.notifyDataSetInvalidated();
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      menu.add(0, PREFS, 0, "Preferences").setIcon(android.R.drawable.ic_menu_preferences);
      menu.add(0, SEARCH_FORM, 0, "Search").setIcon(android.R.drawable.ic_menu_search);
      menu.add(0, ENTRY_FORM, 0, "Form").setIcon(android.R.drawable.ic_menu_edit);
      menu.add(0, CAT_MANAGER, 0, "Category Manager").setIcon(android.R.drawable.ic_menu_manage);
      return true;
   }

   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
      switch (item.getItemId()) {
         case PREFS:
            startActivity(new Intent(this, Preferences.class));
            break;
         case ENTRY_FORM:
            startActivity(new Intent(this, MovieForm.class));
            break;
         case SEARCH_FORM:
            startActivity(new Intent(this, MovieSearch.class));
            break;
         case CAT_MANAGER:
            startActivity(new Intent(this, CategoryManager.class));
            break;
      }
      return false;
   }
}