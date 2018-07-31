package com.example.blink22.todo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.blink22.todo.R;
import com.example.blink22.todo.di.component.ActivityComponent;
import com.example.blink22.todo.di.component.DaggerActivityComponent;
import com.example.blink22.todo.di.component.DaggerApplicationComponent;
import com.example.blink22.todo.di.module.ContextModule;
import com.example.blink22.todo.ui.TodoDetails.TodoActivity;
import com.example.blink22.todo.ui.TodoList.TodoListActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by blink22 on 5/29/18.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity implements MainView {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    protected abstract Fragment createFragment();
    private ActivityComponent mActivityComponent;

    @Inject
    MainPresenter<MainView> mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mActivityComponent = DaggerActivityComponent.builder()
                .applicationComponent(
                        DaggerApplicationComponent.builder().contextModule(new ContextModule(this)
                        ).build())
                .build();
        mActivityComponent.inject(this);
        mPresenter.onAttach(this);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        mDrawerLayout.closeDrawers();
                        switch(menuItem.getItemId()){
                            case R.id.menu_item_add_todo:
                                mPresenter.addTodoSelected();
                                break;
                            case R.id.menu_item_list_todo:
                                mPresenter.listTodoSelected();
                                break;
                        }
                        return true;
                    }
                }
        );

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = createFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void showTodoList(){
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }

    public void showAddTodo(){
        Intent intent = new Intent(this, TodoActivity.class);
        startActivity(intent);
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }
}
