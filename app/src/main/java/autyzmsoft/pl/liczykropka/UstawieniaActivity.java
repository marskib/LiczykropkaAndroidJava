package autyzmsoft.pl.liczykropka;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UstawieniaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ustawienia);
    }

    public void bMinusKlik(View view) {
        TextView tv = (TextView) findViewById(R.id.tv_lkl);
        int level = Integer.parseInt(tv.getText().toString());
        level--;
        if (level==0) { //zapewniam zakres od 1..6
            level=1;
        }
        tv.setText(Integer.toString(level));
    }

    public void bPlusKlik(View view) {
        TextView tv = (TextView) findViewById(R.id.tv_lkl);
        int level = Integer.parseInt(tv.getText().toString());
        level++;
        if (level==7) { //zapewniam zakres od 1..6
            level=6;
        }
        tv.setText(Integer.toString(level));
    }

}
