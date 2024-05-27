package edu.wgu.d387_sample_code.rest;

import edu.wgu.d387_sample_code.locale.DisplayLanguageMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/resources")
@CrossOrigin
public class LanguageMessageController {

    private final Executor executor = Executors.newFixedThreadPool(2);

    @RequestMapping(path ="welcome", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getWelcomeMessages()
    {
        List<String> arrayList = new ArrayList<>();

        // Read english
        executor.execute(() -> {
            DisplayLanguageMessage english = new DisplayLanguageMessage("en", "US");
            arrayList.add(english.getWelcomeMessages());
            System.out.println("English Message Received");
        });
        //READ FRENCH
        executor.execute(() -> {
            DisplayLanguageMessage french = new DisplayLanguageMessage("fr", "CA");
            arrayList.add(french.getWelcomeMessages());
            System.out.println("French Message Received");
        });

        return ResponseEntity.ok(arrayList);
    }
}
