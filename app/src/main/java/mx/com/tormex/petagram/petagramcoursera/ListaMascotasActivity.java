package mx.com.tormex.petagram.petagramcoursera;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.adapter.PageAdapter;
import mx.com.tormex.petagram.petagramcoursera.fragment.GaleriaFragment;
import mx.com.tormex.petagram.petagramcoursera.fragment.RecyclerViewFragment;

public class ListaMascotasActivity extends AppCompatActivity {


    private Toolbar toolBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);
//        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
//        setSupportActionBar(miActionBar);

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager();

        /*

        */

        if (toolBar != null) {
            setSupportActionBar(toolBar);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAbout:
                startActivity(new Intent(this, AcercaDeActivity.class));
                break;
            case R.id.mContact:
                startActivity(new Intent(this, ContactoActivity.class));
                break;
            case R.id.mStar:
                Intent intent = new Intent(ListaMascotasActivity.this, MascotasFavoritasActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragment(){
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new GaleriaFragment());
        return fragments;
    }

    public void setupViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconcatprofile);
    }
}