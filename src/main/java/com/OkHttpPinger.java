package com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.VaccineAvailabilitySummary;
import com.rest.response.Reponse;
import com.rest.response.Session;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import okhttp3.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OkHttpPinger implements Runnable{
     private OkHttpClient client;
     private Config config;
     private Mode mode;
     private Optional<Stream<VaccineAvailabilitySummary>> vaccineAvailabilitySummaryStream = Optional.empty();
     public Optional<Stream<VaccineAvailabilitySummary>> getVaccineAvailabilitySummaryStream(){
         return this.vaccineAvailabilitySummaryStream;
     }
    public OkHttpPinger(Mode mode, Config config){
        client = new OkHttpClient();
        this.config = config;
        this.mode = mode;

    }

    @Override
    public void run() {
         boolean checkPin = false;
        if(mode==Mode.Actual) {
            System.out.println("Last Checked at:"+ LocalDateTime.now());
            String baseUrl = config.getBaseUrl();


            Reponse reponse = null;
            try (Response response = pingNow(baseUrl+getDate())) {

                reponse = getResponseClassObject(response.body().string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stream<VaccineAvailabilitySummary> result = checkAvailability(reponse);
            List<VaccineAvailabilitySummary> list = result.collect(Collectors.toList());

            if(config.getSelectedPinCode()==null || config.getSelectedPinCode().size()==0){
                checkPin=true;
                list.forEach(System.out::println);
            }else {
                for (Integer a : config.getSelectedPinCode()) {
                    for (VaccineAvailabilitySummary v : list) {
                        if (v.getCenter().getPincode().equals(a)) {
                            checkPin = true;

                        }
                    }
                }
            }

        if(checkPin)
            list.forEach(System.out::println);

            if(config.isBeepNow() && checkPin){

                int c =list.size();
                if(c>0) {
                     File path =  new File(config.getBeepFilePath());
                   Runnable r1=  ()->{ try{
                       AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(path);
                       Clip clip = AudioSystem.getClip();
                       clip.open(audioInputStream);
                       clip.start( );
                   }
                   catch(Exception ex)
                   {
                       ex.printStackTrace();}};
                   Thread t1= new Thread(r1);
                   t1.setDaemon(true);
                   t1.start();


                   // Toolkit.getDefaultToolkit().beep();
                    Runtime rt = Runtime.getRuntime();
                    try {
                        rt.exec("open " + config.getOpenWebPageURL());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            vaccineAvailabilitySummaryStream = Optional.of(result);
        }
       if(mode==Mode.Dummy) {
           File inputJson = new File(config.getDummyResponseObject());
           BufferedReader br = null;
           try {
               br = new BufferedReader(new FileReader(inputJson));
               Reponse reponse = getResponseClassObject(br.readLine());
               checkAvailability(reponse);
           } catch (IOException e) {
               e.printStackTrace();
           }

       }

    }


    private Reponse getResponseClassObject(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
       return objectMapper.readValue(json, Reponse.class);

    }

    private Response pingNow(String BASE_URL) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .get()
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11")
                .build();

        return client.newCall(request).execute();

    }


    private Stream<VaccineAvailabilitySummary> checkAvailability(Reponse reponse){
        Predicate<Session> byAge = s->s.getMinAgeLimit()==this.config.getFilterByAge();
        Predicate<Session> isVaccineAvailable = s->s.getAvailableCapacity()>this.config.getVaccineGreaterThan();

      return  reponse.getCenters().parallelStream().flatMap(
            c->c.getSessions()
                    .parallelStream()
                    .filter(byAge)
                    .filter(isVaccineAvailable)
                    .map(s->new VaccineAvailabilitySummary(c,s))

            );
    }
    private  String getDate(){
        Date date = new Date();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd-MM-yyyy");
        String stringDate= DateFor.format(date);
        return stringDate;
    }

}
