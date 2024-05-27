package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.locale.DisplayLanguageMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@CrossOrigin
@RestController
@RequestMapping("/time")
public class TimeZoneController {

    ZoneId eastern= ZoneId.of("America/New_York");
    ZoneId mountain= ZoneId.of("America/Denver");
    ZoneId universal = ZoneId.of("Europe/London");
    ZoneId zoneId=ZoneId.systemDefault();

    LocalDateTime localDateTime=LocalDateTime.now();

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    private final Executor executor = Executors.newFixedThreadPool(2);


    @RequestMapping(path ="presentation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getPresentationTime()
    {
        List<String> timeArray = new ArrayList<String>();

        //eastern time
        executor.execute(() -> {
            ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
            ZonedDateTime ZDTEastern = zonedDateTime.withZoneSameInstant(eastern);
            LocalDateTime LDTEastern = ZDTEastern.toLocalDateTime();
            String formattedEST = LDTEastern.format(timeFormatter);
            timeArray.add("  " + formattedEST + " Eastern time");
            System.out.println("Eastern Time displayed " + formattedEST);
        });

        // Mountain time
        executor.execute(() -> {
            ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
            ZonedDateTime ZDTMountian = zonedDateTime.withZoneSameInstant(mountain);
            LocalDateTime LDTMountian = ZDTMountian.toLocalDateTime();
            String formattedMO = LDTMountian.format(timeFormatter);
            timeArray.add("  " + formattedMO +" Mountain Time ");
            System.out.println("Mountain Time displayed " + formattedMO);
        });

        //universal time
        executor.execute(() -> {
            ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
            ZonedDateTime ZDTUniver = zonedDateTime.withZoneSameInstant(universal);
            LocalDateTime LDTUniver = ZDTUniver.toLocalDateTime();
            String formattedUniver = LDTUniver.format(timeFormatter);
            timeArray.add("  " + formattedUniver +" UTC");
            System.out.println("Universal Time displayed " + formattedUniver);
        });


        return ResponseEntity.ok(timeArray);
    }
}
