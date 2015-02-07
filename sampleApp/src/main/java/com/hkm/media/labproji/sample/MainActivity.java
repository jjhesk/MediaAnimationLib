package com.hkm.media.labproji.sample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hkm.media.fragment.InteractFragment;
import com.hkm.media.labproji.R;
import com.hkm.media.library.elements.core.Element;
import com.hkm.media.library.elements.mathmodels.Movement;
import com.hkm.media.library.elements.shapes.Sprite;


public class MainActivity extends ActionBarActivity {
    private final InteractFragment ifragment = new InteractFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_main);
        addFragments();
    }

    private Element[] constructChildren() {
        final Sprite sprite1 = InteractFragment.newAnimation(R.drawable.rpg_maker_vx_ace_character_jet_fighte_by_moon6666d869q3z, this);
        final Sprite sprite2 = InteractFragment.newAnimation(R.drawable.rpg_maker_vx_ace___smokescreen_sprite_by_pharaoh_yami7nn7f0, this);
        final Movement mm = new Movement(Movement.movemode.HORIZONTAL_X);
        sprite1.defineRowCol(4, 3).setPos(new Point(120, 100)).setMovement(mm).done();
        sprite2.defineRowCol(4, 3).setPos(new Point(350, 190)).setMovement(mm).done();
        return new Element[]{
                sprite1, sprite2
        };
    }

    private void addFragments() {
        ifragment.addChilds(constructChildren());
        MainActivity.this
                .getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, ifragment)
                .commit();

    }

    public void inspectDrawQueue() {
        final Element[] elelist = ifragment.getPanel().getDrawQueue();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
