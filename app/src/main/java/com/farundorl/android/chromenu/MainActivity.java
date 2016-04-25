package com.farundorl.android.chromenu;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                showMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showMenu() {
        ListPopupWindow popupWindow = new ListPopupWindow(this);
        popupWindow.setWidth((int) (getResources().getDisplayMetrics().density * 250));
        popupWindow.setDropDownGravity(Gravity.RIGHT);
        popupWindow.setAnchorView(toolbar);

        ListView listView = new ListView(this);
        listView.addHeaderView(createHeader(popupWindow.getListView()));
        listView.setAdapter(createListAdapter(this));

        HeaderViewListAdapter adapter = (HeaderViewListAdapter) listView.getAdapter();
        popupWindow.setAdapter(adapter);
        popupWindow.show();
    }

    private View createHeader(ViewGroup parent) {
        return getLayoutInflater().inflate(R.layout.view_header_menu, parent, false);
    }

    private ListAdapter createListAdapter(Context context) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1);
        adapter.add("新しいタブ");
        adapter.add("ブックマーク");
        adapter.add("履歴");
        return adapter;
    }
}
