import com.Config;
import com.Mode;
import com.OkHttpPinger;
import com.rest.response.Reponse;
import okhttp3.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class App {
    static String baseUrl="https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=512&date=";
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {

        int retry = 0;
        List<Integer> list = new ArrayList<Integer>();
        list.add(301404);
        list.add(301405);
        list.add(301403);
        list.add(301402);
       // list.add(301001);

        Config config = new Config();
        config.setBaseUrl(baseUrl);
        config.setFilterByAge(18);
        config.setVaccineGreaterThan(0);
        config.setPrintEnabled(true);
        config.setBeepNow(true);
        config.setSelectedPinCode(list);

        OkHttpPinger okHttpPinger = new OkHttpPinger(Mode.Actual,config);
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> scheduledFuture = ses.scheduleAtFixedRate(okHttpPinger, 1,30, TimeUnit.SECONDS);


       /* while (true) {
            System.out.println("count :" + count);
            Thread.sleep(1000);
            if (count == 5) {
                System.out.println("Count is 5, cancel the scheduledFuture!");
                scheduledFuture.cancel(true);
                ses.shutdown();
                break;
            }
        }*/
        /*Thread t1 = new Thread(okHttpPinger);
        t1.setDaemon(true);
        t1.start();
        t1.join();
*/


    }


    //More advance features:
     /* while(!okHttpPinger.getVaccineAvailabilitySummaryStream().isPresent()){
            if(retry>3) break;
            retry++;
            System.out.println("Retrying:" +retry);

            Thread.sleep(1000);
        }

        if(okHttpPinger.getVaccineAvailabilitySummaryStream().isPresent()){
            okHttpPinger.getVaccineAvailabilitySummaryStream().get().forEach(System.out::println);
        }*/

          /* Thread t1 = new Thread(okHttpPinger);
        t1.setDaemon(true);
        t1.start();
        t1.join();
*/


}