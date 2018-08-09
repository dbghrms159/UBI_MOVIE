package kr.ac.hallym.ubimovie;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.io.BufferedInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public ImageButton act, rom,dra,ho,ani,
    no1,no2,no3,no4,no5,no6,no7,no8,no9,no10, marvel,fox,disney,cj,newE;
    private String get ="";
    private String[] movieURL;
    private Bitmap[] bit = new Bitmap[10];

    @Override
    protected synchronized void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Intent intent = getIntent();
        get = intent.getExtras().getString("key");
        Fillper();
        try {
            Thread.sleep(1000);
        }catch(Exception e){

        }

        act = (ImageButton) findViewById(R.id.action);
        dra = (ImageButton) findViewById(R.id.drama);
        ho = (ImageButton) findViewById(R.id.horo);
        rom = (ImageButton) findViewById(R.id.roman);
        newE = (ImageButton) findViewById(R.id.NewButton);
        cj = (ImageButton) findViewById(R.id.CjButton);
        disney = (ImageButton) findViewById(R.id.disnyButton);
        fox = (ImageButton) findViewById(R.id.FoxButton);
        marvel = (ImageButton) findViewById(R.id.MarvelButton);

        newE.setOnClickListener(this);
        cj.setOnClickListener(this);
        disney.setOnClickListener(this);
        fox.setOnClickListener(this);
        marvel.setOnClickListener(this);
        act.setOnClickListener(this);
        dra.setOnClickListener(this);
        ho.setOnClickListener(this);
        rom.setOnClickListener(this);
        no1.setOnClickListener(this);
        no2.setOnClickListener(this);
        no3.setOnClickListener(this);
        no4.setOnClickListener(this);
        no5.setOnClickListener(this);
        no6.setOnClickListener(this);
        no7.setOnClickListener(this);
        no8.setOnClickListener(this);
        no9.setOnClickListener(this);
        no10.setOnClickListener(this);

    }

    private synchronized void Fillper(){
        no1 = (ImageButton) findViewById(R.id.no1);
        no2 = (ImageButton) findViewById(R.id.no2);
        no3 = (ImageButton) findViewById(R.id.no3);
        no4 = (ImageButton) findViewById(R.id.no4);
        no5 = (ImageButton) findViewById(R.id.no5);
        no6 = (ImageButton) findViewById(R.id.no6);
        no7 = (ImageButton) findViewById(R.id.no7);
        no8 = (ImageButton) findViewById(R.id.no8);
        no9 = (ImageButton) findViewById(R.id.no9);
        no10 = (ImageButton) findViewById(R.id.no10);



        movieURL = get.split("-");

        Thread thread = new Thread() {
            public synchronized void run(){

                try {
                    URL url;
                    URLConnection con;
                    BufferedInputStream in;
                    ImageButton[] fillper = new ImageButton[]{no1,no2,no3,no4,no5,no6,no7,no8,no9,no10};
                    for(int i = 0; i < 10; i++) {

                        url = new URL(movieURL[(i*3)+1]);
                        con = url.openConnection();
                        con.connect();
                        in = new BufferedInputStream(con.getInputStream());
                        fillper[i].setImageBitmap(BitmapFactory.decodeStream(in));
                        in.close();

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        thread.start();

        ViewFlipper flipper= (ViewFlipper)findViewById(R.id.flipper);

        Animation showIn= AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        //ViewFlipper에게 등장 애니메이션 적용

        flipper.setInAnimation(showIn);



        //ViewFlipper의 View가 교체될 때 퇴장하는 View의 애니메이션

        //오른쪽으로 슬라이딩 되면 퇴장하는 애니메이션 리소스 파일 적용.

        //위와 다른 방법으로 애니메이션을 적용해봅니다.

        //첫번째 파라미터 : Context

        //두번재 파라미터 : 트윈(Tween) Animation 리소스 파일(오른쪽으로 슬라이딩되며 퇴장)

        flipper.setOutAnimation(this, android.R.anim.slide_out_right);

        flipper.setFlipInterval(3000);//플리핑 간격(1000ms)

        flipper.startFlipping();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_money) {
            ClientTest test = new ClientTest("4");
            test.start();
        }else if (id == R.id.nav_sevmoney) {
            ClientTest test = new ClientTest("2");
            test.start();
        }else if (id == R.id.nav_screen) {
            ClientTest test = new ClientTest("3");
            test.start();
        }else if (id == R.id.nav_moviecount) {
            ClientTest test = new ClientTest("1");
            test.start();
        }else if (id == R.id.nav_review) {
            ClientTest test = new ClientTest("5");
            test.start();
        }else if (id == R.id.nav_developer) {
            Intent nextsin = new Intent(this, Developer.class);
            startActivity(nextsin);
        }

       // Intent nextsin;


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        Intent screen;
        screen = new Intent(MainActivity.this,Main2Activity.class);
        ClientTest test;
        switch(v.getId()){
            case R.id.MarvelButton:
                screen = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/MarvelKorea"));
                startActivity(screen);
                break;
            case R.id.disnyButton:
                screen = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/DisneyMoviesKR"));
                startActivity(screen);
                break;
            case R.id.FoxButton:
                screen = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/FoxMoviesKR"));
                startActivity(screen);
                break;
            case R.id.CjButton:
                screen = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/cjenmmovie"));
                startActivity(screen);
                break;
            case R.id.NewButton:
                screen = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/ItsNEWKorea"));
                startActivity(screen);
                break;
            case R.id.action:
                test = new ClientTest("6");
                test.start();
                break;
            case R.id.roman:
                test = new ClientTest("7");
                test.start();
                break;
            case R.id.drama:
                test = new ClientTest("8");
                test.start();
                break;
            case R.id.horo:
                test = new ClientTest("9");
                test.start();
                break;

            case R.id.no1:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[2]);
                startActivity(screen);
                break;
            case R.id.no2:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[5]);
                startActivity(screen);
                break;
            case R.id.no3:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[8]);
                startActivity(screen);
                break;
            case R.id.no4:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[11]);
                startActivity(screen);
                break;
            case R.id.no5:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[14]);
                startActivity(screen);
                break;
            case R.id.no6:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[17]);
                startActivity(screen);
                break;
            case R.id.no7:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[20]);
                startActivity(screen);
                break;
            case R.id.no8:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[23]);
                startActivity(screen);
                break;
            case R.id.no9:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[26]);
                startActivity(screen);
                break;
            case R.id.no10:
                screen = new Intent(MainActivity.this, Web.class);
                screen.putExtra("link",movieURL[29]);
                startActivity(screen);
                break;
        }

    }
    class ClientTest  extends Thread{
        String send;
        String getMessage;

        public ClientTest(String send){
            this.send = send;

        }
        public void run(){

            try{

                int port = 8008;
                Socket socket = new Socket("210.115.230.145",port);

                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(send);
                out.flush();

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                getMessage = (String) in.readObject();

                //Log.i("test","server test : " + getMessage);

                socket.close();

            }catch(Exception e){
                e.printStackTrace();
            }
            if(send.equals("5")) {
                Intent nextsin = new Intent(MainActivity.this, Review.class);
                nextsin.putExtra("review", getMessage);
                startActivity(nextsin);
            }else if(send.equals("6")) {
                Intent nextsin = new Intent(MainActivity.this, Main2Activity.class);
                nextsin.putExtra("list", getMessage);
                startActivity(nextsin);
            } else if(send.equals("7")) {
                Intent nextsin = new Intent(MainActivity.this, Main2Activity.class);
                nextsin.putExtra("list", getMessage);
                startActivity(nextsin);
            }else if(send.equals("8")) {
                Intent nextsin = new Intent(MainActivity.this, Main2Activity.class);
                nextsin.putExtra("list", getMessage);
                startActivity(nextsin);
            }else if(send.equals("9")) {
                Intent nextsin = new Intent(MainActivity.this, Main2Activity.class);
                nextsin.putExtra("list", getMessage);
                startActivity(nextsin);
            }else if(send.equals("10")) {
                Intent nextsin = new Intent(MainActivity.this, Main2Activity.class);
                nextsin.putExtra("list", getMessage);
                startActivity(nextsin);
            }else {
                Intent intent = new Intent(MainActivity.this, Sub.class);
                intent.putExtra("movie", getMessage);
                startActivity(intent);
            }

        }

    }
}
